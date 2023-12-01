package cn.shef.msc5.todo.activity

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun CourseSection(courses: List<Course>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "courses",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        // we have used lazyVertically grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // it basically tells no. of cells in a row
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp,bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(courses.size) {
                CourseItem(course = courses[it])
            }
        }
    }
}
