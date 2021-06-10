# My personal application

This application is like my personal digital assistant. It will continue to mutate depending upon my needs.
This application is also a kitchen sink where I try various new technologies/ methodologies I get to learn.

Setting up this application in `PROD` mode from scratch requires two steps in order:

#1. Provision resources.
###[terraform](./terraform/README.md)

#2. Run the Spring Boot application

If using spring profile `DEV` , then the appllication runs on a local H2 database, and in `TEST` mode it runs on a in memory H2 database.
