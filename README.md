## Estacione – DevOps Checkpoint 05

## Integrantes

- Fernando Shinji Tanigushi RM553587

## Tecnologias Utilizadas
- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Azure CLI
- Azure Web App
- GitHub Actions
  
## How To Run

Este guia descreve como realizar o deploy da aplicação na Azure utilizando os scripts disponíveis na pasta scripts.

- 1. Fazer login na Azure

Primeiro é necessário autenticar na Azure utilizando a Azure CLI.

az login

Após executar o comando, será aberta uma página no navegador para autenticação com sua conta Azure.

Caso possua mais de uma assinatura configurada, selecione a assinatura correta:

az account set --subscription "NOME_DA_ASSINATURA"

- 2. Clonar o repositório

Clone o repositório do projeto:

git clone https://github.com/ShinjiTani67/Cheackpoint-05---Estacione--Devops.git

Entre na pasta do projeto:

cd Cheackpoint-05---Estacione--Devops

- 3. Executar o script de criação do PostgreSQL

Entre na pasta de scripts:

cd scripts

Execute o script responsável pela criação do banco de dados PostgreSQL na Azure:

./create-postgres.sh

Este script cria automaticamente:

Resource Group
Servidor PostgreSQL
Banco de dados utilizado pela aplicação

- 4. Executar o script de deploy da aplicação

Após a criação do banco de dados, execute o script responsável pelo deploy da aplicação:

./deploy.sh

Este script realiza as seguintes operações:

Criação do App Service Plan
Criação do Azure Web App
Configuração do Application Insights
Configuração das variáveis de ambiente da aplicação
Conexão da aplicação com o banco PostgreSQL
Configuração de deploy automático utilizando GitHub Actions
