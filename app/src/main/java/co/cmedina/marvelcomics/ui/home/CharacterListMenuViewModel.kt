package co.cmedina.marvelcomics.ui.home

import androidx.compose.runtime.mutableStateOf
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
            characterList = emptyList()
        )
    )
    val characterListMenuState: StateFlow<CharacterListMenuState> = _characterListMenuState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            val characterList = getCharacterListUseCase.invoke()
            _characterListMenuState.update {
                it.copy(
                    isLoading = false,
                    characterList = characterList
                )
            }
        }
    }

}