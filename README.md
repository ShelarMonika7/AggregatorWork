# Aggregator
## Run Instruction
### Required Tools
    1. Eclipse(STS must be installed from eclipse marketplace)
    2. JAVA 8/11
    3. Maven
    4. Postman
    
### Runing steps
    1. Clone the project from git
    2. Open Eclipse and import project with maven
    3. Right Click on the project and goto maven and Update the project
    4. Right Click on the project select Run As with Spring boot app
    5. You must see Started AggregatorApplication
    6. Application is running now
    7. Goto Postman and import the postman collection from the cloned project directory
    8. Send the multiple request from postman simultaneously and then see the result.

# TNT Digital

### API Aggregation Service
    This aggregation service takes a input for folowing parameters and provide the combined response. but before combining it satisfy the following criteria
    1. it waits for 5 sec to fill the queue with 5 request
    2. when queue is full i.e queue is size of 5
    it request the API of xyzassessent service and fetch the result from it 
    
# API 
      http://localhost:8081/api/aggregation?pricing=NL,CN&track=109347263,123456862&shipments=109347263,123456862
    
### Queue handling
        When first request comes with the parameter two 
        for example: http://localhost:8081/api/aggregation?pricing=NL,CN&track=109347261,123456862&shipments=109347261,123456862
        
        queues:
        pricing : [CN,BR]
        track:  [109347261,123456862]
        shipments: [109347261,123456862]
        
        in above example we can see we have recieved only two parameters. so in this case the service will wait until another request. and fill the queue with 
        parameters. consider we have sent another request.
        
        for example: http://localhost:8081/api/aggregation?pricing=NL,US,AF&track=109347263,123456864&shipments=109347263,123456864
        
        here queue will be 
        pricing : [CN,BR,NL,US,AF]
        track:  [109347261,123456862,109347263]
        shipments: [109347261,123456862,109347263]

        so here the pricing queue has been filled with the 5 request parameters so it will immedietely trigger the API to backend service for response.
        and other two queues will wait for another request parameters or they will wait for 5 secs. and then they will trigger the APIs.
        
        after getting all the responses from the backend service. the responses will get combined and then sent back to respective requests.
        
# Use of postman collection
   We can use the postman collection for api invoking
   in which we have added all the backend api example and aggregator service api
   example
   ### Shipment API:
    http://localhost:8080/shipments?q=109347263,123456891
   ### Pricing API:
    http://localhost:8080/pricing?q=NL,US,AF
   ### Tracking API:
    http://localhost:8080/track?q=109347263,123456891

  ### Aggregator Service API:
    http://localhost:8081/api/aggregation?pricing=NL,US,AF&track=109347263,123456862,109347261&shipments=109347263,123456862,109347261
   This API we can execute multiple number of time. according to the request parameters which we want to send






