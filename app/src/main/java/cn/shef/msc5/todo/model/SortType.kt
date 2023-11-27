package cn.shef.msc5.todo.model

sealed class SortType(val sortOrder: SortOrder){
    class Priority(sortOrder:SortOrder):SortType(sortOrder)
    class DueDate(sortOrder:SortOrder):SortType(sortOrder)
    class Location(sortOrder:SortOrder):SortType(sortOrder)

    fun reorder(sortOrder: SortOrder) : SortType = when(this){
        is Priority -> Priority(sortOrder)
        is DueDate -> DueDate(sortOrder)
        is Location -> Location(sortOrder)
    }
}
