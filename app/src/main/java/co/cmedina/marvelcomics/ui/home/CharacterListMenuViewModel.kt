package co.cmedina.marvelcomics.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cmedina.marvelcomics.di.IODispatcher
import co.cmedina.marvelcomics.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListMenuViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _characterListMenuState = MutableStateFlow(
        CharacterListMenuState(
            isLoading = true,
            characterList = emptyList(),
            error = null
        )
    )
    val characterListMenuState: StateFlow<CharacterListMenuState> =
        _characterListMenuState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            val characterListResult = getCharacterListUseCase()
            characterListResult.fold(
                ifRight = { characterList ->
                    _characterListMenuState.update {
                        it.copy(
                            isLoading = false,
                            characterList = characterList
                        )
                    }
                },
                ifLeft = { messageException ->
                    _characterListMenuState.update {
                        it.copy(
                            isLoading = false,
                            error = messageException.message
                        )
                    }
                }
            )
        }
    }
}