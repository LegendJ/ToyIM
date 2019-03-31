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

## code 
