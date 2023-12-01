package cn.shef.msc5.todo.ui.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.ui.theme.Pink40
import cn.shef.msc5.todo.ui.theme.Pink80
import cn.shef.msc5.todo.ui.theme.Purple40
import cn.shef.msc5.todo.ui.theme.PurpleGrey80
import androidx.compose.ui.Alignment
import cn.shef.msc5.todo.activity.BottomMenu
import cn.shef.msc5.todo.activity.BottomMenuContent
import cn.shef.msc5.todo.activity.Course
import cn.shef.msc5.todo.activity.CourseSection
import cn.shef.msc5.todo.model.viewmodel.MainViewModel

@ExperimentalFoundationApi
@Composable
fun ProgressScreen(context: Context, mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Ongoing Tasks", "Pending Tasks", "Failed Tasks"))
           // suggestionSection()
//            CourseSection(
//                courses = listOf(
//                    Course(
//                        title = "geek of the year",
//                        R.drawable.ic_play,
//                        Purple40,
//                        Pink40,
//                        PurpleGrey80
//                    ),
//                    Course(
//                        title = "How does AI Works",
//                        R.drawable.ic_launcher_foreground,
//                        Pink40,
//                        Pink80,
//                        Pink80
//                    ),
//                    Course(
//                        title = "Advance python Course",
//                        R.drawable.ic_play,
//                        Pink40,
//                        Pink80,
//                        Pink80
//                    ),
//                    Course(
//                        title = "Advance Java Course",
//                        R.drawable.ic_headphone,
//                        Pink40,
//                        Pink80,
//                        Pink80
//                    ),
//                    Course(
//                        title = "prepare for aptitude test",
//                        R.drawable.ic_play,
//                        Pink40,
//                        Pink80,
//                        Pink80
//                    ),
//                    Course(
//                        title = "How does AI Works",
//                        R.drawable.ic_headphone,
//                        Pink40,
//                        Pink80,
//                        Pink80
//                    ),
//                )
//            )
        }
    }
}


@Composable
fun GreetingSection(
    name: String = ""
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Track your progress here!",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) Color.Green
                        else Color.LightGray
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }
}

@Composable
fun suggestionSection(
    color: Color = Color.Red
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
//        Column {
//            Text(
//                text = "",
//                style = MaterialTheme.typography.h2
//            )
//            Text(
//                text = "do at least • 3-10 problems / day",
//                style = MaterialTheme.typography.body1,
//                color = Color.White
//            )
//        }

    }
}

