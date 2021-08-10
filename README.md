# spring-boot-simple-api
a sample project for learning spring boot
up to now there are 3 entitites: post, tag & category <br>
for using the apis, follow the structure below
## POST
send a request to /api/posts or /api/tags or /api/categories
in the body of request put the required fields as json
## GET
you can get all the objects with /api/posts or a single object with /api/posts/{id}
## PUT
you have to pass the id as query parameter, like /api/posts?id=1
then in the body write the fields you want to update as json
## DELETE
send the request containg id, like /api/posts/{id}
