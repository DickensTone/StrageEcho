# Echo
Echo come from a demo of Netty's File Transport server.  
Now, it is under casual developing.Because, I'm learning Netty. if you have something interesting about this demo, write it in issues.

This project is under slow develop.I need to learn the knowledge to complete it.
Process:5%. :)
### Introduction
Echo is a file server, that could send file from client to server.Of course, client can get the file from server.

### Plan to use Nacos as config center.

[nacos Official documents ](https://nacos.io/)

### TODO List
+ Tools
  - [X] create a tool to encode the file. 
+ complete transport
  - [ ] define some transaction
    - the start and end symbol
    - the code format
  - [X] transport file content
  - [ ] choose transport mode
+ add configCenter<BR>
  - [ ] use configCenter to publish server address.
  - [ ] client can get the address
+ add ORM Framework
  - [x] add Spring Data JPA
  - [ ] consider and realize the data's consistency
+ DataBase
  - [ ] readonly query database
  - [ ] real time database backup
+ add log framework

