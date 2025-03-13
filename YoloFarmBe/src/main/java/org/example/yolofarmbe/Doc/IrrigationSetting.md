## Irrigation Setting API
### Get All Irrigation Setting
#### API: localhost:8080/api/irrigationsettings/automated
#### API: localhost:8080/api/irrigationsettings/manual
#### API: localhost:8080/api/irrigationsettings/scheduled
function: lấy tất cả irrigation setting
Request format:
method: GET
body: None
Response format:
{
    List< Irrigation Setting >
}

### Get Irrigation Setting by farm
#### API: localhost:8080/api/irrigationsettings/automated/farm/{farm_id}
#### API: localhost:8080/api/irrigationsettings/manual/farm/{farm_id}
#### API: localhost:8080/api/irrigationsettings/scheduled/farm/{farm_id}
function: lấy tất cả irrigation setting bằng farm
Request format:
method: GET
body: None
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

### Get Irrigation Setting by id
#### API: localhost:8080/api/irrigationsettings/automated/{id}
#### API: localhost:8080/api/irrigationsettings/manual/{id}
#### API: localhost:8080/api/irrigationsettings/scheduled/{id}
function: lấy tất cả irrigation setting bằng id
Request format:
method: GET
body: None
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

### Add Irrigation Setting 
#### API: localhost:8080/api/irrigationsettings/automated
function: thêm irrigation setting loại automated
Request format:
method: POST
body: 
{   
    farm:{
        id: int
    }
    moistureLevel: String
    dangerSafeBehavior: String
}
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

#### API: localhost:8080/api/irrigationsettings/manual
function: thêm irrigation setting loại manual
Request format:
method: POST
body:
{   
    farm:{
        id: int
    }
    watering: ON/OFF
}
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

#### API: localhost:8080/api/irrigationsettings/scheduled
function: thêm irrigation setting loại scheduled
Request format:
method: POST
body:
{   
    farm:{
        id: int
    }
    dangerSafeBehavior: String
}
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

### Update Irrigation Setting
#### API: localhost:8080/api/irrigationsettings/automated/{id}
function: update irrigation setting loại automated
Request format:
method: PUT
body:
{   
    farm:{
    id: int
    }
    moistureLevel: String
    dangerSafeBehavior: String
}
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

#### API: localhost:8080/api/irrigationsettings/manual/{id}
function: update irrigation setting loại manual
Request format:
method: PUT
body:
{   
    farm:{
        id: int
    }
    watering: ON/OFF
}
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

#### API: localhost:8080/api/irrigationsettings/scheduled/{id}
function: update irrigation setting loại scheduled
Request format:
method: PUT
body:
{   
    farm:{
        id: int
    }
    dangerSafeBehavior: String
}
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}

### Delete Irrigation Setting
#### API: localhost:8080/api/irrigationsettings/automated/{id}
#### API: localhost:8080/api/irrigationsettings/manual/{id}
#### API: localhost:8080/api/irrigationsettings/scheduled/{id}
function: xóa irrigation setting bằng id
Request format:
method: DELETE
body: None
Response format:
{
    message: String
    mode: String
    irrigationSetting: ? extends IrrigationSetting
}