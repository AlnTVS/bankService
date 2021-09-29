# bankService
Simple bank service. Created for practical learning how to work with kafka, swagger, spring framework and some others. 
Include web and transaction service. H2(in memory) db(postgresql) for each microservice, initilize by flyway. Sirvices use apache kafka to init and approve transaction.
Transaction service has swagger. HTML templates are processed thymeleaf

Steps to start up:
1. Start up kafka.
  1.1 create two topics: "TransactionA" and "TransactionB"
  1.2 bootstarp-server should work on port 29092
2. Deploy web-service and transaction-service on tomcat.
3. Go to http://*TOMCATHOST:TOMCATPORT*/bank/main
Explore it! 
