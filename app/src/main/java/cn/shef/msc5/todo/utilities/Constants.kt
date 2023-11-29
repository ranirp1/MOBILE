package cn.shef.msc5.todo.utilities


/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 *
 *      Constant Val
 */
class Constants {
    //companion object, sames like the static keyword in Java
    companion object {
//        const val MODE: String = R.string.mode.toString() // Debug, Release .....

        //navigation bar name
        const val APP_NAME = "ToDos"

        const val NAVIGATION_HOME = "Home"

        const val NAVIGATION_PROGRESS = "Progress"

        const val NAVIGATION_PROFILE = "Profile"

        //delay times
        const val DELAY_TIME = 4000L


        const val DATABASE_TASK = "d_task"
        const val TABLE_TASK = "t_task"

        // sorting types
        const val SORT_PRIORITY = "Priority"
        const val SORT_DUE = "Due Date"
        const val SORT_LOCATION = "Location"

        const val OPTIONS_DUPLICATE = "Duplicate"
        const val OPTIONS_DELETE = "Delete"
    }
}