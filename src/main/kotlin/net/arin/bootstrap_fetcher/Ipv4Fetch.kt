package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "IPv4 Fetch", value = ["/_fetch_ipv4"])
class Ipv4Fetch : HttpServlet() {
    private val ianaUrl = "http://data.iana.org/rdap/ipv4.json"

    private val gcsName = "ipv4.json"

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        servletContext.log( "Fetching ${ianaUrl} to ${gcsName}")
        fetchIana(ianaUrl, gcsName)
        servletContext.log( "Fetch complete of ${ianaUrl} to ${gcsName}")
    }
}