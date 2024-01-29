package co.cmedina.marvelcomics.ui.comicdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cmedina.marvelcomics.di.IODispatcher
import co.cmedina.marvelcomics.domain.usecase.GetComicByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    private val getComicByIdUseCase: GetComicByIdUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _comicDetailState = MutableStateFlow(
        ComicDetailState(
            isLoading = true,
            comic = null,
            error = null
        )
    )
    val comicDetailState: StateFlow<ComicDetailState> = _comicDetailState.asStateFlow()

    fun getComicById(comicId: Int) {
        viewModelScope.launch(dispatcher) {
            val comicResult = getComicByIdUseCase(comicId)
            comicResult.fold(
                ifRight = { comic ->
                    _comicDetailState.update {
                        it.copy(
                            isLoading = false,
                            comic = comic,
                        )
                    }
                },
                ifLeft = { messageException ->
                    _comicDetailState.update {
                        it.copy(
                            isLoading = false,
                            error = messageException.message,
                        )
                    }
                }
            )
        }
    }
}