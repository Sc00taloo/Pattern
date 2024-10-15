import main.src.Student_short

class Data_list_student_short(students: List<Student_short>) : Data_list<Student_short>(students) {

    // Переопределяем метод для получения наименований атрибутов
    override fun get_names(): List<String> {
        return listOf("Фамилия и Инициалы", "git", "contact")
    }

    // Переопределяем метод для создания данных
    override fun get_data(): List<List<Any>> {
        val data = mutableListOf<List<Any>>()

        // Добавляем сгенерированные номера и атрибуты для каждого студента
        for (index in super.get_selected()) {
            val student = data[index] // Получаем студента по индексу
            val row = listOf(
                (index + 1).toString(), // № по порядку
                "${student}"
            )
            data.add(row)
        }

        return data
    }
}