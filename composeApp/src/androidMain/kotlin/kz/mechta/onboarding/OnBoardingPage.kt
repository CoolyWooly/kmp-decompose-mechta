package kz.mechta.onboarding

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import on_boarding.presentation.OnBoardingComponent
import on_boarding.presentation.OnBoardingEffect
import on_boarding.presentation.OnBoardingEvent
import kotlinx.coroutines.launch
import kz.mechta.onboarding.view.ButtonNext
import kz.mechta.onboarding.view.OnBoardingItem
import kz.mechta.shared.MR
import kz.mechta.theme.MechtaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPage(component: OnBoardingComponent) {
    val context = LocalContext.current
    val uiState by component.state.subscribeAsState()
    component.effect.watch {
        when (it) {
            is OnBoardingEffect.ShowToast -> {
                if (it.text.isNullOrBlank()) return@watch
                Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MechtaTheme.colors.ui01
    ) {
        val pagerState = rememberPagerState(pageCount = { 3 })
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    component.onEvent(OnBoardingEvent.OnSwipe(page))
                }
            }
        }
        LaunchedEffect(uiState.selectedIndex) {
            pagerState.animateScrollToPage(uiState.selectedIndex)
        }
        if (uiState.selectedIndex == 2) {
            TrackLocation(
                onLocationFound = { lat, lon ->
                    component.onEvent(OnBoardingEvent.OnCoordinatesFetch(lat = lat, lon = lon))
                }
            )
        }
        Box {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { page ->
                OnBoardingItem(
                    index = page,
                    cityName = uiState.city.name
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonNext(
                    selectedIndex = uiState.selectedIndex,
                    onClick = { component.onEvent(OnBoardingEvent.OnNextClick) }
                )
                Spacer(modifier = Modifier.size(8.dp))
                if (uiState.selectedIndex == 2) {
                    TextButton(onClick = { component.onEvent(OnBoardingEvent.OnSelectCityClick) }) {
                        Text(
                            text = stringResource(id = MR.strings.no_another_city.resourceId),
                            style = MechtaTheme.typography.body1,
                            color = MechtaTheme.colors.text01
                        )
                    }
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