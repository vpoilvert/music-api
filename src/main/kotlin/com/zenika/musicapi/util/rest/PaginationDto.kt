package com.zenika.musicapi.util.rest

import javax.ws.rs.DefaultValue
import javax.ws.rs.QueryParam

/**
 * BeanParam that stores pagination info.
 */
data class PaginationDto(
        @QueryParam("page")
        @DefaultValue("0")
        var page: Int = 0,
        @QueryParam("size")
        @DefaultValue("10")
        var size: Int = 10,
        @QueryParam("sort")
        var sort: String? = null
)