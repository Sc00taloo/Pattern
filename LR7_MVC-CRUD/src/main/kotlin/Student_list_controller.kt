import main.src.Student_short

class Student_list_controller {
    private lateinit var view: Student_list_view
    private val studentList = StudentList(MemoryStudentListAdapter())
    private var currentDataList: Data_list<Student_short>? = null

    // Связывание с view
    fun setView(view: Student_list_view) {
        this.view = view
    }

    fun refresh_data(pageSize: Int, pageNumber: Int) {
        // Получение данных из модели
        currentDataList = Data_list_student_short(
            studentList.get_k_n_student_short_list(pageSize, pageNumber).data,
            studentList.getStudentShortCount()
        )

        // Устанавливаем View как наблюдателя
        currentDataList?.setObserver(view)

        // Получение и преобразование данных для таблицы
        val columnNames = currentDataList?.get_names()?.toTypedArray() ?: arrayOf()
        val dataTable = currentDataList?.get_data() ?: listOf()

        // Уведомляем View
        currentDataList?.notify(columnNames, dataTable)
    }
}