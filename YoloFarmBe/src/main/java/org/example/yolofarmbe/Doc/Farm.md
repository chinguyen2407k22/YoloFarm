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
