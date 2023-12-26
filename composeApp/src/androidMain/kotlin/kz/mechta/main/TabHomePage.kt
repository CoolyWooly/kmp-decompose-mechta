package kz.mechta.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.main.TabHomeComponent

@Composable
fun TabHomePage(
    component: TabHomeComponent
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column {
            Text(text = "tabhome")
            Row {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(color = Color.Red, shape = CircleShape)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Name")
                        Text("...")
                    }
                    Text("456")
                    Text("Показать перевод 19 апр 2020")
                }
            }
        }
    }
}