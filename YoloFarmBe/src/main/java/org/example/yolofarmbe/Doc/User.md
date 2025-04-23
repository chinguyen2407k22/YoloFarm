## User API
### Get user API
#### API: localhost:8080/api/users/farm/{farm_id}
function: Lấy danh sách tất cả user của một nông trại
Request format:
method: GET
body: None
Response format:
{
    List<User>
}
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
    email: String
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