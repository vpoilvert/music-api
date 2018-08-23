package com.zenika.musicapi.feature

import com.zenika.musicapi.ContentParser
import com.zenika.musicapi.dao.DataObject
import com.zenika.musicapi.dao.DataObjectCollection
import com.zenika.musicapi.dao.paginate
import com.zenika.musicapi.util.rest.PageDto
import com.zenika.musicapi.util.rest.PaginationDto
import org.springframework.stereotype.Component
import org.springframework.ui.Model
import java.time.LocalDate
import java.util.concurrent.locks.ReentrantLock
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import kotlin.concurrent.withLock

data class Album (
        override val id: Long,
        val releaseDate: LocalDate?,
        val imageFile: String,
        val producer: String,
        val title: String,
        val tracks: String,
        val artistUrl: String,
        val tags: List<String>
) : DataObject

object AlbumCollection : DataObjectCollection<Album>(ContentParser().parseAlbums()) {

}

@Path("/album")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
class AlbumResource {

    @GET
    @Path("/{id}")
    fun getAlbum(@PathParam("id") id: Long) : Album? {
        return AlbumCollection.collection.firstOrNull { it.id == id }
    }

    @GET
    fun getAlbums(@BeanParam pagination: PaginationDto): PageDto<Album> {
        return AlbumCollection.collection.paginate(pagination)
    }

    @POST
    fun createAlbum(album: Album): Long? {
        val id = AlbumCollection.collection.maxBy { it.id }?.id ?: 1
        AlbumCollection.add(album.copy(id = id))
        return id
    }

    @POST
    @Path("/{id}")
    fun updateAlbum(@PathParam("id") id: Long, dto: Album) {
        val (index, album) = AlbumCollection.collection.withIndex().first { it.value.id == id }
        AlbumCollection.set(index, dto.copy(id = id))
    }

    @DELETE
    @Path("/{id}")
    fun deleteAlbum(@PathParam("id") id: Long) {
        val index = AlbumCollection.collection.withIndex().first { it.value.id == id }.index
        AlbumCollection.delete(index)
    }
}