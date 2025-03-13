## Temperature Setting API
### Get all temperature Setting API
#### API: localhost:8080/api/temperaturesettings/automated
#### API: localhost:8080/api/temperaturesettings/manual
#### API: localhost:8080/api/temperaturesettings/scheduled
function: lấy danh sách các temperature setting
Request format:
method: GET
body: None
Response format:
{
    List<TemperatureSetting>
}

### Get temperature Setting by farm id API
#### API: localhost:8080/api/temperaturesettings/automated/farm/{farm_id}
#### API: localhost:8080/api/temperaturesettings/manual/farm/{farm_id}
#### API: localhost:8080/api/temperaturesettings/scheduled/farm/{farm_id}
function: lấy temperature setting bằng farm id
Request format:
method: GET
body: None
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

### Get temperature Setting by id API
#### API: localhost:8080/api/temperaturesettings/automated/{id}
#### API: localhost:8080/api/temperaturesettings/manual/{id}
#### API: localhost:8080/api/temperaturesettings/scheduled/{id}
function: lấy temperature setting bằng id
Request format:
method: GET
body: None
Response format:
{
    message: String
    teperatureSetting: ? extends TemperatureSetting
    mode: String
}

### Add a temperature Setting
#### API: localhost:8080/api/temperaturesettings/automated
function: add temperature setting loại automated
Request format:
method: POST
body: 
{
    farm:{
        id: int
    }
    sendWarning: ON/OFF
    lower: int
    upper: int
}
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

#### API: localhost:8080/api/temperaturesettings/manual
function: add temperature setting loại manual
Request format:
method: POST
body:
{
    farm:{
        id: int
    }
    sunShade: ON/OFF
}
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

#### API: localhost:8080/api/temperaturesettings/scheduled
function: add temperature setting loại scheduled
Request format:
method: POST
body:
{
    farm:{
        id: int
    }
    sendWarning: ON/OFF
}
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

### Update Temperature Setting
#### API: localhost:8080/api/temperaturesettings/automated/{id}
function: update temperature setting loại automated
Request format:
method: PUT
body:
{
    farm:{
        id: int
    }
    sendWarning: ON/OFF
    lower: int
    upper: int
}
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

#### API: localhost:8080/api/temperaturesettings/manual/{id}
function: update temperature setting loại manual
Request format:
method: PUT
body:
{
    farm:{
        id: int
    }
    sunShade: ON/OFF
}
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

#### API: localhost:8080/api/temperaturesettings/scheduled/{id}
function: update temperature setting loại scheduled
Request format:
method: PUT
body:
{
    farm:{
        id: int
    }
    sendWarning: ON/OFF
}
Response format:
{
    message: String
    mode: String
    teperatureSetting: ? extends TemperatureSetting
}

### Delete temperature Setting by id API
#### API: localhost:8080/api/temperaturesettings/automated/{id}
#### API: localhost:8080/api/temperaturesettings/manual/{id}
#### API: localhost:8080/api/temperaturesettings/scheduled/{id}
function: xóa temperature setting bằng id
Request format:
method: DELETE
body: None
Response format:
{
    message: String
    teperatureSetting: ? extends TemperatureSetting
    mode: String
}
