import main.src.Student

class Student_list_view {
    private lateinit var controller: Student_list_controller

    fun setController(controller: Student_list_controller) {
        this.controller = controller
    }
    // Метод для обновления таблицы студентов
    fun refreshStudentTable() {
        // Первая страница, 20 записей
        val studentShortList = controller.getStudentShortList(20, 1)
        println("Таблица обновлена: $studentShortList")
    }
    // Метод для удаления студента
    fun deleteSelectedStudent(studentId: Int) {
        controller.removeStudentById(studentId)
    }
    // Метод для редактирования студента
    fun editSelectedStudent(studentId: Int, updatedStudent: Student) {
        controller.replaceStudentById(studentId, updatedStudent)
    }
}