package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterId
import com.qoiu.holybibleapp.presentation.chapter.ChapterUi

interface ChapterDomainToUiMapper: Abstract.Mapper.Data<ChapterId,ChapterUi>