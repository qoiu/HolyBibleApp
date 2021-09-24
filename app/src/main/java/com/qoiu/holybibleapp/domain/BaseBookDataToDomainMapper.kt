package com.qoiu.holybibleapp.domain

class BaseBookDataToDomainMapper: BookDataToDomainMapper {
    override fun map(id: Int, name: String): BookDomain = BookDomain.Base(id,name)
}