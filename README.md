# Simple-Login-App-SpringBoot-MySQL-React



### Introduction

In this repository you will find an implementation of an application that goes through all stacks of development, precisely the back end and the front end. The back end is constituted of a MySQL database and a spring boot server middleware. The front end is a react app web client.

### Application

This application is intended to portray the use of Spring Boot  in the implementation of RESTful back end middleware and how to secure some exposed resources with the use of Jason Web Token (JWT). The application in its' all represents a Log in and Sign in system. 

The server side exposes a REST API and one could use  Postman or curl command line to perform the following actions:

1. Signup 
2. Login (user receives a JWT upon successful authentication )
3. Get a user based on it's id. (Authentication token required)

The front end is a simple web application with which one can perform the same actions as those describe for Postman and curl. The following pages are available on the web app :

- Home page 
- Login page (user receives a JWT upon successful authentication )
- Register (sign in) page
- Welcome page
- There is also a log out functionality.  

### Database Schema

To achieve the goal of the application, a single table named "user"  was required.

This table looks as follows:

![img_01](images\img_01.PNG)

This table is used to keep track of users who have been signed into our application and can hence be authenticated by our system.



### Technology

- **MySQL** - SQL database
- **Spring Boot** - Server side framework
- **JWT** - Authentication mechanism for REST APIs
- **React** and Material UI
- 

### Deploying The App

##### Requirements

To run the Docker containers , you need to install a Docker environment on your computer. in my case I used   [Docker Toolbox](https://docs.docker.com/toolbox/) 

- Docker 19.03.1
- Docker-compose 1.24.1

To build the server project, you need to install Java SDK, Apache Maven, MySQL.

- Apache Maven 3.6.1
- Java 8
- MySQL (In case you want to run the server app locally without using Docker containers)

To build the web application you need to install Nodejs

- Node 12.13.1 (In case you want to run the web app locally without using Docker containers)

##### Deployment

###### Automatic deployment using a bash script

1. Clone this repository 
2. Make sure you have a terminal (ideally Docker QuickStart shell, it comes along in the Docker Toolbox package) session opened in the root directory of the cloned repository .
3. Run the following command:

```
$> bash deploy.sh 
```

or

```
$> ./deploy.sh
```

In case of an error that reads `error: unable to create file spring-boot-server/src/main/java/de/tchuensu/home/springbootserver/SpringBootServerApplication.java: Filename too long`, type the following git command to resolve the `Filename too long error`

```
git config --system core.longpaths true
```

and then retry step 3

4. Open a browser and go to `<ip>`:8080

NB:  the `<ip>` depends on how you have installed and configured Docker. It can be localhost if you run Docker natively on Linux. It can also be `192.168.99.100` if you have a default Docker machine. 



###### Manual deployment 

If for any reason you prefer to do things manually instead of using the script, do the following:

1. Clone the branch containing the server using this command

```
git clone -b fb-springBootServer https://github.com/rodrigue-tchuensu/Simple-Login-App-SpringBoot-MySQL-React.git
```

2. Go into the directory `spring-boot-server` and run this command:

```
mvn clean install 
```

3. Launch the server running this command:

```
  mvn spring-boot:run
```

The server is accessible locally through the address `http://localhost:8090/api`

4. Clone the branch containing the web application using this command

```
git clone -b fb-reactGui https://github.com/rodrigue-tchuensu/Simple-Login-App-SpringBoot-MySQL-React.git
```

5. Move into the directory `login-app-gui`and open a shell session the run the following commands to build and start the web application locally

```
npm install 
```

then

```
npm start
```

The web app is accessible in any browser using the address `http://localhost:3000`







