package cn.shef.msc5.todo.model

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 01/12/2023 19:55
 */
data class TaskListState(

    val isLoading: Boolean = false,

    val isEmpty: Boolean = false,

    val hasError: Boolean = false,

    val data: List<Task> = emptyList(),
)

val TaskListState.isEmpty: Boolean
    get() = data.isEmpty()

