package net.arin.bootstrap_fetcher

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "ASN Fetch", value = ["/_fetch_asn"])
class AsnFetch : HttpServlet() {
    private val ianaUrl = "http://data.iana.org/rdap/asn.json"

    private val gcsName = "asn.json"

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        servletContext.log( "Fetching ${ianaUrl} to ${gcsName}")
        fetchIana(ianaUrl, gcsName)
        servletContext.log( "Fetch complete of ${ianaUrl} to ${gcsName}")
    }
}