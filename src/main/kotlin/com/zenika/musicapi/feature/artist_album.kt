package com.zenika.musicapi.feature

import com.zenika.musicapi.dao.paginate
import com.zenika.musicapi.util.rest.PageDto
import com.zenika.musicapi.util.rest.PaginationDto
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/artist/{id}/album")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
class ArtistAlbumResource {

    @GET
    fun getAlbums(@PathParam("id") id: Long, @BeanParam pagination: PaginationDto): PageDto<Album> {
        val artist = ArtistCollection.collection.first { it.id == id }

        return AlbumCollection.collection
                .filter { it.artistUrl == artist.url }
                .paginate(pagination)
    }
}