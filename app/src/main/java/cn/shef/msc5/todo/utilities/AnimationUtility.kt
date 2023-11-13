package cn.shef.msc5.todo.utilities

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State


/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 *
 *      Constant Val
 */
object AnimationUtility {
    @Composable
    fun getAnimate(): State<Float> {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        return infiniteTransition.animateFloat(
            initialValue = 24.0f,
            targetValue = 48.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    delayMillis = 100,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
    }
}