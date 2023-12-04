package cn.shef.msc5.todo.model

sealed class TemplateEnum(val templateType: String, val templateText: String){
    object ASSIGNMENT : TemplateEnum("Assignment", "Assignment submission for course ")
    object EXERCISE : TemplateEnum("Attendance", "Attend course ")
    object MEETING : TemplateEnum("Meeting", "Meeting with ")
}

fun getTemplateStr(): List<String> {
    return listOf(
        TemplateEnum.ASSIGNMENT.templateType,
        TemplateEnum.EXERCISE.templateType,
        TemplateEnum.MEETING.templateType
    )
}

fun getTemplateTextStr(): List<String> {
    return listOf(
        TemplateEnum.ASSIGNMENT.templateText,
        TemplateEnum.EXERCISE.templateText,
        TemplateEnum.MEETING.templateText
    )
}