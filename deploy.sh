#!/usr/bin/env sh

# Create temporal directories
mkdir tmp_gui
mkdir tmp_server

# Delete any files/directory if any exists in the tmp_gui directory
rm -rf tmp_gui/*

# Delete any files/directory if any exists in the tmp_server directory
rm -rf tmp_server/* 

# Clean the gui source files in the docker react-gui directory
rm -rf deployment/Docker/docker-images/react-gui/sources/*

# Clean the java jar in the docker spring-server directory
rm deployment/Docker/docker-images/spring-server/*.jar

# Obtain the sources of the gui app and copy them to the docker react-gui directory

# Access the tmp_gui directory 
cd tmp_gui

# Clone the brach of the projet containing the wep app
git clone -b fb-reactGui https://github.com/rodrigue-tchuensu/Simple-Login-App-SpringBoot-MySQL-React.git

# Copy the gui sources
cp -R Simple-Login-App-SpringBoot-MySQL-React/app-gui/login-app-gui/public  ../deployment/Docker/docker-images/react-gui/sources
cp -R Simple-Login-App-SpringBoot-MySQL-React/app-gui/login-app-gui/src  ../deployment/Docker/docker-images/react-gui/sources
cp  Simple-Login-App-SpringBoot-MySQL-React/app-gui/login-app-gui/package-lock.json  ../deployment/Docker/docker-images/react-gui/sources/package-lock.json
cp  Simple-Login-App-SpringBoot-MySQL-React/app-gui/login-app-gui/package.json  ../deployment/Docker/docker-images/react-gui/sources//package.json

# Exit the tmp_gui directory
cd ..

# Access the tmp_server directory 
cd tmp_server


# Clone the brach of the projet containing the server app
git clone -b fb-springBootServer https://github.com/rodrigue-tchuensu/Simple-Login-App-SpringBoot-MySQL-React.git

cd Simple-Login-App-SpringBoot-MySQL-React/spring-boot-server 

# Build the server project to obtain a jar
mvn clean package install 

# Copy the jar found in the target directory to the spring-server docker directory 
cp target/spring-boot-server-0.0.1-SNAPSHOT.jar ../../../deployment/Docker/docker-images/spring-server/

# Exit the server directory 
cd ../../..

# Delete temporal directories
rm -rf tmp_gui tmp_server

# Access the docker-compose file 
cd deployment/Docker/topology/

# Launch and build the docker-compose config
docker-compose up --build




#git config --system core.longpaths true