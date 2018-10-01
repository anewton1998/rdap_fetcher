package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "DNS Fetch", value = ["/_fetch_dns"])
class DnsFetch : HttpServlet() {
    private val ianaUrl = "http://data.iana.org/rdap/dns.json"
    private val gcsName = "dns.json"

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        servletContext.log( "Fetching ${ianaUrl} to ${gcsName}")
        fetchIana(ianaUrl, gcsName)
        servletContext.log( "Fetch complete of ${ianaUrl} to ${gcsName}")
    }
}