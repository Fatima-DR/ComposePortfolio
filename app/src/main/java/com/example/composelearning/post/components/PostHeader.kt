package com.example.composelearning.post.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearning.R


@Composable
private fun PostHeader() {

    val ringBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFFC913B9),
                Color(0xFFF9373F),
                Color(0xFFFECD00)
            )
        )
    }

    val borderWidth = 1.5.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(32.dp)
                    .padding(borderWidth + (borderWidth / 2))
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(borderWidth, ringBrush),
                        shape = CircleShape
                    )
            )

            Column {
                Text(
                    text = "Cute Pup",
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = "Sponsored",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "more"
        )
    }
}

@Preview
@Composable
private fun PostHeaderPreview() {
    PostHeader()
}