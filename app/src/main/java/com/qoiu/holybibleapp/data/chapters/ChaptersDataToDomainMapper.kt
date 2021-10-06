package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.chapter.ChaptersDomain

abstract class ChaptersDataToDomainMapper:
    Abstract.Mapper.DataToDomain.Base<List<ChapterData>, ChaptersDomain>()