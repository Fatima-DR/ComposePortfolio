package com.example.composelearning.post

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.composelearning.post.components.PostActions
import com.example.composelearning.post.components.PostComments
import com.example.composelearning.post.components.PostHeader
import com.example.composelearning.post.components.PostImage
import com.example.composelearning.ui.theme.Padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostItem() {

    var showPopup by remember { mutableStateOf(false) }
    var positionInY by remember { mutableStateOf(200f) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = Padding.MEDIUM)
        ) {
            PostHeader()
            PostImage()
            Column(
                modifier = Modifier.padding(all = Padding.MEDIUM)
            ) {
                PostActions(
                    commentsAction = { showPopup = !showPopup },
                    onCommentsLayout = { positionInY = it } //this is to set animation position
                )
                PostComments(
                    userName = "user_name",
                    comment = "lorum ipsum doller set amet"
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = showPopup,
            enter = slideIn(initialOffset = { IntOffset(0, 200) }),
            exit = slideOut(targetOffset = { IntOffset(0, 200) })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White)
                    .align(Alignment.Center)
            ) {
                TextField(value = TextFieldValue(""), onValueChange = {})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostItemNewPreview() {
    PostItem()
}
