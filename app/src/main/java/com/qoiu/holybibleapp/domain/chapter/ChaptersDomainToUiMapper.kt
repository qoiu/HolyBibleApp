package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.presentation.chapter.ChaptersUi

abstract class ChaptersDomainToUiMapper(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ChapterDomain>, ChaptersUi>(resourceProvider) {
}