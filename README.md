# Introduction

This application serves files using HTTP.

The currently deployed instance serves files at:
https://files.qladstone.com/

# Dev setup

Use JDK 21.

# Running

Run HttpFileServer.main with the following VM arguments:
- app.root: Absolute or relative path to file root directory.
- app.port: optional port number to serve, default is 8080.

Alternatively, build and deploy locally to run locally.

# Build and deploy

To build:
```
mvn clean package
```

To deploy:
```
java -Dapp.root=<root> -Dapp.port=<port> -jar my-http-file-server-*.jar
```
