# Online Store #

* Sample program to calculate total-amount for bought products.

## Prerequisites ##

* Open JDK 11
* Maven 3.6.X
* NPM 6.15.4
* Node 12.16.3

## Environment Setup ##

### DB Setup ###

* Run SQL scripts located in  ```online-store/db-setup/test``` to setup test-db.

* Run SQL scripts located in ```online-store/db-setup``` to setup the db.

## Building and Deployment ##

### API Server Module ###

* Run following command.

```shell script
    mvn clean install
```

* Find the deployable in ```/online-store/api-server/target/api-server```.

* Run following command to start the ```API Server```.

```shell script
    ./bin/api-server start;
``` 

* Run following command to stop the ```API Server```.


```shell script
    ./bin/api-server stop;
``` 

### Online Store Web ###

* Please find the instructions related to web-module deployment in [WEB Readme](./online-store-web/README.md).