package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.WeatherService
import com.picpay.desafio.android.data.repositories.WeatherRepositoryImpl
import com.picpay.desafio.android.domain.utils.Error
import com.picpay.desafio.android.domain.utils.Result
import com.picpay.desafio.android.util.CoroutineTestRule
import com.picpay.desafio.android.util.errorMockResponse
import com.picpay.desafio.android.util.responseCityWeatherDtoMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class WeatherRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val weatherServiceMock: WeatherService = mockk()

    private val subject: WeatherRepositoryImpl =
        WeatherRepositoryImpl(weatherServiceMock, "c39dbce7fd89cbb2e3780e91333fee71")


    @ExperimentalCoroutinesApi
    @Test
    fun test_getCity() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery {
            weatherServiceMock.getCityWeather(
                "Monte",
                "c39dbce7fd89cbb2e3780e91333fee71",
                "metric"
            )
        } returns responseCityWeatherDtoMock
        val expected =
            weatherServiceMock.getCityWeather("Monte", "c39dbce7fd89cbb2e3780e91333fee71", "metric")
                .body()?.toCityWeather()

        // When
        val response = subject.getCityWeather("Monte", "metric") as Result.Success

        // Then
        assertEquals(expected, response.data)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_getContactListError() = coroutineTestRule.testDispatcher.runBlockingTest {
        //Given
        coEvery {
            weatherServiceMock.getCityWeather(
                "Monte",
                "c39dbce7fd89cbb2e3780e91333fee71",
                "metric"
            )
        } returns errorMockResponse
        val expected = Error.ResponseError

        //When
        val response = subject.getCityWeather("Monte", "metric") as Result.Failure

        //Then
        assertEquals(expected, response.errorInfo)
    }
}