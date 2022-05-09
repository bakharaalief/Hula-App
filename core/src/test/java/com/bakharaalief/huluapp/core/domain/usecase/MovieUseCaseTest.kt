package com.bakharaalief.huluapp.core.domain.usecase

import com.bakharaalief.huluapp.DataDummy
import com.bakharaalief.huluapp.core.data.Resource
import com.bakharaalief.huluapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        movieUseCase = MovieInteract(movieRepository)
    }

    @Test
    fun `when Get User Should Not Null and Return Success`() {

        val dummyMovies = DataDummy.generateDummyMovies()
        val expectedResult = Resource.Success(dummyMovies)
        `when`(movieRepository.getAllMovies()).thenReturn(flowOf(expectedResult))

        val actualResult = movieUseCase.getAllMovies()

        verify(movieRepository).getAllMovies()
        Assert.assertNotNull(actualResult)
    }
}