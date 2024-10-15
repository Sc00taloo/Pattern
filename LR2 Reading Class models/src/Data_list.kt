import main.src.Student_short

open class Data_list<T>(private val data: List<Student_short> = mutableListOf()) {
    private val selectedIndices = mutableSetOf<Int>()
    // выделения элемента по номеру
    fun select(number: Int) {
        if (number in data.indices) {
            selectedIndices.add(number)
        }
    }

    // получение массив id выделенных элементов
    fun get_selected(): List<Int> {
        return selectedIndices.toList()
    }

    // получение данных в виде списка списков
    // паттерн применение
    open fun get_data(): List<List<Any>> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }

    // получение значений атрибутов объекта
    private fun getValues(element: Student_short): List<Any> {
        // все поля класса, кроме id
        return element::class.java.declaredFields
            .filter { it.name != "id" }
            .map { field ->
                field.isAccessible = true // делаем поле доступным
                field.get(element)
            }
    }

}