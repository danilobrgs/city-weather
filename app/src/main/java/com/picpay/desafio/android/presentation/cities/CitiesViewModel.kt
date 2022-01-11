package com.picpay.desafio.android.presentation.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.domain.usecases.GetCitiesUseCase
import com.picpay.desafio.android.domain.utils.Result
import com.picpay.desafio.android.presentation.utils.ResourcesHelper
import com.picpay.desafio.android.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.*

class CitiesViewModel(
    resourcesHelper: ResourcesHelper,
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _presentationState = MutableLiveData<CitiesState.Presentation>()
    val presentationState: LiveData<CitiesState.Presentation> = _presentationState

    private val _actionState = SingleLiveEvent<CitiesState.Action>()
    val actionState: LiveData<CitiesState.Action> = _actionState

    private val cities: Array<String> = resourcesHelper.getStringArray(R.array.cities)
    private val citiesWeather = mutableListOf<CityWeather>()

    init {
        getCitiesWeather()
    }

    private fun getCitiesWeather() {
        viewModelScope.launch {
            _actionState.value = CitiesState.Action.Loading(true)

            val responses = cities.map { city ->
                async(Dispatchers.IO) {
                    getCitiesUseCase.getCityWeather(city, "metric")
                }
            }.awaitAll()

            responses.forEach { result ->
                result.transform {
                    citiesWeather.add(it)
                }
            }

            _actionState.value = CitiesState.Action.Loading(false)
            _presentationState.value = CitiesState.Presentation.Success(citiesWeather)
        }
    }

    fun getCoordinatorsWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _actionState.value = CitiesState.Action.Loading(true)
            when (val response =
                getCitiesUseCase.getCoordinatorsWeather(latitude, longitude, 1, "metric")) {
                is Result.Success -> {
                    _actionState.value = CitiesState.Action.Loading(false)

                    if (!citiesWeather.contains(response.data)) {
                        citiesWeather.add(0, response.data)
                    }
                    _presentationState.value =
                        CitiesState.Presentation.Success(citiesWeather)
                }
                is Result.Failure -> {
                    _actionState.value = CitiesState.Action.Loading(false)
                    _presentationState.value = CitiesState.Presentation.NetworkError()

                }
            }
        }
    }
}