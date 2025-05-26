import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.Directives.{complete, path}
import akka.stream.Materializer

import scala.concurrent.ExecutionContextExecutor

object HelloWorldServer {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("hello-system")
    implicit val materializer: Materializer = Materializer(system)
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val route =
      path("hello") {
        Directives.get {
          complete("Hello, World!")
        }
      }

    val bindingFuture = Http().newServerAt("0.0.0.0", 8080).bind(route)

    println("Server online at http://localhost:8080/hello")
    sys.addShutdownHook {
      bindingFuture
        .flatMap(_.unbind())
        .onComplete(_ => system.terminate())
    }
  }
}
