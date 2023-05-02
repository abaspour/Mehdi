# Mehdi Abbaspoor API TEST Repository

# Link to the deployed application

http://springboot-env.eba-cmtnhtm3.us-east-1.elasticbeanstalk.com/

# Steps to run the app with docker

Building the docker image:
```shell
docker build -t mehdi_api .
```

Running the docker container:
```shell
docker run -p 8080:5000 mehdi_api
```

Navigate to [localhost:8080](http://localhost:8080) to see the application. 

# Steps to create a docker tar file

First, please make sure the image is already build. Then run the following:
```shell
docker save -o mehdi_api.tar mehdi_api
```

To run the tar file please do the following:

```shell
docker load -i mehdi_api.tar
```
```shell
docker run -p 8080:5000 mehdi_api
```