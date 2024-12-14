import main.src.Student_list_JSON

fun main() {
//    val controller = Student_list_controller()
//    val view = Student_list_view(controller)
//    controller.setView(view)
//
//    // Имитируем обновление данных
//    controller.refresh_data(pageSize = 2, pageNumber = 1)


//    val dbInstance = Student_list_DB.getInstance()
//    val dbAdapter = Students_List_DB_Adapter(dbInstance)
//
//    val jsonAdapter = Student_list_json_adapter(Student_list_JSON())
//    val pageSize = 3
//    val pageNumber = 1
//
//    println("BD")
//    val dbData = dbAdapter.get_k_n_student_short_list(pageNumber, pageSize)
//    dbData.data.forEach { student ->
//        println("${student.id}: ${student.lastName} ${student.firstName} ${student.middleName}")
//    }
//    println("${dbData.wholeEntitiesCount}")
//
//
//    println("\nJSON")
//    val jsonData = jsonAdapter.get_k_n_student_short_list(pageNumber, pageSize)
//    jsonData.data.forEach { student ->
//        println("${student.id}: ${student.lastName} ${student.firstName} ${student.middleName}")
//    }
//    println("${jsonData.wholeEntitiesCount}")


    val dbInstance = Student_list_DB.getInstance()
    val dbAdapter = Students_List_DB_Adapter(dbInstance)
    val controller = Student_list_controller(dbAdapter)

    val pageSize = 3
    val pageNumber = 1

    // Try
    val result = controller.get_k_n_student_short_list(pageNumber, pageSize)

    if (result != null) {
        println("Students:")
        result.data.forEach {
            println("${it.id}: ${it.lastName} ${it.firstName} ${it.middleName}")
        }
        println("${result.wholeEntitiesCount}")
    } else {
        println("Sad (")
    }
}