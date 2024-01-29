package co.cmedina.marvelcomics.domain.model

data class Comic(
    val id: Int,
    val imageURL: String,
    val title: String,
    val description: String
)
