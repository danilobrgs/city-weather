package com.picpay.desafio.android.domain

import com.picpay.desafio.android.domain.repositories.CityWeatherRepository
import com.picpay.desafio.android.domain.usecases.GetCitiesUseCase
import com.picpay.desafio.android.domain.utils.Result
import com.picpay.desafio.android.util.CoroutineTestRule
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class GetCitiesUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val contactRepositoryMock: CityWeatherRepository = mockk()

    private val subject: GetCitiesUseCase =
        GetCitiesUseCase(contactRepositoryMock)

    @ExperimentalCoroutinesApi
    @Test
    fun test_getCitiesList() = coroutineTestRule.testDispatcher.runBlockingTest {
        //When
        coroutineTestRule.launch {
            subject.getCityWeather("Monte", "metric") as Result.Success
        }

        //Then
        coVerify(exactly = 1) { contactRepositoryMock.getCityWeather("Monte", "metric") }
    }
}