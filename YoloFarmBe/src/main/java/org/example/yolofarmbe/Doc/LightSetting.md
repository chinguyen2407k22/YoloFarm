## Light Setting API
### Get All Light Setting API
#### API: localhost:8080/api/lightsettings/automated
#### API: localhost:8080/api/lightsettings/manual
#### API: localhost:8080/api/lightsettings/scheduled
function: Lấy danh sách tất cả light settings
Request format:
method: GET
body: None
Response format:
{
    message: String 
    mode: String 
    lightSetting: ? extends LightSetting
}

### Get A Light Setting by Farm Id
#### API: localhost:8080/api/lightsettings/farm/automated/{farm_id}
#### API: localhost:8080/api/lightsettings/farm/manual/{farm_id}
#### API: localhost:8080/api/lightsettings/farm/scheduled/{farm_id}
function: Lấy lightsetting bằng farm id
Request format:
method: GET
body: None
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}

### Get A Light Setting by Farm Id
#### API: localhost:8080/api/lightsettings/automated/{farm_id}
#### API: localhost:8080/api/lightsettings/manual/{farm_id}
#### API: localhost:8080/api/lightsettings/scheduled/{farm_id}
function: Lấy lightsetting bằng farm id
Request format:
method: GET
body: None
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}

### Get A Light Setting by Id
#### API: localhost:8080/api/lightsettings/automated/{id}
#### API: localhost:8080/api/lightsettings/manual/{id}
#### API: localhost:8080/api/lightsettings/scheduled/{id}
function: Lấy lightsetting bằng id
Request format:
method: GET
body: None
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}

### Add a Light Setting
#### API: localhost:8080/api/lightsettings/automated
function: Add lightsetting có mode là automated
Request format:
method: POST
body: 
{
    farm:{
        id: int
    }
    min: int
    max: int
    upper: int
    lower: int
    sendWarning: ON/OFF
    
}
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}
#### API: localhost:8080/api/lightsettings/manual
function: Add lightsetting có mode là manual
Request format:
method: POST
body:
{
    farm:{
        id: int
    }
    turnOn: ON/OFF
}
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}
#### API: localhost:8080/api/lightsettings/scheduled
function: Add lightsetting có mode là manual
Request format:
method: POST
body:
{
    farm:{
        id: int
    }
    sendWarning: ON/OFF
    scheduler:{
        id: int
        type: daily/weekly/montly
    }
}
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}

### Update Light Setting
#### API: localhost:8080/api/lightsettings/automated/{id}
function: Update lightsetting có mode là automated
Request format:
method: PUT
body:
{
    farm:{
        id: int
    }
    min: int
    max: int
    upper: int
    lower: int
    sendWarning: ON/OFF
}
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}
#### API: localhost:8080/api/lightsettings/manual/{id}
function: Update lightsetting có mode là manual
Request format:
method: PUT
body:
{
    farm:{
        id: int
    }
    turnOn: ON/OFF
}
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}
#### API: localhost:8080/api/lightsettings/scheduled/{id}
function: Update lightsetting có mode là manual
Request format:
method: PUT
body:
{
    farm:{
        id: int 
    }
    sendWarning: ON/OFF
    scheduler:{
    id: int
    type: daily/weekly/montly
}
}
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}

### Delete a Light Setting
#### API: localhost:8080/api/lightsettings/farm/automated/{id}
#### API: localhost:8080/api/lightsettings/farm/manual/{id}
#### API: localhost:8080/api/lightsettings/farm/scheduled/{id}
function: Lấy lightsetting bằng farm id
Request format:
method: DELETE
body: None
Response format:
{
    message: String
    mode: String
    lightSetting:  ? extends LightSetting
}