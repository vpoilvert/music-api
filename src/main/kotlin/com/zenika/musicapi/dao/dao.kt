package com.zenika.musicapi.dao

import com.zenika.musicapi.feature.AlbumCollection
import com.zenika.musicapi.util.rest.PageDto
import com.zenika.musicapi.util.rest.PaginationDto
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

interface DataObject {
    val id : Long
}

abstract class DataObjectCollection<T : DataObject> (collection : List<T>) {
    val lock = ReentrantLock()
    var collection: List<T> = collection
        private set

    fun add(album: T) = lock.withLock {
        collection = collection.toMutableList().apply { this.add(album) }.toList()
    }
    fun set(index: Int, album: T) = lock.withLock {
        collection = collection.toMutableList().apply { this[index] = album }.toList()
    }

    fun delete(index: Int) = lock.withLock {
        collection = collection.toMutableList().apply { this.removeAt(index) }.toList()
    }
}

fun <T : Any> List<T>.paginate(pagiation: PaginationDto) : PageDto<T> {
    val page = Math.max(0, pagiation.page)
    val size = Math.max(1, pagiation.size)
    val start = page * size
    val list = this.drop(page * size).take(size)

    val totalElements = this.size.toLong()
    val totalPages = Math.round(totalElements.toFloat() / size.toFloat())
    return PageDto(list, totalPages, totalElements)
}