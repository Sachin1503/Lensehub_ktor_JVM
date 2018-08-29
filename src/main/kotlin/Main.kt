import db.DBConnection
import  io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.receiveText
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import models.User

fun main(args: Array<String>) {

    val application = Application()
    application.init()

    val server = embeddedServer(Netty, port = 8780) {
        routing {
            get("/") {
                call.respondText("Hello World! from get api", ContentType.Any)
            }
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }
            post("/setUser") {
                val userName = call.receiveText()
                application.userDataSource?.insert(userName)
                call.respondText { "post executed" }
            }
        }
    }
    server.start(wait = true)
}
