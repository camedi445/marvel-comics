package co.cmedina.marvelcomics.data.model

import co.cmedina.marvelcomics.domain.model.Character

data class CharacterResponse(
    val data: CharacterData
)

data class CharacterData(
    val results: List<CharacterDTO>
)

data class CharacterDTO(
    val id: Int,
    val thumbnail: Thumbnail,
    val name: String
) {

    fun toDomainCharacter() = Character(
        id = this.id,
        imageURL = thumbnail.getURL(),
        name = this.name
    )
}

