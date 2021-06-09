import {
    Get,
    Post
} from '../../static/js/request.js'

const saveProject = Post("/project/save")
const getAllProject = Get("/project/all")

export {
    saveProject,
	getAllProject
}