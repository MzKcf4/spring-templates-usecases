# Spring Async with SecurityContext and MDC delegation example

This project shows an example with Async processing from rest call , with options to preserve MDC and SecurityContext.

1. Start the project , find the following line in console : 
```
Using generated security password: xxxxxxxxxxxxxxxxxx
```

2.	Go to http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ , enter following for login credential
```
user : user
password : [%The password copied%]
```

3.	Test the async calls
