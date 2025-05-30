## Reminder API
### Get All reminder
#### API: localhost:8080/api/reminders
function: lấy danh sách tất cả các reminder của tất cả các user
Request format:
method: GET
body: none
Response format:
{
    List<Reminders>
}

### Get All reminder of a user
#### API: localhost:8080/api/reminders/user/{username}
function: lấy danh sách tất cả các reminder của một user cụ thể
Request format:
method: GET
body: none
Response format:
{
    List<Reminders>
}

### Get a reminder by id
#### API: localhost:8080/api/reminders/id
function: lấy reminder bằng id
Request format:
method: GET
body:
{
    id: int,
    username: String
}
//Do reminder là thực thể yếu của user nên id gồm id của reminder và username của user
Response format:
{
    message: String
    reminder : Reminder
}

### Add a new reminder
#### API: localhost:8080/api/reminders
function: thêm 1 reminder
Request format:
method: POST
body:
{
    id: {
        id: int,
        username: String
    }
    title: String
    reminderDescription : String
    reminderTime : LocalDateTime
    isDone: Boolean
}
//Do reminder là thực thể yếu của user nên id gồm id của reminder và username của user
Response format:
{
    message: String
    reminder : Reminder
}

### Update a new reminder
#### API: localhost:8080/api/reminders
function: cập nhật một reminder
Request format:
method: PUT
body:
{
    id: {
        id: int,
        username: String
    }
    title: String
    reminderDescription : String
    reminderTime : LocalDateTime
    isDone: Boolean
}
//Do reminder là thực thể yếu của user nên id gồm id của reminder và username của user
Response format:
{
    message: String
    reminder : Reminder
}

### Delete a new reminder
#### API: localhost:8080/api/reminders
function: xóa một reminder
Request format:
method: DELETE
body:
{
    id: int,
    username: String
    
}
//Do reminder là thực thể yếu của user nên id gồm id của reminder và username của user
Response format:
{
    message: String
    reminder : Reminder
}