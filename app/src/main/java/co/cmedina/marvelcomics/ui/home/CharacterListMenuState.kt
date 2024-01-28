package co.cmedina.marvelcomics.ui.home

import co.cmedina.marvelcomics.domain.model.Character

data class CharacterListMenuState(
    val isLoading: Boolean,
    val characterList: List<Character>
)
