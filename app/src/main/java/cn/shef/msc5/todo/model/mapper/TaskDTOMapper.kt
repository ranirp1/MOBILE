package cn.shef.msc5.todo.model.mapper

import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.dto.TaskDTO

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
            priority =  model.priority,
            longitude =  model.longitude,
            latitude =  model.latitude,
            imageUrl =  model.imageUrl,
            dueTime =  model.dueTime,
            parentId =  model.parentId,
            gmtModified =  model.gmtModified,
            gmtCreated =  model.gmtCreated,
            isDeleted =  model.isDeleted,
            isCompleted = model.isCompleted
        )
    }

    override fun map2DTOModel(domainModel: Task): TaskDTO {
        return TaskDTO(
            id = domainModel.id,
            title = domainModel.title,
            userId = domainModel.userId,
            description = domainModel.description,
            priority =  domainModel.priority,
            longitude =  domainModel.longitude,
            latitude =  domainModel.latitude,
            imageUrl =  domainModel.imageUrl,
            dueTime =  domainModel.dueTime,
            parentId =  domainModel.parentId,
            gmtModified =  domainModel.gmtModified,
            gmtCreated =  domainModel.gmtCreated,
            isDeleted =  domainModel.isDeleted,
            isCompleted = domainModel.isCompleted
        )
    }

    fun map2ModelList(initial: List<TaskDTO>): List<Task> {
        return initial.map { map2Model(it) }
    }

    fun map2DTOModel(initial: List<Task>): List<TaskDTO> {
        return initial.map { map2DTOModel(it) }
    }

}
