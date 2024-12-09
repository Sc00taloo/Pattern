import main.src.Student
import main.src.Student_list_txt

fun main() {
    val view = Student_list_view()
    val controller = Student_list_controller(view)

    view.refreshStudentTable()
}