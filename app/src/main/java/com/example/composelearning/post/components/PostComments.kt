package com.example.composelearning.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import com.example.composelearning.ui.theme.Padding

@Composable
fun PostComments(
    userName: String,
    comment: String
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("@$userName: ")
        }
        append(comment)
    }

    Column {
        Text(
            text = annotatedString,
            lineHeight = 20.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "View all 16 comments",
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostCommentsPreview() {
    PostComments(
        userName = "user_name",
        comment = "lorum ipsum doller set amet"
    )
}