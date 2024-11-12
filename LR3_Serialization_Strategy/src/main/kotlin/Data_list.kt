import main.src.Student_short

open class Data_list<T>(val data: List<T> = mutableListOf()) {
    private val selectedIndices = mutableSetOf<Int>()
    // выделение элемента по номеру
    fun select(number: Int) {
        if (number in data.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Недопустимый индекс: $number")
        }
    }

    // получение id элементов
    fun get_selected(): List<Int> {
        return selectedIndices.toList()
    }

    // получение значений атрибутов объекта
    open fun get_names(): List<String> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }

    // получение данных
    open fun get_data(): List<List<Any>> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }
}