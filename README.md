# Client Relationship Management App

# Key Feature
- Hibernate Framework implemented for Object Relational Mapping
- SpringBoot Framework Utilized for autoconfiguration 
- Spring Security providind authentication layer

# Project setup
1. Creating Database
   Run following scripts in workbench:
   - [crmapp/spring-client-web-app-database.sql](https://github.com/Ninja-Cyborg/spring-security-crud-webapp/blob/0d5fbb7158b32f095c9f99b64fc0f439dcec0911/crmapp/spring-client-web-app-database.sql)
   - [crmapp/spring-security-bcrypt-database.sql](https://github.com/Ninja-Cyborg/spring-security-crud-webapp/blob/0d5fbb7158b32f095c9f99b64fc0f439dcec0911/crmapp/spring-security-bcrypt-database.sql)
2. Install Apache Tomcat 9
3. Database access:
   databases are setup through properties files,  
   - /crmapp/src/main/resources/persistence-mysql.properties       Hiberate, client database and pool setup
   - /crmapp/src/main/resources/security-persistence-mysql.properties   security database and pool setup
   -  
# Running Application:
- Run application on server
- Open http://localhost:[default-port]/ in local browser
- Enter login credentials to directed page,
  demo credentials: ( username: meryl )    (password: user123)

# Application view
## HomePage (displaying username, roles, and role based Actions) 
![image](https://user-images.githubusercontent.com/66517017/200060594-75cf07ed-5154-4478-a0a7-1f47f89bc4fb.png)

## Client Sorting Function (embeded in table headers) 
![image](https://user-images.githubusercontent.com/66517017/200060758-dad0d4c3-b95e-4c35-817a-137c276db70e.png)

# Links
- Tomcat 9: https://tomcat.apache.org/download-90.cgi
- BCrypt password generation tool: https://bcrypt.online/
- Mock data website: https://www.mockaroo.com/

