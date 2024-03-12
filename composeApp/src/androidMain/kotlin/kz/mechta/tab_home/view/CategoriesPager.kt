package kz.mechta.tab_home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.shared.MR
import kz.mechta.theme.MechtaTheme
import kz.mechta.view.MechtaImageView
import tab_home.domain.CategoryModel

@Composable
fun CategoriesPager(
    categoriesList: List<CategoryModel>,
    onClick: (String?) -> Unit
) {
    LazyRow(
        modifier = Modifier.background(
            color = MechtaTheme.colors.ui01,
            shape = RoundedCornerShape(bottomStart = 16.dp)
        ),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Item(
                title = stringResource(id = MR.strings.actions.resourceId),
                image = R.drawable.img_actions,
                onClick = { onClick(null) },
            )
        }
        items(categoriesList) {
            Item(
                title = it.name,
                image = it.imageUrl,
                onClick = { onClick(it.code) }
            )
        }
    }
}

@Composable
private fun Item(
    title: String,
    image: Any?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MechtaImageView(
            modifier = Modifier
                .size(width = 80.dp, height = 65.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = image
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = title,
            style = MechtaTheme.typography.subtitle2,
            color = MechtaTheme.colors.text01
        )
    }
}