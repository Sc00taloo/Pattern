import main.src.Student
import main.src.Student_short

class Student_list_controller(private val view: Student_list_view) {
    private val studentList: StudentList
    init {
        val adapter = MemoryStudentListAdapter()
        studentList = StudentList(adapter)
        // Устанавливаем ссылку на контроллер в view
        view.setController(this)
    }

    // Логика для добавления студента
    fun addStudent(student: Student) {
        studentList.addStudent(student)
        view.refreshStudentTable()
    }
    // Логика для удаления студента
    fun removeStudentById(id: Int) {
        studentList.removeStudentById(id)
        view.refreshStudentTable()
    }
    // Логика для получения краткой информации о студентах
    fun getStudentShortList(pageSize: Int, pageNumber: Int): Data_list<Student_short> {
        return studentList.get_k_n_student_short_list(pageSize, pageNumber)
    }
    // Логика для замены студента
    fun replaceStudentById(id: Int, newStudent: Student) {
        studentList.replaceStudentById(id, newStudent)
        view.refreshStudentTable()
    }
    // Получение общего количества студентов
    fun getStudentCount(): Int {
        return studentList.getStudentShortCount()
    }
    // Получение полного объекта студента по ID
    fun getStudentById(id: Int): Student? {
        return studentList.getStudentById(id)
    }
}