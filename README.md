# ToyIM
> Toy IM project as netease homework
## Getting started
### Using docker to deploy this service

> recommend using docker 

- first, install [docker](https://www.docker.com/),my version is 
```shell
docker --version
> Docker version 18.09.4, build d14af54266
```
- cd parent directory of the project and run the command below
```shell
docker-compose up
```
- you can also build your docker image separately by specifying dockerfile under the parent dir

- now u can access service page through http://localhost:8081,btw,u may need to shutdown your mysql service first or close your
3306 port if being used. 

### play the demo

- because of limited time and focusing on im system design instead of business logic ,all the users are pre-registered,
below are user information u can use to play the demo(still some bugs maybe :< )
    - role username : password
    - Administrator oracle : oracle
    - user just0:123456,just1:123456,just2:123456,....,just8:123456
