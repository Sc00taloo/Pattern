import main.src.Student
import main.src.Student_short
import main.src.SuperStudent

class Data_list_student_short(students: List<Student_short>) : Data_list<Student_short>(students) {
    // получение атрибутов кроме ID
    override fun get_names(): List<String> {
        return listOf("Инициалы ФИО","Гит","Контакт")
    }

    // получение полного списка
    override fun get_data(): List<List<Any>> {
        val dataList = mutableListOf<List<Any>>()
        for (index in get_selected()) {
            val student = data[index]
            val row = listOf(
                student.FIO(),
                student.git,
                student.contactMethod()
            )
            dataList.add(listOf(row))
        }
        return dataList
    }
}