## Scheduler API
### Get scheduler API
#### API: localhost:8080/api/schedulers/daily
#### API: localhost:8080/api/schedulers/weekly
#### API: localhost:8080/api/schedulers/monthly
function: lấy danh sách các scheduler
Request format:
method: GET
body: None
Response format:
{
    List<Scheduler>
}

### Get scheduler API by id
#### API: localhost:8080/api/schedulers/daily/{id}
#### API: localhost:8080/api/schedulers/weekly/{id}
#### API: localhost:8080/api/schedulers/monthly/{id}
function: lấy danh sách các scheduler
Request format:
method: GET
body: None
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}

### Add a new scheduler API
#### API: localhost:8080/api/schedulers/daily
function: thêm 1 scheduler loại daily
Request format:
method: POST
body: 
{
    duration: long
    time: hh:mm:ss
}
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}

#### API: localhost:8080/api/schedulers/weekly
function: thêm 1 scheduler loại weekly
Request format:
method: POST
body:
{
    duration: long
    time: hh:mm:ss
    dayOfWeeks: ["MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"]
}
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}
#### API: localhost:8080/api/schedulers/monthly
function: thêm 1 scheduler loại monthly
Request format:
method: POST
body:
{
    duration: long
    time: hh:mm:ss
    "days": ["yyyy-mm--dd"]
}
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}
### Update  a  scheduler API
#### API: localhost:8080/api/schedulers/daily/{id}
function: thêm 1 scheduler loại daily
Request format:
method: PUT
body:
{
    duration: long
    time: hh:mm:ss
}
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}

#### API: localhost:8080/api/schedulers/weekly/{id}
function: thêm 1 scheduler loại weekly
Request format:
method: PUT
body:
{
    duration: long
    time: hh:mm:ss
    dayOfWeeks: ["MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"]
}
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}
#### API: localhost:8080/api/schedulers/monthly/{id}
function: thêm 1 scheduler loại monthly
Request format:
method: PUT
body:
{
    duration: long
    time: hh:mm:ss
    "days": ["yyyy-mm--dd"]
}
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}

### Delete a scheduler
#### API: localhost:8080/api/schedulers/daily/{id}
#### API: localhost:8080/api/schedulers/weekly/{id}
#### API: localhost:8080/api/schedulers/monthly/{id}
function: lấy danh sách các scheduler
Request format:
method: DELETE
body: None
Response format:
{
    message: String ,
    scheduler: ? extends Scheduler
}
