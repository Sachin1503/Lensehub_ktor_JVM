import  io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.receiveText
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8880) {
        routing {
            get("/") {
                call.respondText("Hello World! from get api", ContentType.Any)
            }
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }
            post("postText") {
                System.out.print(call.receiveText())
                call.respondText { "post executed" }
            }
        }
    }
    server.start(wait = true)
}
