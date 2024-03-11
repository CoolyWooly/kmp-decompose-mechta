package core.data

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import city_select.domain.model.CityModel

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

    fun getMindboxDeviceId(): Flow<String?> {
        return prefs.getStringOrNullFlow(MINDBOX_DEVICEID).distinctUntilChanged()
    }

    fun setMindboxDeviceId(deviceId: String?) {
        prefs[MINDBOX_DEVICEID] = deviceId
    }

    private const val CITY = "CITY"
    private const val MINDBOX_DEVICEID = "MINDBOX_DEVICEID"
}