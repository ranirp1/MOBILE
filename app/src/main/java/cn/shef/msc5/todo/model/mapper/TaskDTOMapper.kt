package cn.shef.msc5.todo.model.mapper

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.dto.TaskDTO
import java.sql.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 */
class TaskDTOMapper : DomainMapper<TaskDTO, Task> {

    override fun map2Model(model: TaskDTO): Task {
        return Task(
            id = model.id,
            title = model.title,
            userId = model.userId,
            description = model.description,
            level =  model.level,
            longitude =  model.longitude,
            latitude =  model.latitude,
            imageUrl =  model.imageUrl,
            gmtCreate =  model.gmtCreate,
            isDelete =  model.isDelete,
            remark = model.remark
        )
    }

    override fun map2DTOModel(domainModel: Task): TaskDTO {
        return TaskDTO(
            id = domainModel.id,
            title = domainModel.title,
            userId = domainModel.userId,
            description = domainModel.description,
            level =  domainModel.level,
            longitude =  domainModel.longitude,
            latitude =  domainModel.latitude,
            imageUrl =  domainModel.imageUrl,
            gmtCreate =  domainModel.gmtCreate,
            isDelete =  domainModel.isDelete,
            remark = domainModel.remark
        )
    }

    fun map2ModelList(initial: List<TaskDTO>): List<Task> {
        return initial.map { map2Model(it) }
    }

    fun map2DTOModel(initial: List<Task>): List<TaskDTO> {
        return initial.map { map2DTOModel(it) }
    }

}
