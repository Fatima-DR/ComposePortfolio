package com.example.composelearning

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

//need to abstract all components out
//need to add ability to make the like heart is clickable where it changes from like to unlike
//add ability to write a comment (create a modal maybe?)
//add ability to update bookmark to bookmarked
//dynamic text to "100 likes"

@Preview
@Composable
fun PostItem(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(vertical = 8.dp)
    ) {
        val (
            imgAvatar,
            imgMore,
            textUserNameTop,
            imgPost,
            imgLike,
            imgComment,
            imgShare,
            imgBookmark,
            textLikes,
            textUserNameBottom,
            textDescription,
            textViewComments,
            imgTags
        ) = createRefs()

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

        var isFavorite by remember { mutableStateOf(false) }
        var isBookmarked by remember { mutableStateOf(true) }

        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "avatar",
            modifier = modifier
                .constrainAs(imgAvatar) {
                    start.linkTo(parent.start, 14.dp)
                    top.linkTo(parent.top)
                }
                .size(32.dp)
                .border(
                    border = BorderStroke(borderWidth, ringBrush),
                    shape = CircleShape
                )
                .padding(borderWidth + (borderWidth / 2))
                .clip(CircleShape)
        )

        Column(
            modifier = modifier
                .constrainAs(textUserNameTop) {
                    start.linkTo(imgAvatar.end, 8.dp)
                    top.linkTo(imgAvatar.top)
                    bottom.linkTo(imgAvatar.bottom)
                }
        ) {
            Text(
                text = "Cute Pup",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )

            Text(
                text = "Sponsored",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "more",
            modifier = modifier.constrainAs(imgMore) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(imgAvatar.top)
                bottom.linkTo(imgAvatar.bottom)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.post_image),
            contentDescription = "image",
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .constrainAs(imgPost) {
                    start.linkTo(parent.start)
                    top.linkTo(imgAvatar.bottom, 8.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.ic_tags),
            contentDescription = "tags",
            modifier = modifier.constrainAs(imgTags) {
                start.linkTo(imgPost.start, 8.dp)
                bottom.linkTo(imgPost.bottom, 8.dp)
            }
        )

        Image(
            painter = if (isFavorite) {
                painterResource(id = R.drawable.ic_like)
            } else {
                painterResource(R.drawable.ic_unlike)
            },
            contentDescription = "like or unlike",
            modifier = modifier
                .clickable { isFavorite = !isFavorite }
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgLike) {
                    top.linkTo(imgPost.bottom, 8.dp)
                    start.linkTo(imgAvatar.start)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = "comment",
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgComment) {
                    top.linkTo(imgLike.top)
                    start.linkTo(imgLike.end, 8.dp)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "share",
            modifier = modifier
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgShare) {
                    top.linkTo(imgComment.top)
                    start.linkTo(imgComment.end, 8.dp)
                }
        )

        Image(
            painter = if (isBookmarked) {
                painterResource(id = R.drawable.ic_bookmark)
            } else {
                painterResource(R.drawable.ic_bookmark_selected)
            },
            //when the bookmark is selected show a pop up saying bookmark selected
            contentDescription = "share",
            modifier = modifier
                .clickable { isBookmarked = !isBookmarked }
                .width(24.dp)
                .height(24.dp)
                .constrainAs(imgBookmark) {
                    top.linkTo(imgLike.top)
                    end.linkTo(parent.end, 16.dp)
                }
        )

        Text(
            text = if (isFavorite) {
                "${countLikes(isFavorite)} Likes"
            } else { "0 Likes" },
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
            modifier = modifier.constrainAs(textLikes) {
                start.linkTo(imgLike.start)
                top.linkTo(imgLike.bottom, 8.dp)
            }
        )

        Text(
            text = "Username",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = modifier.constrainAs(textUserNameBottom) {
                start.linkTo(textLikes.start)
                top.linkTo(textLikes.bottom, 8.dp)
            }
        )

        Text(
            text = "lots of text here, to fill two lines for testing purposes only",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.constrainAs(textDescription) {
                start.linkTo(textUserNameBottom.end, 4.dp)
                top.linkTo(textLikes.bottom, 8.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = "View all 16 comments",
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            modifier = modifier.constrainAs(textViewComments) {
                start.linkTo(textUserNameBottom.start)
                top.linkTo(textDescription.bottom, 8.dp)
            }
        )
    }
}

//@Composable
//private fun BuildAvatar(modifier: Modifier, imgAvatar: ConstrainedLayoutReference) {
//    ConstraintLayout {
//        val ringBrush = remember {
//            Brush.sweepGradient(
//                listOf(
//                    Color(0xFFC913B9),
//                    Color(0xFFF9373F),
//                    Color(0xFFFECD00)
//                )
//            )
//        }
//
//        val borderWidth = 1.5.dp
//
//        Image(
//            painter = painterResource(id = R.drawable.avatar),
//            contentDescription = "avatar",
//            modifier = modifier
//                .constrainAs(imgAvatar) {
//                    start.linkTo(parent.start, 14.dp)
//                    top.linkTo(parent.top)
//                }
//                .size(32.dp)
//                .border(
//                    border = BorderStroke(borderWidth, ringBrush),
//                    shape = CircleShape
//                )
//                .padding(borderWidth + (borderWidth / 2))
//                .clip(CircleShape)
//        )
//    }
//}

//private fun bookmarkSelected(isSelected: Boolean): Int {
//    if (isSelected) {
//        //do something like pop up a message saying "Bookmarked!"
//    } else {
//        //if already bookmarked unbookmark
//    }
//}

private fun countLikes(isClicked: Boolean): Int {
    var count = 0
    if (isClicked) {
        count = count + 1
    } else {
        count
    }
    return count
}