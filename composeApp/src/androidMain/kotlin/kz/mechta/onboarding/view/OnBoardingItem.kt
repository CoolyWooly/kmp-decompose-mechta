package kz.mechta.onboarding.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme
import `mechta-kmp`.shared.MR

@Composable
fun OnBoardingItem(
    index: Int,
    cityName: String,
) {
    val pageModel = itemsData[index]
    Column {
        Spacer(modifier = Modifier.size(24.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = pageModel.image),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(id = pageModel.title, cityName),
            textAlign = TextAlign.Center,
            style = MechtaTheme.typography.h1,
            color = MechtaTheme.colors.text01
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(id = pageModel.text),
            textAlign = TextAlign.Center,
            style = MechtaTheme.typography.body1,
            color = MechtaTheme.colors.text02
        )
    }
}

private data class OnBoardingItem(
    val title: Int,
    val text: Int,
    val image: Int,
)

private val itemsData = listOf(
    OnBoardingItem(
        title = MR.strings.onboard_title_1.resourceId,
        text = MR.strings.onboard_text_1.resourceId,
        image = R.drawable.img_onboarding1
    ),
    OnBoardingItem(
        title = MR.strings.onboard_title_2.resourceId,
        text = MR.strings.onboard_text_2.resourceId,
        image = R.drawable.img_onboarding2
    ),
    OnBoardingItem(
        title = MR.strings.onboard_title_3.resourceId,
        text = MR.strings.onboard_text_3.resourceId,
        image = R.drawable.img_onboarding3
    ),
)