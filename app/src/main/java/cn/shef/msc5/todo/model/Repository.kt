package cn.shef.msc5.todo.model

import android.content.Context
import androidx.lifecycle.LiveData
import cn.shef.msc5.todo.model.dao.UserDAO
//import cn.shef.msc5.todo.model.database.ToDosDatabase

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 17:16
 */
open class Repository {

//    private var userDAO: UserDAO

    constructor(context: Context){
//         var database = ToDosDatabase.inst(context)
//         this.userDAO = database.userDAO()
    }

    fun insertUser(user: User){
//        this.userDAO.insert(user)
    }

    fun updateUser(user: User){
//        this.userDAO.update(user)
    }

    fun getUser(id: Long){
//        this.userDAO.getUser(id)
    }

//    fun queryList(): LiveData<List<User>> {
//        return this.userDAO.queryList()
//    }


}