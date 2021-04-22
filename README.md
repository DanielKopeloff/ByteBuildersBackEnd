# Byte Builders  (Team : Byte Code DJ)
## What is it?
### This project aims to ... 

## Technologies Used: 

- Tools  
  - Spring Boot
  - Postman
  - DBeaver
  - Angular CLI

- Prerequisites
  - Java SE 8
  - Maven
    - Optional: `M2_HOME` environment variable
    - Dependencies:
      - **_Spring Framework Boot:_** actuator, data-rest, data-jpa, starter-web, devtools, starter-tomcat, starter-test, starter-security
      - JUnit
      - lombok
      - PostgreSQL
      - Jupiter
      - Jsonwebtoken
  - PostgreSQL
    - Configure your database connection & credentials in `application.properties`, currently configured to use the default URL, port, and username
  - Environment Variables
    - `JAVA_HOME` pointed to your Java JDK 8
    - `MAVEN_HOME`
    
## Demo/How-to/Setup

**1. Clone repositories to your machine**

`git clone https://github.com/DanielKopeloff/ByteBuildersBackEnd.git`  
`git clone https://github.com/DanielKopeloff/ByteBuildersFrontEnd.git`

**2. Run mvn install && Setup PostgreSQL database for Backend**

Use the provided **application.properties** database provided to use a built AWS RDS or provide credentials to access your database. You can then populate the database using the script above.     
You can use either DBeaver or pgAdmin

**3. Front End Setup**  
Run `npm install` and `npm audit fix`. Then run `ng serve --open` to open the home page. 


## API Description

- Default landing page will be found at `localhost:8080/home/`

## Swagger
- Use `http://localhost:9000/swagger-ui/` to view documentions on all the endpoints available. 
  

### Byte Builders API

- Send HTTP GET, POST, PUT, and DELETE requests to `http://localhost:9000/api/byte-user`
  - `GET` returns all user in a JSON object like so:
 ```json
            {
                "id": 1,
                "username": "danielK",
                "password": "password",
                "firstName": "Daniel",
                "lastName": "Kop",
                "email": "danielKOP@gmail.com",
                "profilePic": "",
                "byteRole": "USER",
                "userCreated": "2021-03-02T08:00:00.000+00:00",
                "userTerminated": null,
                "_links": {
                    "self": {
                        "href": "http://localhost:9000/api/byte-user/1"
                    },
                    "byteUser": {
                        "href": "http://localhost:9000/api/byte-user/1"
                    },
                    "addresses": {
                        "href": "http://localhost:9000/api/byte-user/1/addresses"
                    },
                    "byteOrders": {
                        "href": "http://localhost:9000/api/byte-user/1/byteOrders"
                    }
                }
            }
```
```json
            {
                "id": 2,
                "username": "DanR",
                "password": "password",
                "firstName": "Daniel",
                "lastName": "R",
                "email": "danielR@gmail.com",
                "profilePic": "",
                "byteRole": "ADMIN",
                "userCreated": "1999-01-08T12:05:06.000+00:00",
                "userTerminated": null,
                "_links": {
                    "self": {
                        "href": "http://localhost:9000/api/byte-user/2"
                    },
                    "byteUser": {
                        "href": "http://localhost:9000/api/byte-user/2"
                    },
                    "addresses": {
                        "href": "http://localhost:9000/api/byte-user/2/addresses"
                    },
                    "byteOrders": {
                        "href": "http://localhost:9000/api/byte-user/2/byteOrders"
                    }
                }
            }
```

  - `POST` accepts a JSON object with first name, last name, username, password, email, profile pic link. This inserts a new user into the database, returning a JSON object of the inserted user.
    - Input format:
      ```json
      {
        "firstname": "Name",
        "lastname": "name",
        "username": "username",
        "password": "password",
        ...
      }
      ```
    
  - `PUT` accepts a JSON object that should correspond to an existing user. You can change certain attributes which will be updated in the database.
    
  - `DELETE` accepts an `id` key in the URL of the request, which will delete the user from the database

### Login API

- Send GET, POST, PUT, DELETE requests to `http://localhost:8080/authenticate`
  - `POST` sent to `http://localhost:9000/authenticate` will return a JSON object with the webtoken and information of the user entered 
    ```json
    [
      {
        "username": "testuser",
        "password": "password"
      }
    ]
    ```
```json
{
    "jwtToken": "webtoken",
    "date": "2021-04-21T20:27:25.018+00:00",
    "name": "testuser",
    "userEmail": "test@gmail.com",
    "userId": 2
}
```



## Contributors

- Ronald Lopez 
- Joey Elmblad 
- Daniel Kopeloff
- Daniel Reyes
## External Resources

- [Java SE 8 API (Documentation)](https://docs.oracle.com/javase/8/docs/api/)
- [Maven Documentation](https://maven.apache.org/guides/index.html)
- [Maven Repository](https://mvnrepository.com) - for Maven java packages
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [DBeaver](https://dbeaver.io/) - Using the community (free) version
- [Postman](https://www.postman.com/downloads/)

## Known Issues


## Planned Improvements

- ...
- ...





(https://trello.com/b/yZULAQLG/project2)


