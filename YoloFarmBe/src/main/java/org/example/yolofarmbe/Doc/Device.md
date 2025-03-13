## Device API
### Get all device API
#### API: localhost:8080/api/devices
function: Lấy danh sách tất cả thiết bị
Request format:
method: GET
body: None
Response format:
{
    List<Device>
}

### Get all device of a farm API
#### API: localhost:8080/api/devices/farm/{farm_id}
function: Lấy danh sách tất cả thiết bị của 1 farm
Request format:
method: GET
body: None
Response format:
{
    List<Device>
}

### Get a device by id API
#### API: localhost:8080/api/devices/{id}
function: Lấy một thiết bị bởi id
Request format:
method: GET
body: None
Response format:
{
    message: String
    device: Device
}

### Add a new device API
#### API: localhost:8080/api/devices
function: Thêm một thiết bị mới
Request format:
method: POST
body: 
{
    name: String
    location: String
    model: String
    type: String
    state: ON/OFF
    farm:{
        id: int
    }
}
Response format:
{
    message: String
    device: Device
}

### Update a device API
#### API: localhost:8080/api/devices/{id}
function: Update thông tin một thiết bị
Request format:
method: PUT
body:
{
    name: String
    location: String
    model: String
    type: String
    state: ON/OFF
    farm:{
        id: int
    }
}
Response format:
{
    message: String
    device: Device
}

### Delete a device API
#### API: localhost:8080/api/devices/{id}
function: Xóa một thiết bị
Request format:
method: PUT
body: None
Response format:
{
    message: String
    device: Device
}