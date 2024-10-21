package main.src
import Data_list
import main.src.Student.Examination
import java.io.File

class Students_list_txt(private val filePath: String) {
    private val students : MutableList<Student> = mutableListOf()
    init {
        read_from_txt()
    }
    // a. Чтение всех значений из файла
    fun read_from_txt(): List<SuperStudent> {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }
        //val students = mutableListOf<SuperStudent>()
        file.forEachLine { line ->
            try{
                val student = Student(line)
                Student.students.add(student)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: \"$line\"")
            }
        }
        return Student.students
    }

    // b. Запись всех значений в файл
    fun write_to_txt() {
        val file = File("output.txt")
        file.bufferedWriter().use { writer ->
            Student.students.forEach { student ->
                writer.write(
                    "${student.id};${student.lastName};${student.firstName};${student.middleName ?: ""};" +
                            "${student.phone ?: ""};${student.telegram ?: ""};${student.email ?: ""};${student.git ?: ""}"
                )
                writer.newLine()
            }
        }
    }

    // c. Получить объект класса Student по ID
    fun getStudentById(id: Int): Student? {
        return Student.students.find { it.id == id }
    }

    // d. Получить список k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        val startIndex = (n - 1) * k
        val endIndex = startIndex + k
        val studentShortList = Student.students.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(students.size)).map { Student_short(it) }
        return Data_list(studentShortList)
    }

    // e. Сортировать элементы по набору ФамилияИнициалы
    fun sortStudents() {
        Student.students.sortBy { it.getInfo() }
    }

    // f. Добавить объект класса Student в список
    fun addStudent(student: Student, id: Int = students.maxOf { it.id } + 1) {
        val newStudent = student
        newStudent.id = id
        Student.students.add(newStudent)
    }

    // g. Заменить элемент списка по ID
    fun replaceStudentById(id: Int, newStudent: Student) {
        val newStud = newStudent
        newStud.id = id
        val ind = Student.students.indexOf(this.students.find { it.id == id })
        if (ind != -1) Student.students[ind] = newStud
        else this.addStudent(newStudent, id)
    }

    // h. Удалить элемент списка по ID
    fun removeStudentById(id: Int) {
        val index = Student.students.indexOfFirst { it.id == id }
        if (index != -1) {
            Student.students.removeAt(index)
        } else {
            throw NoSuchElementException("Студент с ID $id не найден.")
        }
    }

    // i. Получить количество элементов
    fun getStudentShortCount(): Int {
        return Student.students.size
    }
}