## Intergration Setting API
### Get All Intergration Setting
#### API: localhost:8080/api/intergrationsettings
function: lấy tất cả intergration setting 
Request format:
method: GET
body: None
Response format:
{
    List< Intergration Setting >
}

### Get Intergration Setting by farm id
#### API: localhost:8080/api/intergrationsettings/farm/{id}
function: lấy intergration setting bằng farm id
Request format:
method: GET
body: None
Response format:
{
    message : String
    intergrationSetting: IntergrationSetting
}

### Get All Intergration Setting by id
#### API: localhost:8080/api/intergrationsettings/{id}
function: lấy tất cả intergration setting băng intergration setting id
Request format:
method: GET
body: None
Response format:
{
    message : String
    intergrationSetting: IntergrationSetting
}

### Add a new Intergration Setting 
#### API: localhost:8080/api/intergrationsettings
function: Thêm 1 intergration Setting
Request format:
method: POST
body: 
{
    soilSensor: ON/OFF
    weatherApi: ON/OFF
    predict: ON/OFF
    farm: {
        id: Int
    }
}
Response format:
{
    message : String
    intergrationSetting: IntergrationSetting
}

### Update a Intergration Setting
#### API: localhost:8080/api/intergrationsettings/{id}
function: Thêm 1 intergration Setting
Request format:
method: POST
body:
{
    soilSensor: ON/OFF
    weatherApi: ON/OFF
    predict: ON/OFF
    farm: {
        id: int
    }
}
Response format:
{
    message : String
    intergrationSetting: IntergrationSetting
}

### Delete a Intergration Setting
#### API: localhost:8080/api/intergrationsettings/{id}
function: Thêm 1 intergration Setting
Request format:
method: POST
body:None
Response format:
{
    message : String
    intergrationSetting: IntergrationSetting
}
