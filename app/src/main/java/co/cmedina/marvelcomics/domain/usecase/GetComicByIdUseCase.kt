package co.cmedina.marvelcomics.domain.usecase

import co.cmedina.marvelcomics.domain.repository.ComicRepository
import javax.inject.Inject

class GetComicByIdUseCase @Inject constructor(
    private val comicRepository: ComicRepository
) {
    suspend operator fun invoke(comicId: Int) = comicRepository.getComicById(comicId)
}