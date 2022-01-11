package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.domain.usecases.GetCitiesUseCase
import com.picpay.desafio.android.domain.utils.Error
import com.picpay.desafio.android.domain.utils.Result
import com.picpay.desafio.android.presentation.cities.CitiesState
import com.picpay.desafio.android.presentation.cities.CitiesViewModel
import com.picpay.desafio.android.presentation.utils.ResourcesHelper
import com.picpay.desafio.android.util.CoroutineTestRule
import com.picpay.desafio.android.util.captureValues
import com.picpay.desafio.android.util.cityWeatherMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CitiesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    lateinit var subject: CitiesViewModel

    private val getCitiesUseCaseMock: GetCitiesUseCase = mockk(relaxed = true)
    private val resourceHelper: ResourcesHelper = mockk(relaxed = true)

    @Before
    fun setup() {
        subject = CitiesViewModel(resourceHelper, getCitiesUseCaseMock)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGetCityWithSuccess() = coroutinesTestRule.runBlockingTest {
        val citiesResultMock: Result.Success<CityWeather> = Result.Success(cityWeatherMock)
        coEvery {
            getCitiesUseCaseMock.getCoordinatorsWeather(
                0.0,
                0.0,
                1,
                "metric"
            )
        } returns citiesResultMock

        val expected = CitiesState.Presentation.Success(
            contactListPresentation = mutableListOf(cityWeatherMock)
        )

        subject.presentationState.captureValues {
            subject.getCoordinatorsWeather(0.0, 0.0)
            assertEquals(expected, this.values.first())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGetErrorWhenTryToGetCity() = coroutinesTestRule.runBlockingTest {
            val citiesResultErrorMock: Result.Failure<CityWeather> = Result.Failure(Error.ResponseError)
            coEvery {
                getCitiesUseCaseMock.getCoordinatorsWeather(
                    1.0,
                    1.0,
                    1,
                    "metric"
                )
            } returns citiesResultErrorMock

            val expected = CitiesState.Presentation.NetworkError()

            subject.presentationState.captureValues {
                subject.getCoordinatorsWeather(1.0,  1.0)
                assertEquals(expected, this.values[1])
            }
        }


}