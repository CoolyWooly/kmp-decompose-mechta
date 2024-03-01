package data.data_source

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.coroutines.toSuspendSettings
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.CityModel

@OptIn(ExperimentalSettingsApi::class)
object Prefs {

    private val prefs: ObservableSettings = Settings() as ObservableSettings

    fun getCity(): Flow<CityModel?> {
        return prefs.getStringOrNullFlow(CITY).distinctUntilChanged().map {
            if (!it.isNullOrBlank()) {
                Json.decodeFromString<CityModel>(it)
            } else {
                null
            }
        }
    }

    fun setCity(cityModel: CityModel?) {
        val json = Json.encodeToString(cityModel)
        prefs[CITY] = json
    }

    private const val CITY = "CITY"
}