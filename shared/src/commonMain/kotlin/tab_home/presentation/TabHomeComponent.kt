package tab_home.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import on_boarding.data.OnBoardingRepository
import on_boarding.presentation.OnBoardingEvent
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.getScopeName
import org.koin.core.component.inject
import tab_home.data.TabHomeRepository
import utils.WrappedSharedFlow

class TabHomeComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, KoinComponent {

    private val tabHomeRepository by inject<TabHomeRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableValue(TabHomeState())
    val state: Value<TabHomeState> = _state
    private val _effect = MutableSharedFlow<TabHomeEffect>()
    val effect: WrappedSharedFlow<TabHomeEffect> = WrappedSharedFlow(_effect.asSharedFlow())

    init {
        getHistory()
        trackCity {
            getBanners()
            getPopularCategories()
            getPersonalRecommendations()
            getHitsRecommendations()
            getActions()
            getNews()
            getBrands()
        }
    }

    fun onEvent(event: TabHomeEvent) {
        when (event) {
            is TabHomeEvent.OnCoordinatesFetch -> TODO()
            is TabHomeEvent.OnNextClick -> TODO()
            is TabHomeEvent.OnSelectCityClick -> TODO()
            is TabHomeEvent.OnSwipe -> TODO()
        }
    }

    private fun trackCity(
        onChange: () -> Unit
    ) {
        scope.launch {
            tabHomeRepository.getCity().collect {
                onChange()
            }
        }
    }

    private fun getBanners() {
        scope.launch {
            _state.update { it.copy(isLoadingBanners = true) }
            delay(4000)
            when (val data = tabHomeRepository.getBanners()) {
                is Result.Error -> {
                    _state.update { it.copy(isLoadingBanners = false) }
                    _effect.emit(TabHomeEffect.ShowToast(data.text))
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoadingBanners = false,
                            bannersList = data.value,
                        )
                    }
                }
            }
        }
    }

    private fun getPopularCategories() {
        scope.launch {
            _state.update { it.copy(isLoadingCategories = true) }
            when (val data = tabHomeRepository.getCategories()) {
                is Result.Error -> {
                    _state.update { it.copy(isLoadingCategories = false) }
                    _effect.emit(TabHomeEffect.ShowToast(data.text))
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoadingCategories = false,
                            categoriesList = data.value,
                        )
                    }
                }
            }
        }
    }

    private fun getHistory() {

    }

    private fun getPersonalRecommendations() {
        scope.launch {
            when (val data = tabHomeRepository.getRecommendations()) {
                is Result.Error -> {}
                is Result.Success -> {
                    _state.update { it.copy(recommendationPersonalList = data.value) }
                }
            }
        }
    }

    private fun getHitsRecommendations() {
        scope.launch {
            when (val data = tabHomeRepository.getHits()) {
                is Result.Error -> {}
                is Result.Success -> {
                    _state.update { it.copy(recommendationHitsList = data.value) }
                }
            }
        }
    }

    private fun getActions() {

    }

    private fun getNews() {

    }

    private fun getBrands() {

    }
}