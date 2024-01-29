package co.cmedina.marvelcomics.ui.comiclist

import co.cmedina.marvelcomics.domain.model.Comic

data class ComicListState(
    val isLoading: Boolean,
    val comicList: List<Comic>,
    val error: String?
)
