package cn.shef.msc5.todo.model

import androidx.compose.ui.graphics.Color
import cn.shef.msc5.todo.ui.theme.Orange
import cn.shef.msc5.todo.ui.theme.Purple40
import cn.shef.msc5.todo.ui.theme.Red

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:16
 */
sealed class PriorityLevelEnum(val level: Int, val color: Color, val value: String){
    object HIGH : PriorityLevelEnum(1, Red, "SUPER IMPORTANT")
    object MEDIUM : PriorityLevelEnum(2, Orange, "Please do this")
    object LOW : PriorityLevelEnum(3, Purple40, "Not urgent")

    companion object {
        fun createFromInt(priority: Int): PriorityLevelEnum {
            return when (priority) {
                LOW.level -> LOW
                MEDIUM.level -> MEDIUM
                HIGH.level -> HIGH
                else -> throw IllegalArgumentException("Invalid priority value: $priority")
            }
        }
    }
}