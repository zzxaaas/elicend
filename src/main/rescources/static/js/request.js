// const baseUrl = "http://localhost:8080"
const baseUrl = "http://159.75.109.38:8080"

const Get = uri => {
	return function(params) {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: baseUrl+uri,
				type: 'get',
				dataType: 'json',
				contentType: 'application/json; charset=UTF-8',
				data: params,
				success: function(msg) {
					resolve(msg)
				},
				error: function(err) {
					reject(err)
				}
			})
		})
	}
}

const Post = uri => {
	return function(params) {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: baseUrl+uri,
				type: 'post',
				dataType: 'json',
				contentType: 'application/json; charset=UTF-8',
				data: JSON.stringify(params),
				success: function(msg) {
					resolve(msg)
				},
				error: function(err) {
					reject(err)
				}
			})
		})
	}
}

export {
	baseUrl,
	Get,
	Post
}