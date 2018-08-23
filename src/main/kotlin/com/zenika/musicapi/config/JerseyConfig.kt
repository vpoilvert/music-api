package com.zenika.musicapi.config

import com.zenika.musicapi.feature.AlbumResource
import com.zenika.musicapi.feature.ArtistAlbumResource
import com.zenika.musicapi.feature.ArtistResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig() : ResourceConfig() {
    init {
        register(ArtistResource())
        register(AlbumResource())
        register(ArtistAlbumResource())
    }
}