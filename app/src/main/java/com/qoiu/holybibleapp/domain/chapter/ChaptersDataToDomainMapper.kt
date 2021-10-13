package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterData

abstract class ChaptersDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<ChapterData>, ChaptersDomain>() {

}