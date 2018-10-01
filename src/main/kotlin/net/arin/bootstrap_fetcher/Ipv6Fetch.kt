package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "IPv6 Fetch", value = ["/_fetch_ipv6"])
class Ipv6Fetch : HttpServlet() {
    private val ianaUrl = "http://data.iana.org/rdap/ipv6.json"

    private val gcsName = "ipv6.json"

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        servletContext.log( "Fetching ${ianaUrl} to ${gcsName}")
        fetchIana(ianaUrl, gcsName)
        servletContext.log( "Fetch complete of ${ianaUrl} to ${gcsName}")
    }
}