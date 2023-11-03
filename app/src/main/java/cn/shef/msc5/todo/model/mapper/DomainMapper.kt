package cn.shef.msc5.todo.model.mapper

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 */
interface DomainMapper <T, DomainModel>{

    /**
     * convert from DTO to DO
     */
    fun map2Model(model: T): DomainModel

    /**
     * convert from DO to DTO
     */
    fun map2DTOModel(domainModel: DomainModel): T
}