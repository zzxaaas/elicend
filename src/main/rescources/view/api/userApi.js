import {
    Get,
    Post
} from '../../static/js/request.js'

const userRegister = Post("/user/register")
const userLogin = Post("/user/login")

export {
    userRegister,
    userLogin
}