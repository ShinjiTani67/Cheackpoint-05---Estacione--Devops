$RG = "rg-estacione-rm553587"
$LOCATION = "chilecentral"
$SERVER_NAME = "sqlserver-rm553587"
$USERNAME = "admsql"
$PASSWORD = "Fiap@2tdsvms"
$DBNAME = "dimdimdb"

az group create --name $RG --location $LOCATION
az sql server create -l $LOCATION -g $RG -n $SERVER_NAME -u $USERNAME -p $PASSWORD --enable-public-network true
az sql db create -g $RG -s $SERVER_NAME -n $DBNAME --service-objective Basic --backup-storage-redundancy Local --zone-redundant false
az sql server firewall-rule create -g $RG -s $SERVER_NAME -n AllowAll --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255

# Cria os objetos de Banco
# Certifique-se de ter o sqlcmd instalado em seu ambiente
Invoke-Sqlcmd -ServerInstance "$SERVER_NAME.database.windows.net" `
              -Database "$DBNAME" `
              -Username "$USERNAME" `
              -Password "$PASSWORD" `
              -Query @"
CREATE TABLE [dbo].[tb_transaction] (
    id BIGINT NOT NULL IDENTITY,
    date DATETIME NOT NULL,
    description VARCHAR(120) NOT NULL,
    type VARCHAR(255) NOT NULL,
    value DECIMAL(19,2) NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE [dbo].[tb_user] (
    id BIGINT NOT NULL IDENTITY,
    balance DECIMAL(19,2) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE [dbo].[tb_transaction] ADD CONSTRAINT FK_USER_TRANSACTION FOREIGN KEY (user_id) REFERENCES [dbo].[tb_user] (id);
"@
