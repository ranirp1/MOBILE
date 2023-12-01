package cn.shef.msc5.todo.model

sealed class SortOrder {
    object Ascending:SortOrder()
    object Descending:SortOrder()
}