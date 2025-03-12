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
    email: String
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