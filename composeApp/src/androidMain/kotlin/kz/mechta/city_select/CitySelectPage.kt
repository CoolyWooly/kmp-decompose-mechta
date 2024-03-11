package kz.mechta.city_select

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import city_select.presentation.CitySelectComponent
import city_select.presentation.Effect
import city_select.presentation.Event
import kz.mechta.R
import kz.mechta.city_select.view.CityItem
import kz.mechta.city_select.view.CityItemEstimated
import kz.mechta.theme.MechtaTheme
import kz.mechta.view.MechtaCircularProgressIndicator
import kz.mechta.view.MechtaSearchField
import kz.mechta.view.MechtaSearchView
import `mechta-kmp`.shared.MR

@Composable
fun CitySelectPage(component: CitySelectComponent) {
    val context = LocalContext.current
    val uiState by component.state.subscribeAsState()
    LaunchedEffect(component.effect) {
        component.effect.watch {
            when (it) {
                is Effect.ShowToast -> {
                    if (it.text.isNullOrBlank()) return@watch
                    Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MechtaTheme.colors.ui01
    ) {
        TrackLocation(
            onLocationFound = { lat, lon ->
                component.onEvent(Event.OnCoordinatesFetch(lat = lat, lon = lon))
            }
        )
        Column {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = MR.strings.select_city.resourceId),
                style = MechtaTheme.typography.h1,
                color = MechtaTheme.colors.text01
            )
            Spacer(modifier = Modifier.size(24.dp))
            MechtaSearchField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = uiState.textSearch,
                placeholderText = stringResource(id = MR.strings.city_search.resourceId),
                onValueChange = { component.onEvent(Event.OnTextSearchChange(it)) },
                onSearchClick = {  }
            )
            Spacer(modifier = Modifier.size(16.dp))
            CityItemEstimated(
                name = uiState.cityEstimated.name,
                onClick = { component.onEvent(Event.OnCityClick(uiState.cityEstimated)) }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                if (uiState.isLoading) {
                    item {
                        MechtaCircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
                items(uiState.cityList) {
                    CityItem(
                        name = it.name,
                        onClick = { component.onEvent(Event.OnCityClick(it)) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun TrackLocation(onLocationFound: (latitude: Double, longitude: Double) -> Unit) {
    val context = LocalContext.current
    val permissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    onLocationFound(location.latitude, location.longitude)
                }
            }
        }

        is PermissionStatus.Denied -> {
            LaunchedEffect(permissionState) {
                permissionState.launchPermissionRequest()
            }
        }
    }
}