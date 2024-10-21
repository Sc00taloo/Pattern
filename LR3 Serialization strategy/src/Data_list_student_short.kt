import main.src.Student_short

class Data_list_student_short(students: List<Student_short>) : Data_list<Student_short>(students) {
    // получение атрибутов кроме ID
    override fun get_names(): List<List<Any>> {
        return data.map { student ->
            listOf(
                student.lastName,
                student.firstName,
                student.middleName,
                student.phone ?: "null",
                student.telegram ?: "null",
                student.email ?: "null",
                student.git ?: "null"
            )
        }
    }

    // получение полного списка
    override fun get_data(): List<List<Any>> {
        val dataList = mutableListOf<List<Any>>()
        for (index in get_selected()) {
            val student = data[index]
            val row = listOf(
                (index + 1).toString(),
                student.lastName,
                student.firstName,
                student.middleName,
                student.phone,
                student.telegram,
                student.email,
                student.git
            )
            dataList.add(listOf(row))
        }
        return dataList
    }
}