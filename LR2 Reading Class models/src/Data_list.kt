class Data_list<T : Comparable<T>>(private val data: MutableList<T> = mutableListOf()) {
    private val selectedIndices = mutableSetOf<Int>() // хранит индексы выделенных элементов

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
    fun get_names(): List<String> {
        return if (data.isEmpty()) {
            emptyList()
        } else {
            data.first()::class.java.declaredFields
                .map { it.name }
                .filter { it != "id" }
        }
    }

    // получение данных в виде списка списков
    fun getData(): List<List<Any>> {
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
    private fun getValues(element: T): List<Any> {
        // все поля класса, кроме id
        return element::class.java.declaredFields
            .filter { it.name != "id" }
            .map { field ->
                field.isAccessible = true // делаем поле доступным
                field.get(element)
            }
    }

}