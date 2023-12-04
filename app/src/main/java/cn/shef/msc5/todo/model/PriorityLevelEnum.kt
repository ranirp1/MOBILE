package cn.shef.msc5.todo.model

import cn.shef.msc5.todo.R

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:16
 */
sealed class PriorityLevelEnum(val color: Int, val id: Int){
    object LOW : PriorityLevelEnum(androidx.appcompat.R.color.material_blue_grey_800, 3)
    object MEDIUM : PriorityLevelEnum(R.color.purple_200, 2)
    object HIGH : PriorityLevelEnum(R.color.red, 1)


}


