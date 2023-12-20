package com.example.composelearning.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelearning.ui.theme.Padding

@Composable
fun PostItemScreen() {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Padding.MEDIUM),
        content = {
            repeat(10) {
                item {
                    PostItem()
                }
            }
        })

}

@Preview
@Composable
fun PostItemScreenPreview() {
    PostItemScreen()
}