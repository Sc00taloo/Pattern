import main.src.Student_short

open class Data_list<T>(val data: List<T> = mutableListOf(), val wholeEntitiesCount: Int) {
    private val selectedIndices = mutableSetOf<Int>()

    fun select(number: Int) {
        if (number in data.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Недопустимый индекс: $number")
        }
    }

    fun get_selected(): List<Int> {
        return selectedIndices.toList()
    }

    open fun get_names(): List<String> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }

    open fun get_data(): List<List<Any?>> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }

    private var observer: Student_list_view? = null

    open fun setObserver(view: Student_list_view) {
        this.observer = view
    }

    open fun notify(columnNames: Array<String>, dataTable: List<List<Any>>) {
        val convertedTable = dataTable.map { it.toTypedArray() }
        observer?.set_table_params(columnNames, wholeEntitiesCount)
        observer?.set_table_data(convertedTable)
    }

    fun getList(): List<T> {
        return data
    }
}