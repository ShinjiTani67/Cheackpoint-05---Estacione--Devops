$RG = "rg-estacione-rm553587"
$LOCATION = "brazilsouth"
$SERVER_NAME = "psql-rm553587"
$USERNAME = "admpsql"
$PASSWORD = "Fiap@2tdsvms"
$DBNAME = "dimdimdb"

# Criar Resource Group
az group create --name $RG --location $LOCATION

# Criar servidor PostgreSQL (Flexible Server)
az postgres flexible-server create `
    --resource-group $RG `
    --name $SERVER_NAME `
    --location $LOCATION `
    --admin-user $USERNAME `
    --admin-password $PASSWORD `
    --sku-name Standard_B1ms `
    --tier Burstable `
    --storage-size 32 `
    --version 14 `
    --public-access 0.0.0.0

# Criar banco de dados
az postgres flexible-server db create `
    --resource-group $RG `
    --server-name $SERVER_NAME `
    --database-name $DBNAME
