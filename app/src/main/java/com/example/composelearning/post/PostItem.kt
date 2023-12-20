package com.example.composelearning.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelearning.post.components.PostActions
import com.example.composelearning.post.components.PostComments
import com.example.composelearning.post.components.PostHeader
import com.example.composelearning.post.components.PostImage
import com.example.composelearning.ui.theme.Padding

@Composable
fun PostItem() {
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
            PostActions()
            PostComments(
                userName = "user_name",
                comment = "lorum ipsum doller set amet"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostItemNewPreview() {
    PostItem()
}
