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

    // получения имён атрибутов
    open fun get_names(): List<String> {
        return if (data.isEmpty()) {
            emptyList()
        } else {
            data.first()::class.java.declaredFields
                .map { it.name }
                .filter { it != "id" }
        }
    }

    // получение данных в виде списка списков
    open fun get_data(): List<List<Any>> {
        return if (data.isEmpty()) {
            emptyList()
        } else {
            val names = get_names().toMutableList().apply { add(0, "№") }
            val values = data.mapIndexed { index, element ->
                listOf(index + 1) + getValues(element)
            }
            listOf(names) + values
        }
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