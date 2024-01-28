package co.cmedina.marvelcomics.di

import co.cmedina.marvelcomics.data.api.ComicAPI
import co.cmedina.marvelcomics.data.repository.ComicRepositoryImpl
import co.cmedina.marvelcomics.domain.repository.ComicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ComicComponent {

    @Provides
    @ViewModelScoped
    fun provideComicRepository(comicAPI: ComicAPI):
            ComicRepository = ComicRepositoryImpl(comicAPI)
}