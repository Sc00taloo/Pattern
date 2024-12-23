package main.src
import Student_List_Adapter
import java.io.File

class Student_list_txt : StudentListInterface  {
    override fun readFromFile(filePath: String): List<Student> {
        val students = mutableListOf<Student>()
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        file.forEachLine { line ->
            try {
                val student = Student(line)
                students.add(student)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: \"$line\"")
            }
        }

        return students // Возвращаем список студентов
    }

    override fun writeToFile(filePath: String, students: List<Student>) {
        val file = File(filePath)
        file.bufferedWriter(Charsets.UTF_8).use { writer ->
            students.forEach { student ->
                writer.write(
                    "${student.id};${student.lastName};${student.firstName};${student.middleName ?: ""};" +
                            "${student.phone ?: ""};${student.telegram ?: ""};${student.email ?: ""};${student.git ?: ""}"
                )
                writer.newLine()
            }
        }
    }
}