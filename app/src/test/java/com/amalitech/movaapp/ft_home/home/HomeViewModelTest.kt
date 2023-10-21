package com.amalitech.movaapp.ft_home.home

import com.amalitech.movaapp.domain.use_case.GetHomeMoviesUseCase
import com.amalitech.movaapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `gets movies successfully`() {
        // Arrange
        //val useCase = FakeSuccessGetHomeMoviesUseCase()
        val useCase = mockk<GetHomeMoviesUseCase>()
        coEvery { useCase.invoke() } coAnswers { Result.failure(Exception("Something Went Wrong")) }
        val viewModel = HomeViewModel(useCase)

        // Act
        viewModel.showMovies()
        val currentState = viewModel.uiState.value

        // Assert
        Assert.assertNotNull(currentState.movies)
        Assert.assertFalse(currentState.isLoading)
        Assert.assertFalse(currentState.hasError)

        coVerify(exactly = 1) { useCase.invoke() }
    }

    @Test
    fun `error occurs while fetching movies`() {
        // Arrange
        val fakeUseCase = FakeErrorGetHomeMoviesUseCase()
        val viewModel = HomeViewModel(fakeUseCase)

        // Act
        viewModel.showMovies()
        val currentState = viewModel.uiState.value
        println("State: $currentState")

        // Assert
        Assert.assertTrue(currentState.hasError)
        Assert.assertNotNull(currentState.errorMessage)
        Assert.assertNull(currentState.movies)
    }

    @Test
    fun `error message is reset after displaying it`() {
        // Arrange
        val fakeUseCase = FakeErrorGetHomeMoviesUseCase()
        val viewModel = HomeViewModel(fakeUseCase)

        // Act
        viewModel.showMovies()
        val state1 = viewModel.uiState.value
        viewModel.errorShown()
        val state2 = viewModel.uiState.value

        // Assert
        Assert.assertTrue(state1.hasError)
        Assert.assertFalse(state2.hasError)
    }
}