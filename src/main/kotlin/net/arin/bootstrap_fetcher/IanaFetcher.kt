/*
 * Copyright (C) 2018 American Registry for Internet Numbers (ARIN)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */
package net.arin.bootstrap_fetcher

import com.google.auth.appengine.AppEngineCredentials
import com.google.cloud.storage.StorageOptions
import java.net.URL


private val refreshHeader = "x-refresh"

/**
 * Function that does fetching of an IANA file.
 */
fun fetchIana( ianaUrl: String, gcsName : String ) {

    val bucketName : String? = System.getProperty( BUCKET_NAME_PROPERTY )

    bucketName?.let{

        val url = URL( ianaUrl )

        val credentials = AppEngineCredentials.getApplicationDefault()
        val storage = StorageOptions.newBuilder().setCredentials(credentials).build().service
        val bucket = storage.get( bucketName )
        bucket.create( gcsName, url.openStream(), "application/json" )

    } ?: run {
        throw RuntimeException( "environment variable ${BUCKET_NAME_PROPERTY} not set" )
    }
}
