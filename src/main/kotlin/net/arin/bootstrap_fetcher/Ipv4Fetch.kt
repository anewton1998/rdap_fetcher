package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "IPv4 Fetch", value = ["/_fetch_ipv4"])
class Ipv4Fetch : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        fetchIana( "http://data.iana.org/rdap/ipv4.json", "ipv4.json" )
    }
}