package co.cmedina.marvelcomics.data.model

import co.cmedina.marvelcomics.domain.model.Character

data class CharacterResponse(
    val code: Int,
    val status: String,
    val data: CharacterData
)

data class CharacterData(
    val results: List<CharacterDTO>
)

data class CharacterDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) {

    fun toDomainCharacter() = Character(
        id = this.id,
        name = this.name,
        description = this.description,
        imageURL = thumbnail.getURL()
    )
}

