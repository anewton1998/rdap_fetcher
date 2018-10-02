package net.arin.bootstrap_fetcher

import java.lang.RuntimeException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Hello", value = ["/hello"])
class HelloWorld : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        res.writer.write("Hello, World! I am a Servlet 3.1 running on Java8 App Engine Standard, and written in Kotlin...")
        req.parameterMap.forEach{ param ->
            param.value.forEach { pValue ->
                res.writer.write( "\nrequest parameter ${param.key} = ${pValue}")
                if( pValue.equals( "blowedUp" ) ) {
                    throw RuntimeException( "this blowed up real goode!" )
                }
            }
        }
    }
}