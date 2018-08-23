package com.zenika.musicapi.feature

import com.zenika.musicapi.ContentParser
import com.zenika.musicapi.dao.DataObject
import com.zenika.musicapi.dao.DataObjectCollection
import com.zenika.musicapi.dao.paginate
import com.zenika.musicapi.util.rest.PageDto
import com.zenika.musicapi.util.rest.PaginationDto
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import kotlin.concurrent.withLock

data class Artist (
        override val id: Long,
        val activeYearBegin: Int?,
        val activeYearEnd: Int?,
        val imageFile: String,
        val location: String,
        val name: String,
        val url: String,
        val tags: List<String>
) : DataObject

object ArtistCollection : DataObjectCollection<Artist>(ContentParser().parseArtists()) {

}

@Path("/artist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
class ArtistResource {

    @GET
    @Path("/{id}")
    fun getArtist(@PathParam("id") id: Long) : Artist? {
        return ArtistCollection.collection.firstOrNull { it.id == id }
    }

    @GET
    fun getArtists(@BeanParam pagination: PaginationDto): PageDto<Artist> {
        return ArtistCollection.collection.paginate(pagination)
    }

    @POST
    fun createArtist(artist: Artist): Long? {
        val id = ArtistCollection.collection.maxBy { it.id }?.id ?: 1
        ArtistCollection.add(artist.copy(id = id))
        return id
    }

    @POST
    @Path("/{id}")
    fun updateArtist(@PathParam("id") id: Long, dto: Artist) {
        val (index, artist) = ArtistCollection.collection.withIndex().first { it.value.id == id }
        ArtistCollection.set(index, dto.copy(id = id))
    }

    @DELETE
    @Path("/{id}")
    fun deleteArtist(@PathParam("id") id: Long) {
        val index = ArtistCollection.collection.withIndex().first { it.value.id == id }.index
        ArtistCollection.delete(index)
    }
}