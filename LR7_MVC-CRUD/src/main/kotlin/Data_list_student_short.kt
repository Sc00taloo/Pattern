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
    override fun get_data(): List<List<Any?>> { // Используем Any? для nullable типов
        val dataList = mutableListOf<List<Any?>>()
        dataList.add(get_names())  // Добавляем заголовки

        var index = 0
        for (student in data) {
            index += 1
            val row: List<Any?> = listOf(
                index, // Индекс
                student.FIO(), // Инициалы, может быть nullable
                student.git, // Git, может быть nullable
                student.contactMethod() // Контакт, может быть nullable
            )
            dataList.add(row)  // Добавляем строку как плоский список
        }

        return dataList
    }
}