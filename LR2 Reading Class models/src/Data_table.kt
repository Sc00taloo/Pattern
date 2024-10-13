class Data_table(private val data: Array<Array<Any>>) {
    // Метод для получения количества строк
    fun getRowCount(): Int {
        return data.size
    }
    // Метод для получения количества столбцов
    fun getColumnCount(): Int {
        return if (data.isNotEmpty()) data[0].size else 0
    }
    // Метод для получения значения по индексу
    fun getValue(row: Int, column: Int): Any? {
        return if (row in data.indices && column in data[row].indices) {
            data[row][column]
        } else {
            null // если индексы выходят за пределы
        }
    }
}