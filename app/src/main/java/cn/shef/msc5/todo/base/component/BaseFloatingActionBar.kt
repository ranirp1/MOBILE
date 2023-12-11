package cn.shef.msc5.todo.base.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 11:58
 */
@Composable
fun BaseFloatingActionBar(
    fabVisible: Boolean = false,
    imageVector: ImageVector = Icons.Filled.AddTask,
    contentDescription: String = "Add",
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
    ) {
        Row(Modifier.padding(start = 12.dp, end = 12.dp)) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            AnimatedVisibility(
                fabVisible,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(modifier = Modifier.padding(start = 12.dp), text = contentDescription)
            }
        }
    }
}
