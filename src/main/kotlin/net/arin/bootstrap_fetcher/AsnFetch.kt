package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "ASN Fetch", value = ["/_fetch_asn"])
class AsnFetch : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        fetchIana( "http://data.iana.org/rdap/asn.json", "asn.json" )
    }
}