package com.zenika.musicapi

import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.zenika.musicapi.feature.Album
import com.zenika.musicapi.feature.Artist
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class ContentParser {

    fun openFile(file: String) : InputStream {
        val fileUrl = this.javaClass.getResource("/raw_artists.csv")
        fileUrl ?: throw IllegalArgumentException("file $file not found")
        return fileUrl.openStream()
    }

    fun parseArtists() : List<Artist> {
        val artists = mutableListOf<Artist>()

        openFile("/raw_artists.csv").use {
            val parser = CSVReader(InputStreamReader(it))
            parser.readNext() // skip header
            do {
                val line = parser.readNext()?.map { checkNotNull(it) } ?: break
                val (a) = line

                artists.add(Artist(
                        id = line[0].toLong(),
                        activeYearBegin = line[1].let { if(it.length > 0) it.toInt() else null },
                        activeYearEnd = line[2].let { if(it.length > 0) it.toInt() else null },
                        imageFile = line[3],
                        location = line[4],
                        name = line[5],
                        url = line[6],
                        tags = listOf()
                ))

            } while(true)

            return artists.toList()
        }
    }

    fun parseAlbums() : List<Album> {
        val albums = mutableListOf<Album>()

        openFile("/raw_albums.csv").use {
            val parser = CSVReader(InputStreamReader(it))
            parser.readNext() // skip header
            do {
                val line = parser.readNext()?.map { checkNotNull(it) } ?: break
                val (a) = line

                albums.add(Album(
                        id = line[0].toLong(),
                        releaseDate = parseDate(line[1]),
                        imageFile = line[2],
                        producer = line[3],
                        title = line[4],
                        tracks = line[5],
                        artistUrl = line[6],
                        tags = listOf()
                ))

            } while(true)

            return albums.toList()
        }
    }

    fun parseDate(str: String) : LocalDate? {
        try {
            println("parse date $str")
            return LocalDate.parse(str, DateTimeFormatter.ofPattern("MM/dd/yyyy"))

        } catch(e :DateTimeParseException) {
            return null
        }
    }
}