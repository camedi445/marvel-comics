package co.cmedina.marvelcomics.domain.model

data class Character(
    val id: Int,
    val imageURL: String,
    val name: String,
    val description: String
)
