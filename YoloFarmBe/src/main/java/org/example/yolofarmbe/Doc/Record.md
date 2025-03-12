## Record API
### Get all record of all farm API
#### API: localhost:8080/api/records/amountofwater
#### API: localhost:8080/api/records/moisture
#### API: localhost:8080/api/records/light
#### API: localhost:8080/api/records/humidity
#### API: localhost:8080/api/records/temperature
function: lấy tất cả record (của một loại cụ thể) của tất cả farm
Request format:
method: GET
body: None
Response format:
{
    List< T extends Record>
}

### Get all record of a farm API
#### API: localhost:8080/api/records/amountofwater/{farm_id}
#### API: localhost:8080/api/records/moisture/{farm_id}
#### API: localhost:8080/api/records/light/{farm_id}
#### API: localhost:8080/api/records/humidity/{farm_id}
#### API: localhost:8080/api/records/temperature/{farm_id}
function: lấy tất cả record (của một loại cụ thể) của một farm cụ thể
Request format:
method: GET
body: None
Response format:
{
    List< T extends Record>
}

### Delete a record API
#### API: localhost:8080/api/records/amountofwater/{record_id}
#### API: localhost:8080/api/records/moisture/{record_id}
#### API: localhost:8080/api/records/light/{record_id}
#### API: localhost:8080/api/records/humidity/{record_id}
#### API: localhost:8080/api/records/temperature/{record_id}
function: xóa 1 record
Request format:
method: DELETE
body: None
Response format:
{
    message: String
}