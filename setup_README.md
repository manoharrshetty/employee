## Enable ssl.One-way SSL .Assuming the server needs to prove its identity to the client.But not vice versa.
The server proves its identity to the client. Any client can connect if it trusts the server's certificate.
HTTPS uses TLS to encrypt communication and authenticate the server using a certificate signed by a trusted Certificate Authority (e.g., VeriSign/DigiCert).
The client(e.g. postman) validates the certificate chain to establish trust, then uses asymmetric cryptography to securely negotiate a symmetric session key.
All subsequent HTTP traffic is encrypted using this symmetric key for performance and confidentiality
1. keytool -genkeypair -alias mycert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore server_keystore.p12 -validity 3650
2. Place server_keystore.p12 in src/main/resources.
3. vi ~/.zshrc
4. add the following lines at the end of the file to create environment variables and then 
run source ~/.zshrc to apply the changes.Note the best practice is to keep secrets in a secure vault such as HashiCorp Vault.
5. export server_ssl_key_store_password="Shakuntalaq6w!"
6. export spring_datasource_password="Shakuntalaq6w!"
7. export spring_ai_openai_api_key="<your key>"
## Start docker desktop if not already running.
start redis as docker container.Note redis:7-alpine is a small image suitable for most use cases.
Make sure docker desktop is running before executing the command below:
below command will install and start redis server in a docker container named 'redis', mapping host port 6379 to container port 6379, and set it to restart unless stopped manually.
if this was already done once, no need to run it again.You can start or stop the redis container using docker desktop or command line.
docker run -d --name redis -p 6379:6379 --restart unless-stopped redis:7-alpine


## GCP  client id and client secret for oauth2 authentication:
1. client id :<GCP IDP CLIENT ID>
2. client secret :<GCP Client secret>
