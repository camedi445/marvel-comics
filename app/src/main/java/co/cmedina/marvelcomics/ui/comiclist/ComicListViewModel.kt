package co.cmedina.marvelcomics.ui.comiclist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cmedina.marvelcomics.di.IODispatcher
import co.cmedina.marvelcomics.domain.usecase.GetComicListUseCase
import co.cmedina.marvelcomics.ui.home.CharacterListMenuState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(
    private val getComicListUseCase: GetComicListUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _comicListState = MutableStateFlow(
        ComicListState(
            isLoading = true,
            comicList = emptyList()
        )
    )
    val comicListState: StateFlow<ComicListState> = _comicListState.asStateFlow()

    fun getComicList(characterId: Int) {
        viewModelScope.launch(dispatcher) {
            val comicList = getComicListUseCase.invoke(characterId)
            _comicListState.update {
                it.copy(
                    isLoading = false,
                    comicList = comicList
                )
            }
        }
    }
}