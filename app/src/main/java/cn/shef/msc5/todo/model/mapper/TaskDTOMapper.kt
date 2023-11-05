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
            name = model.name,
            level =  model.level,
            remark = model.remark
        )
    }

    override fun map2DTOModel(domainModel: Task): TaskDTO {
        return TaskDTO(
            id = domainModel.id,
            name = domainModel.name,
            level =  domainModel.level,
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
