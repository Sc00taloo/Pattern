import main.src.Student
import main.src.Student_list_txt

fun main() {
    val controller = Student_list_controller()
    val view = Student_list_view(controller)
    controller.setView(view)

    // Имитируем обновление данных
    controller.refresh_data(pageSize = 2, pageNumber = 1)
}