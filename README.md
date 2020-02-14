# contest-api

## Running...

1. Install bower via npm. 
1. Run:

    ```
    ./gradlew build
    bower install
    ./gradlew bootRun
    ```

1. Open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Postman collection

To use the postman collection, add an environme with two variables: 

```
contests-api-host: {{ URL to the API }}
contest: {{ contest ID }}
```

Important operations to host a contest are:

* `bulkLoad`: load everything (participants, groups, categories) into the server
* `listShowsusingget`: get the passwords of the participants
* `startVotationsusingput`: start the votation period
* `stopVotationsusingdelete`: stop the votation period
* `getContestusingget`: get the results of the contest
