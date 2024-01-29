package co.cmedina.marvelcomics.ui.comicdetail

import co.cmedina.marvelcomics.domain.model.Comic

data class ComicDetailState(
    val isLoading: Boolean,
    val comic: Comic?,
    val error: String?
)
