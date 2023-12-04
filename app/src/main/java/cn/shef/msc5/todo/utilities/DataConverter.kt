package cn.shef.msc5.todo.utilities

import androidx.room.TypeConverter
import cn.shef.msc5.todo.model.dto.SubTask
import org.json.JSONArray
import org.json.JSONObject

class SubTaskConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromSubTaskList(list: List<SubTask>): String {
            val jsonArray = JSONArray()
            for (item in list) {
                val jsonObject = JSONObject()
                jsonObject.put("text", item.text)
                jsonObject.put("isChecked", item.isChecked)
                jsonArray.put(jsonObject)
            }
            return jsonArray.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toSubTaskList(jsonString: String): List<SubTask> {
            val jsonArray = JSONArray(jsonString)
            val itemList = mutableListOf<SubTask>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val text = jsonObject.getString("text")
                val isChecked = jsonObject.getBoolean("isChecked")
                itemList.add(SubTask(text, isChecked))
            }
            return itemList
        }
    }
}
