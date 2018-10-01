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

import com.google.cloud.storage.Blob
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.StorageOptions
import java.net.URL
import java.nio.channels.Channels
import java.time.LocalDateTime


private val refreshHeader = "x-refresh"

/**
 * Function that does fetching of an IANA file.
 */
fun fetchIana( ianaUrl: String, gcsName : String ) {

    val bucket : String? = System.getProperty( BUCKET_NAME_PROPERTY )

    bucket?.let{

        val url = URL( ianaUrl )

        val storage = StorageOptions.getDefaultInstance().service
        val blobId = BlobId.of( bucket, gcsName )
        val blob : Blob? = storage.get( blobId )
        blob?.let {
            val writer = blob.writer()
            val os = Channels.newOutputStream( writer )
            url.openStream().copyTo( os )
            blob.toBuilder().setMetadata( hashMapOf( refreshHeader to LocalDateTime.now().toString() ) ).build().update()
        } ?: run {
            storage.create(
                    BlobInfo
                            .newBuilder( bucket, gcsName )
                            .setMetadata(
                                    hashMapOf(
                                            refreshHeader to LocalDateTime.now().toString() )
                            )
                            .setContentType( "application/json" )
                            .build(),
                    url.openStream()
            )
        }

    } ?: run {
        throw RuntimeException( "environment variable ${BUCKET_NAME_PROPERTY} not set" )
    }
}
