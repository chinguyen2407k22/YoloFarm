## Activity Log API
### Get All Activity Log API
#### API: localhost:8080/api/activitylogs
function: Lấy danh sách tất cả activity logs
Request format:
method: GET
body: None
Response format:
{
    List<ActivityLog>
}

### Get An activity log by id API
#### API: localhost:8080/api/activitylogs/{id}
function: Lấy activity logs bằng id
Request format:
method: GET
body: None
Response format:
{
    message: String
    activitylog: ActivityLog
}

### Get all activity log of a farm API
#### API: localhost:8080/api/activitylogs/farm/{id}
function: Lấy danh sách activity log của một farm
Request format:
method: GET
body: None
Response format:
{
    List<Activity Log>
}

### Add an activity log API
#### API: localhost:8080/api/activitylogs
function: Thêm 1 activity log
Request format:
method: POST
body:
{
    category: String
    title: String
    mode: String
    logTime: hh:mm:ss
    farm: {
        id: int
    }
}
Response format:
{
    message: String
    activitylog: ActivityLog
}

### update an activity log API
#### API: localhost:8080/api/activitylogs/{id}
function: Update 1 activity log
Request format:
method: PUT
body:
{
    category: String
    title: String
    mode: String
    logTime: hh:mm:ss
    farm: {
        id: int
    }
}
Response format:
{
    message: String
    activitylog: ActivityLog
}

### Delete an activity log API
#### API: localhost:8080/api/activitylogs/{id}
function: Xóa 1 activity log
Request format:
method: DELETE
body: None
Response format:
{
    message: String
    activitylog: ActivityLog
}