package cn.shef.msc5.todo.model.enums

import cn.shef.msc5.todo.ui.theme.Orange
import cn.shef.msc5.todo.ui.theme.Purple40
import cn.shef.msc5.todo.ui.theme.Red

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:16
 */
sealed class TaskStateEnum(val state: Int, val value: String, val levelStr: String){
    object UNFINISHED : PriorityLevelEnum(1, Red, "Unfinished", "Unfinished")
    object ISCOMPLETED : PriorityLevelEnum(2, Orange, "IsCompleted", "IsCompleted")
    object EXPIRED : PriorityLevelEnum(3, Purple40, "Expired", "Expired")

    companion object {
        fun createFromInt(priority: Int): PriorityLevelEnum {
            return when (priority) {
                UNFINISHED.level -> UNFINISHED
                ISCOMPLETED.level -> ISCOMPLETED
                EXPIRED.level -> EXPIRED
                else -> throw IllegalArgumentException("Invalid priority value: $priority")
            }
        }
    }
}

fun getTaskStateValues(): List<String> {
    return listOf(
        TaskStateEnum.UNFINISHED.levelStr,
        TaskStateEnum.ISCOMPLETED.levelStr,
        TaskStateEnum.EXPIRED.levelStr
    )
}