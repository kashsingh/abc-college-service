# ABC College Portal

## About
This is just a simple demo for implementing a college portal for the students using **Spring Boot** with **Spring Security** using **JWT (JSON Web Token)** for application security. This solution is partially based on the demo projects [Cerberus](https://github.com/brahalla/Cerberus) and [JWT Spring Security Demo](https://github.com/szerhusenBC/jwt-spring-security-demo). Thanks to the authors!

## Requirements
This demo is build with with Gradle 2.7.7 and Java 1.8.

## Usage
The application will be running at [http://localhost:8080](http://localhost:8080) once started.

There are four user accounts present to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions:
```
Admin - admin:admin
Student User - vegeta:password
Student User - maxpayne:password 
Student User - goku:password
```

There are three endpoints that can be accessed by the users:
```
/auth - authentication endpoint with unrestricted access
/api/admin/* - an endpoint that is restricted to authorized admin users with the role 'ROLE_ADMIN'(a valid JWT token must be present in the request header with the admin authorization)
/api/student/* - an endpoint that is restricted to authorized student users with the role 'ROLE_USER' (a valid JWT token must be present in the request header)
```
### Generating password hash for new users

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords. Your can generate your hashes with this simple tool: [Bcrypt Generator](https://www.bcrypt-generator.com)

###### Note: I haven't developed the front-end for the application yet but will provide a link to its repository once I complete them.


## Copyright and license

The code is released under the [MIT license](LICENSE?raw=true).

---------------------------------------

Please feel free to send me some feedback or questions!
