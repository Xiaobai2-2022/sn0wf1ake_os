# Development Log

## V 0.0._

### Notes

#### Frontend

To start the frontend web app, use command

```shell
npm start
```

#### Backend

To start the backend server, use command

```shell
./mvnw spring-boot:run
```

WebMcvConfigurer is an interface provided by the **Spring Framework** in the **Spring MVC** module.

CORS stands for **Cross-Origin Resource Sharing** it is an HTTP-header base mechanism that allows server to indicate any origins other than its own.

To test the backend server, use command

```shell
curl -X POST -H "Content-Type: application/json" -d '{"command": "FTP", "args": "Test Arguments"}' http://localhost:8080/api/execute
```

---

#### Database

To connect to the MySQL database, use command

```shell
mysql -u root -p
```
