package cn.shef.msc5.todo.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import cn.shef.msc5.todo.R

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 08:16
 */
@Composable
fun BaseSnackBar(
    snackBarEnum: SnackBarColorEnum
) {
    val snackbarHostState = remember { SnackbarHostState() }
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            containerColor = Color(integerResource(id = snackBarEnum.backgroundColor)),
            contentColor = Color(integerResource(id = snackBarEnum.backgroundColor)),
            snackbarData = data,
            shape = MaterialTheme.shapes.medium
        )
    }
}

sealed class SnackBarColorEnum(val backgroundColor: Int) {
    object SUCCESS : SnackBarColorEnum(R.color.purple_200)
    object ERROR : SnackBarColorEnum(R.color.white)
}

