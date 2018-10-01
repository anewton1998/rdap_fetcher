package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "IPv6 Fetch", value = ["/_fetch_ipv6"])
class Ipv6Fetch : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        fetchIana( "http://data.iana.org/rdap/ipv6.json", "ipv6.json" )
    }
}