package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "DNS Fetch", value = ["/_fetch_dns"])
class DnsFetch : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        fetchIana( "http://data.iana.org/rdap/dns.json", "dns.json" )
    }
}