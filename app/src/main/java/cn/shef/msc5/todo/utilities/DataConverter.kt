package cn.shef.msc5.todo.utilities

import androidx.room.TypeConverter
import cn.shef.msc5.todo.model.dto.SubTask
import cn.shef.msc5.todo.model.dto.TaskDTO
import org.json.JSONArray
import org.json.JSONObject

class MapItems(id: Int, title: String, longitude: Double, latitude: Double) {
    var id: Int = id
    var title: String = title
    var longitude: Double = longitude
    var latitude: Double = latitude
}

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

        @TypeConverter
        @JvmStatic
        fun fromTaskList(list: List<TaskDTO>): String {
            val jsonArray = JSONArray()
            for (item in list) {
                val jsonObject = JSONObject()
                jsonObject.put("id", item.id.toString())
                jsonObject.put("title", item.title)
                jsonObject.put("latitude", item.latitude.toString())
                jsonObject.put("longitude", item.longitude.toString())
                jsonArray.put(jsonObject)
            }
            return jsonArray.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toTaskList(jsonString: String): List<MapItems> {
            val jsonArray = JSONArray(jsonString)
            val itemList = mutableListOf<MapItems>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getInt("id")
                val title = jsonObject.getString("title")
                val latitude = jsonObject.getDouble("latitude")
                val longitude = jsonObject.getDouble("longitude")
                itemList.add(MapItems(id, title, longitude, latitude))
            }
            return itemList
        }
    }
}
