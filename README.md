
# Đồ án Đa ngành - hướng CNPM - nhóm 72

## Authentication API
### Register API
#### API: localhost:8080/api/register
function: Đăng kí một tài khoản mới
Request format:
method: POST
body:
{
    username: String
    password: String
    firstname : String
    lastname: String
    farm: {
        id: int
    }
}
Response format:
{
    message: String ,
    user : User
}

### Login API
#### API: localhost:8080/api/login
function: Đăng nhập
Request format:
method: POST
body:
{
    username: String
    password: String
}
Response format:
{
    message: String ,
    user : User
}
//Nếu đăng nhập tài khoản và mật khẩu không đúng, message sẽ báo lỗi,user = null

##User API
### Get user API
#### API: localhost:8080/api/users/{username}
function: Lấy thông tin của user thông qua username
Request format:
method: GET
body: None
Response format:
{
    message: String ,
    user : User
}
### Update user API
#### API: localhost:8080/api/users/{username}
function: Cập nhật thông tin của user thông qua username
Request format:
method: PUT
body:
{
    (không nhất thiết phải điền đủ các trường, sửa trường nào update mỗi trường đó)
    username: String
    password: String
    firstname : String
    lastname: String
    farm: { 
        id: int
    }
}
Response format:
{
    message: String ,
    user : User (đã update)
}
### Delete user API
#### API: localhost:8080/api/users/{username}
function: xóa user thông qua username
Request format:
method: DELETE
body: None
Response format:
{
    message: String ,
    user : User (vừa xoá)
}

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

## Farm API
### Get All farm API
#### API: localhost:8080/api/farms
function: lấy danh sách tất cả các farm
Request format:
method: GET
body: None
Response format:
{
    List<Farm>
}

### Get a farm API
#### API: localhost:8080/api/farms/{farm_id}
function: lấy một farm bằng farm_id
Request format:
method: GET
body: None
Response format:
{
    message: String
    farm : Farm 
}

### Add a farm API
#### API: localhost:8080/api/farms
function: tạo một farm mới
Request format:
method: POST
body:
{
    farmSize: Double
    crop: String
    farmName: String 
}
Response format:
{
    message: String
    farm : Farm
}

### Update a farm API
#### API: localhost:8080/api/farms/{farm_id}
function: cập nhật thông tin farm
Request format:
method: PUT
body:
{
    (nhập cái nào update cái đó, không cần nhập hết)
    farmSize: Double
    crop: String
    farmName: String
}
Response format:
{
    message: String
    farm : Farm (đã update)
}

### Delete a farm API
#### API: localhost:8080/api/farms/{farm_id}
function: xóa một farm
Request format:
method: DELETE
body: none
Response format:
{
    message: String
    farm : Farm (đã vừa delete)
}










