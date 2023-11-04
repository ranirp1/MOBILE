package cn.shef.msc5.todo.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cn.shef.msc5.todo.base.BaseDAO
import cn.shef.msc5.todo.model.User

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 13:43
 */
@Dao
interface UserDAO : BaseDAO<User>{

    @Query("SELECT COUNT(*) FROM t_user")
    fun count(): Long

    @Query("SELECT * FROM t_user WHERE id = :id")
    fun getUser(id: Long): User?

    @Query("select * from t_user")
    fun queryList(): LiveData<List<User>>

    override fun insert(vararg user: User) {
        TODO("Not yet implemented")
    }

    override fun replaceInsert(vararg user: User) {
        TODO("Not yet implemented")
    }

    override fun update(vararg user: User): Int {
        TODO("Not yet implemented")
    }

    override fun delete(user: User) {
        TODO("Not yet implemented")
    }
}