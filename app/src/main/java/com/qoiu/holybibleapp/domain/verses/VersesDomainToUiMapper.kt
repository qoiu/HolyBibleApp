package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.presentation.verses.VersesUi

abstract class VersesDomainToUiMapper(resourceProvider: ResourceProvider):
    Abstract.Mapper.DomainToUi.Base<List<VerseDomain>, VersesUi>(resourceProvider) {

    }