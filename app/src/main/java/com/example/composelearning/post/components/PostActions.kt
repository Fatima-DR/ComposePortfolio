package com.example.composelearning.post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelearning.R
import com.example.composelearning.ui.theme.Padding

@Composable
fun PostActions(
    commentsAction: () -> Unit,
    onCommentsLayout: (Float) -> Unit
) {

    var isFavorite by remember { mutableStateOf(false) }
    var isBookmarked by remember { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(Padding.MEDIUM),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Padding.MEDIUM),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (isFavorite) {
                        painterResource(id = R.drawable.ic_like)
                    } else {
                        painterResource(R.drawable.ic_unlike)
                    },
                    tint = Color.Unspecified,
                    contentDescription = "like or unlike",
                    modifier = Modifier
                        .clickable { isFavorite = !isFavorite }
                        .defaultImageSize()
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "comment",
                    modifier = Modifier
                        .defaultImageSize()
                        .clickable {
                            commentsAction()
                        }
                        .onGloballyPositioned {
                            //get position of comments icon to start animation from
                            onCommentsLayout(it.positionInRoot().y)
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "share",
                    modifier = Modifier.defaultImageSize()
                )
            }

            Image(
                painter = if (isBookmarked) {
                    painterResource(id = R.drawable.ic_bookmark)
                } else {
                    painterResource(R.drawable.ic_bookmark_selected)
                },
                //when the bookmark is selected show a pop up saying bookmark selected
                contentDescription = "share",
                modifier = Modifier
                    .clickable { isBookmarked = !isBookmarked }
                    .defaultImageSize()
            )
        }
        Text(
            text = if (isFavorite) {
                "${countLikes(isFavorite)} Likes"
            } else {
                "0 Likes"
            },
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
        )
    }
}

private fun Modifier.defaultImageSize() = size(24.dp)

@Preview
@Composable
fun PostActionsPreview() {
    PostActions(
        commentsAction = {},
        onCommentsLayout = {}
    )
}

private fun countLikes(isClicked: Boolean): Int {
    var count = 0
    if (isClicked) {
        count += 1
    } else {
        count
    }
    return count
}