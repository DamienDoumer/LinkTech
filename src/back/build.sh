bash ./apigateway/build.sh 
cp ./apigateway/DOCKERFILE_COPY ./apigateway/target/Dockerfile
bash ./authservice/build.sh 
cp ./authservice/DOCKERFILE_COPY ./authservice/target/Dockerfile
bash ./postsservice/build.sh 
cp ./postsservice/DOCKERFILE_COPY ./postsservice/target/Dockerfile
bash ./userservice/build.sh 
cp ./userservice/DOCKERFILE_COPY ./userservice/target/Dockerfile
bash ./messagesservice/build.sh 
cp ./messagesservice/DOCKERFILE_COPY ./messagesservice/target/Dockerfile
bash ./institutionsservice/build.sh 
cp ./institutionsservice/DOCKERFILE_COPY ./institutionsservice/target/Dockerfile
