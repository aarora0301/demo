#### FLIGHT BOOKING SERVICE


##### API Specification


1. Schedule Flight <br/>

 URL : `http://localhost:8085/api/scheduleFlight` <br/>
 Method: POST <br/>
 Request Payload: <br/>
`{
 "flightNumber":1,
 "departureTime":"13-4-2015 10:59:26",
 "noOfSeats": 10
 }`
 
 Response Payload : <br/>
 `{
      "status": "Success",
      "message": null
  }`
  
  ###### Assumptions
  
- Flight Number i.e flight instance should pre exist in database so that it can be scheduled
 (for testing purpose flightNumber : 1 & 2) can be used as Id.
 
-  create entry in [data.sql](src/main/resources/data.sql) to create flight instance.


2. Book Seat <br/>
 URL : `http://localhost:8085/api/bookSeat` <br/>
 Method: POST <br/>
 Request Payload: <br/>
 `"flightNumber": 1,
  "userName": "aarshi99"
  }`
  
  Response Payload : <br/>
  `{
       "status": "Success",
       "message": "",
       "seatId": 3
   }`
 
 3. Get Available Seats <br/>
 
 URL: `http://localhost:8085/api/getAvailableSeats?flightNumber=1` <br/>
 Response Payload : <br/>
 `{
      "status": "Success",
      "message": "",
      "count": 7
  }`
 
