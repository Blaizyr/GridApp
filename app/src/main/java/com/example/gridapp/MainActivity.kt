@file:OptIn(ExperimentalFoundationApi1::class)

package com.example.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridapp.data.DataSource
import com.example.gridapp.model.Topic
import com.example.gridapp.ui.theme.GridAppTheme
import com.example.gridapp.ui.theme.Typography
import androidx.compose.foundation.ExperimentalFoundationApi as ExperimentalFoundationApi1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DefaultPreview()
                }
            }
        }
    }
}

@Composable
fun TopicBox(topic: Topic) {
    Card() {
        Row() {
            Box {
                Image(
                    painter = painterResource(id = topic.imageId),
                    contentDescription = stringResource(id = topic.titleId),
                    modifier = Modifier
                        .height(68.dp)
                        .width(68.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    //.wrapContentSize(Alignment.Center)
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                Text(
                    text = stringResource(id = topic.titleId),
                    style = MaterialTheme.typography.body2

                )
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "▒",
                        style = MaterialTheme.typography.caption,

                        )
                    Text(
                        text = topic.numberOfCourses.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

        }
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun TopicGrid(topics: List<Topic>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(topics) {topic ->
                TopicBox(topic)
            }
        },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GridAppTheme {
        TopicGrid(topics = DataSource().loadTopics())
    }
}