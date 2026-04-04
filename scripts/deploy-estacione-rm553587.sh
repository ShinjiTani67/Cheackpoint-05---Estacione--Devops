#!/bin/bash

# Variáveis
export RESOURCE_GROUP_NAME="rg-estacione-rm553587"
export WEBAPP_NAME="estacione-rm553587"
export APP_SERVICE_PLAN="estacione-rm553587"
export LOCATION="chilecentral"
export RUNTIME="JAVA:21-java21"

export GITHUB_REPO_NAME="checkpoint05--estacione-devops"
export BRANCH="main"

export APP_INSIGHTS_NAME="ai-estacione-rm553587"

# PostgreSQL já existente
export POSTGRES_SERVER="psql-estacione-rm553587"
export POSTGRES_DB="estacionamento"
export POSTGRES_USER="postgresadm"
export POSTGRES_PASSWORD="Fiap@2tdsvms"

# Criar Application Insights (caso não exista)
az monitor app-insights component create \
  --app $APP_INSIGHTS_NAME \
  --location $LOCATION \
  --resource-group $RESOURCE_GROUP_NAME \
  --application-type web

# Criar App Service Plan
az appservice plan create \
  --name $APP_SERVICE_PLAN \
  --resource-group $RESOURCE_GROUP_NAME \
  --location $LOCATION \
  --sku F1 \
  --is-linux

# Criar Web App
az webapp create \
  --name $WEBAPP_NAME \
  --resource-group $RESOURCE_GROUP_NAME \
  --plan $APP_SERVICE_PLAN \
  --runtime $RUNTIME

# Habilitar SCM Basic Auth
az resource update \
  --resource-group $RESOURCE_GROUP_NAME \
  --namespace Microsoft.Web \
  --resource-type basicPublishingCredentialsPolicies \
  --name scm \
  --parent sites/$WEBAPP_NAME \
  --set properties.allow=true

# Recuperar Connection String do Application Insights
CONNECTION_STRING=$(az monitor app-insights component show \
  --app $APP_INSIGHTS_NAME \
  --resource-group $RESOURCE_GROUP_NAME \
  --query connectionString \
  --output tsv)

# Configurar variáveis de ambiente
az webapp config appsettings set \
  --name $WEBAPP_NAME \
  --resource-group $RESOURCE_GROUP_NAME \
  --settings \
APPLICATIONINSIGHTS_CONNECTION_STRING=$CONNECTION_STRING \
ApplicationInsightsAgent_EXTENSION_VERSION="~3" \
XDT_MicrosoftApplicationInsights_Mode="Recommended" \
XDT_MicrosoftApplicationInsights_PreemptSdk="1" \
SPRING_DATASOURCE_URL="jdbc:postgresql://$POSTGRES_SERVER.postgres.database.azure.com:5432/$POSTGRES_DB" \
SPRING_DATASOURCE_USERNAME="$POSTGRES_USER@$POSTGRES_SERVER" \
SPRING_DATASOURCE_PASSWORD="$POSTGRES_PASSWORD" \
SPRING_JPA_HIBERNATE_DDL_AUTO="update"

# Reiniciar WebApp
az webapp restart \
  --name $WEBAPP_NAME \
  --resource-group $RESOURCE_GROUP_NAME

# Conectar Application Insights
az monitor app-insights component connect-webapp \
  --app $APP_INSIGHTS_NAME \
  --web-app $WEBAPP_NAME \
  --resource-group $RESOURCE_GROUP_NAME

# Configurar GitHub Actions
az webapp deployment github-actions add \
  --name $WEBAPP_NAME \
  --resource-group $RESOURCE_GROUP_NAME \
  --repo $GITHUB_REPO_NAME \
  --branch $BRANCH \
  --login-with-github
