import application.LHApplication
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import models.Item
import models.User
import wsmodels.CategoryRequestModel
import wsmodels.UserRequestModel

fun main(args: Array<String>) {
    startServer()
}

private fun startServer() {

    val application = LHApplication()
    val gson = Gson()

    val server = embeddedServer(Netty, port = 8780) {
        routing {

            post("/createUser") {
                val user = gson.fromJson(call.receiveText(), User::class.java)
                application.userDataSource?.create(user)
                call.respondText { "User Created" }
            }
            post("/createItem"){
                val item = gson.fromJson(call.receiveText(),Item::class.java)
                application.itemDataSource?.create(item)
                call.respondText { "Item created" }
            }
            post("/updateUser") {
                val user = gson.fromJson(call.receiveText(), User::class.java)
                application.userDataSource?.update(user)
                call.respondText { "User Updated" }
            }
            post("/updateItem") {
                val item = gson.fromJson(call.receiveText(), Item::class.java)
                application.itemDataSource?.update(item)
                call.respondText { "Item Updated" }
            }
            post("/deleteUser") {
                val user = gson.fromJson(call.receiveText(), User::class.java)
                application.userDataSource?.delete(user.id)
                call.respondText { "User Deleted" }
            }
            post("/deleteItem") {
                val user = gson.fromJson(call.receiveText(), Item::class.java)
                application.userDataSource?.delete(user.id)
                call.respondText { "User Deleted" }
            }
            post("/getCategories") {
                val categoryRequestModel = gson.fromJson(call.receiveText(), CategoryRequestModel::class.java)
                val categories = application.categoryDataSource?.getCategories(categoryRequestModel.city)
                val categoryJsonString = gson.toJson(categories)
                call.respondText { categoryJsonString }
            }
            get("/getAllUser") {
                val users = application.userDataSource?.getAllUser()
                val jsonString = gson.toJson(users)
                call.respondText(jsonString)
            }
            post("/getUser") {
                val userRequestModel = gson.fromJson(call.receiveText(), UserRequestModel::class.java)
                val user = application.userDataSource?.getUser(userRequestModel.email)
                val jsonString = gson.toJson(user)
                call.respondText(jsonString)
            }
            get("/") {

                call.respondText("Hello")
            }

        }
    }
    server.start(wait = true)
}
