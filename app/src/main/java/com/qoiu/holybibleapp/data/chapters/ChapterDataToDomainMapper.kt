package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.chapter.ChapterDomain

interface ChapterDataToDomainMapper : Abstract.Mapper.Data<ChapterId,ChapterDomain>