package co.cmedina.marvelcomics.data.model

import co.cmedina.marvelcomics.domain.model.Comic

data class ComicResponse(
    val data: ComicData
)

data class ComicData(
    val results: List<ComicDTO>
)

data class ComicDTO(
    val id: Int,
    val thumbnail: Thumbnail,
    val title: String,
    val description: String
) {

    fun toDomainComic() = Comic(
        id = this.id,
        imageURL = this.thumbnail.getURL(),
        title = this.title,
        description = this.description
    )
}