package com.picpay.desafio.android.presentation.cities

import com.picpay.desafio.android.domain.model.CityWeather

class CitiesState {

    sealed class Presentation {
        data class Success(val contactListPresentation: MutableList<CityWeather>): CitiesState.Presentation()
        data class NetworkError(val error: String? = null): CitiesState.Presentation()
    }

    sealed class Action {
        data class Loading(val showLoading: Boolean) : CitiesState.Action()
    }
}