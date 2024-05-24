package com.ps.latestgreatestplayground.presentation.document

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ps.latestgreatestplayground.R
import kotlinx.serialization.Serializable

@Serializable
data object DocumentDestination

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DocumentScreen(modifier: Modifier = Modifier) {
    val navigator = rememberSupportingPaneScaffoldNavigator()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack(
        )
    }

    SupportingPaneScaffold(directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            DocumentPane(modifier,
                openComments = { navigator.navigateTo(SupportingPaneScaffoldRole.Supporting) },
                areCommentsDisplayed = navigator.scaffoldValue.secondary == PaneAdaptedValue.Expanded)
        },
        supportingPane = {
            CommentPane(
                modifier = modifier, comments = listOf(
                    Comment("Good one", "William", R.drawable.bojack),
                    Comment("Good one", "William", R.drawable.bojack),
                    Comment("Good one", "William", R.drawable.bojack)
                )
            )
        })
}

val myString =
    "elit sed vulputate mi sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada proin libero nunc consequat interdum varius sit amet mattis vulputate enim nulla aliquet porttitor lacus luctus accumsan tortor posuere ac ut consequat semper viverra nam libero justo laoreet sit amet cursus sit amet dictum sit amet justo donec enim diam vulputate ut pharetra sit amet aliquam id diam maecenas ultricies mi eget mauris pharetra et ultrices neque ornare aenean euismod elementum nisi quis eleifend quam adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque viverra justo nec ultrices dui sapien eget mi proin sed libero enim sed faucibus turpis in eu mi bibendum neque egestas congue quisque egestas diam in arcu cursus euismod quis viverra nibh cras pulvinar mattis nunc sed blandit libero volutpat sed cras ornare arcu dui vivamus arcu felis bibendum ut tristique et egestas quis ipsum suspendisse ultrices gravida dictum fusce ut placerat orci nulla pellentesque dignissim enim sit amet venenatis urna cursus eget nunc scelerisque viverra mauris in aliquam sem fringilla ut morbi tincidunt augue interdum velit euismod in pellentesque massa placerat duis ultricies lacus sed turpis tincidunt id aliquet risus feugiat in ante metus dictum"

@Composable
fun DocumentPane(
    modifier: Modifier = Modifier, openComments: () -> Unit, areCommentsDisplayed: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            value = myString,
            onValueChange = {},
            readOnly = true
        )

        if (!areCommentsDisplayed) {
            TextButton(onClick = openComments) {
                Text(text = "Open Comments")
            }
        }

    }
}

@Composable
fun CommentPane(modifier: Modifier = Modifier, comments: List<Comment>) {
    LazyColumn(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(comments) { comment ->
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            comment.authorImgId,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(64.dp)
                                .clip(CircleShape)
                        )

                        Text(
                            text = comment.author, style = MaterialTheme.typography.labelLarge
                        )

                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8),
                        value = comment.comment,
                        onValueChange = {},
                        readOnly = true,
                    )
                }

            }
        }
    }
}


data class Comment(val comment: String, val author: String, val authorImgId: Int)

