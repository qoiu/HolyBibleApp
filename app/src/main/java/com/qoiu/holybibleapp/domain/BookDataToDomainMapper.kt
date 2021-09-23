package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract

interface BookDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookDomain
}
