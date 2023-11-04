package cn.shef.msc5.todo.base

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.utilities.AnimationUtility.getAnimate
import kotlinx.coroutines.delay

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 11:58
 */
@Composable
fun BaseFloatingActionBar() {

    var isClick by remember { mutableStateOf(false) }

    LaunchedEffect(isClick) {
        if (isClick) {
            delay(1000)
            isClick = false
        }
    }

    FloatingActionButton(
        onClick = {
            isClick = true
        },
        contentColor = Color.White,
        containerColor = Color.White,
        shape = CircleShape,
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(if (isClick) getAnimate().value.dp else 24.dp)
        )
    }
}
