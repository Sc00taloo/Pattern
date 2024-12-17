//import main.src.Student
//
//class Student_list_view(private var controller: Student_list_controller?) {
//    fun set_table_params(columnNames: Array<String>, wholeEntitiesCount: Int) {
//        // Логика задания параметров таблицы
//        println("Установлены параметры таблицы: ${columnNames.joinToString()}, количество записей: $wholeEntitiesCount")
//    }
//
//    fun set_table_data(dataTable: List<Array<Any>>) {
//        // Логика заполнения таблицы данными
//        println("Таблица заполнена данными:")
//        dataTable.forEach { row ->
//            println(row.joinToString())
//        }
//    }
//
//    fun setController(controller: Student_list_controller) {
//        this.controller = controller
//    }
//}