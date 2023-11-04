package cn.shef.msc5.todo.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cn.shef.msc5.todo.model.Repository

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 01/11/2023 10:30
 */
class UserViewModel : AndroidViewModel{

    var repository : Repository

    constructor(application: Application) : super(application){
        this.repository = Repository(application)
    }
}