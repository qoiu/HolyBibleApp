package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.verses.VerseData

abstract class VersesDataToDomainMapper:
    Abstract.Mapper.DataToDomain.Base<List<VerseData>, VersesDomain>()
