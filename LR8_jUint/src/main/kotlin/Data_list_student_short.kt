import main.src.Student_short

class Data_list_student_short(students: List<Student_short>, wholeEntitiesCount: Int) :
    Data_list<Student_short>(students, wholeEntitiesCount) {
    private var observer: Student_list_view? = null

    // Установка наблюдателя
    override fun setObserver(observer: Student_list_view) {
        this.observer = observer
    }

    override fun notify(columnNames: Array<String>, dataTable: List<List<Any>>) {
        val convertedTable = dataTable.map { it.toTypedArray() }
        observer?.set_table_params(columnNames, wholeEntitiesCount)
        observer?.set_table_data(convertedTable)
    }

    override fun get_names(): List<String> {
        return listOf("№","Инициалы ФИО","Гит","Контакт")
    }

    // получение полного списка
    override fun get_data(): List<List<Any>> {
        val dataList = mutableListOf<List<Any>>()
        dataList.add(get_names())
        var index =0
        for (student in data) {
            index += 1;
            val row = listOf(
                index,
                student.FIO(),
                student.git,
                student.contactMethod()
            )
            dataList.add(listOf(row))
        }
        return dataList
    }
}