--enable ssl.One-way SSL (default): The server proves its identity to the client. Any client can connect if it trusts the server's certificate.
keytool -genkeypair -alias mycert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore server_keystore.p12 -validity 3650
Place server_keystore.p12 in src/main/resources.
vi ~/.zshrc
add the following lines at the end of the file to create environment variables and then run source ~/.zshrc to apply the changes:
export server_ssl_key_store_password="Shakuntalaq6w!"
export spring_datasource_password="Shakuntalaq6w!"
export spring_ai_openai_api_key="<your key>"
--start docker desktop if not already running.
--start redis as docker container.Note redis:7-alpine is a small image suitable for most use cases.
--Make sure docker desktop is running before executing the command below:
--below command will install and start redis server in a docker container named 'redis', mapping host port 6379 to container port 6379, and set it to restart unless stopped manually.
--if this was already done once, no need to run it again.You can start or stop the redis container using docker desktop or command line.
docker run -d --name redis -p 6379:6379 --restart unless-stopped redis:7-alpine
