import main.src.Student
import main.src.Student_short
import java.sql.*
import Data_list

class Student_list_DB public constructor(private val connection: Connection) {
    companion object {
        private var instance: Student_list_DB? = null

        fun getInstance(): Student_list_DB {
            if (instance == null) {
                val connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "aktirf")
                instance = Student_list_DB(connection)
            }
            return instance!!
        }
    }

    // a. Получить объект класса Student по ID
    fun getStudentById(id: Int): Student_short? {
        val sql = "SELECT * FROM student WHERE id = ?"
        val statement = connection.prepareStatement(sql)
        statement.setInt(1, id)
        val resultSet = statement.executeQuery()

        return if (resultSet.next()) {
            Student_short(
                id = resultSet.getInt("id"),
                lastName = resultSet.getString("lastname"),
                firstName = resultSet.getString("firstname"),
                middleName = resultSet.getString("middlename"),
                phone = resultSet.getString("phone"),
                telegram = resultSet.getString("telegram"),
                email = resultSet.getString("email"),
                git = resultSet.getString("git")
            )
        } else {
            null
        }
    }

    // b. Получить список k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(k: Int, n: Int, gitSubstring: String? = null, filterType: String): Data_list<Student_short> {
        val sql = when (filterType) {
            "yes" -> if (gitSubstring != null) {
                "SELECT * FROM student WHERE git IS NOT NULL AND git LIKE ? ORDER BY id ASC LIMIT ? OFFSET ?"
            } else {
                "SELECT * FROM student WHERE git IS NOT NULL ORDER BY id ASC LIMIT ? OFFSET ?"
            }
            "no" -> "SELECT * FROM student WHERE git IS NULL ORDER BY id ASC LIMIT ? OFFSET ?"
            else -> "SELECT * FROM student ORDER BY id ASC LIMIT ? OFFSET ?"
        }

        val statement = connection.prepareStatement(sql)
        var paramIndex = 1

        if (filterType == "yes" && gitSubstring != null) {
            statement.setString(paramIndex++, "%$gitSubstring%")
        }

        statement.setInt(paramIndex++, k)
        statement.setInt(paramIndex, (n - 1) * k)  // Offset: (n-1) * k

        val resultSet = statement.executeQuery()
        val studentShortList = mutableListOf<Student_short>()

        while (resultSet.next()) {
            studentShortList.add(
                Student_short(
                    id = resultSet.getInt("id"),
                    lastName = resultSet.getString("lastname"),
                    firstName = resultSet.getString("firstname"),
                    middleName = resultSet.getString("middlename"),
                    phone = resultSet.getString("phone"),
                    telegram = resultSet.getString("telegram"),
                    email = resultSet.getString("email"),
                    git = resultSet.getString("git")
                )
            )
        }

        val totalStudents = getTotalStudents()
        return Data_list(studentShortList, totalStudents)
    }

    // c. Добавить объект класса Student в таблицу
    fun addStudent(student: Student_short) {
        val sql = "INSERT INTO student (lastname, firstname, middlename, phone, telegram, email, git) VALUES (?, ?, ?, ?, ?, ?, ?)"
        val statement = connection.prepareStatement(sql)
        statement.setString(1, student.lastName)
        statement.setString(2, student.firstName)
        statement.setString(3, student.middleName?.takeIf { it.isNotBlank() })
        statement.setString(4, student.phone?.takeIf { it.isNotBlank() })
        statement.setString(5, student.telegram?.takeIf { it.isNotBlank() })
        statement.setString(6, student.email?.takeIf { it.isNotBlank() })
        statement.setString(7, student.git)
        statement.executeUpdate()
    }

    // d. Заменить элемент по ID
    fun updateStudent(id: Int, student: Student_short) {
        val sql = "UPDATE student SET lastname = ?, firstname = ?, middlename = ?, phone = ?, telegram = ?, email = ?, git = ? WHERE id = ?"
        val statement = connection.prepareStatement(sql)
        statement.setString(1, student.lastName)
        statement.setString(2, student.firstName)
        statement.setString(3, student.middleName ?: "")
        statement.setString(4, student.phone?.ifEmpty { null })
        statement.setString(5, student.telegram?.ifEmpty { null })
        statement.setString(6, student.email?.ifEmpty { null })
        statement.setString(7, student.git)
        statement.setInt(8, id)
        statement.executeUpdate()
    }

    // e. Удалить элемент по ID
    fun deleteStudent(id: Int) {
        val sql = "DELETE FROM student WHERE id = ?"
        val statement = connection.prepareStatement(sql)
        statement.setInt(1, id)
        statement.executeUpdate()
    }

    // f. Получить количество элементов
    fun getTotalStudents(): Int {
        val sql = "SELECT COUNT(*) FROM student"
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(sql)

        return if (resultSet.next()) {
            resultSet.getInt(1)
        } else {
            0
        }
    }

    fun getAllStudent(): Data_list<Student_short>{
        val sql = "SELECT * FROM student"
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(sql)
        val studentShortList = mutableListOf<Student_short>()
        while (resultSet.next()) {
            studentShortList.add(
                Student_short(
                    id = resultSet.getInt("id"),
                    lastName = resultSet.getString("lastname"),
                    firstName = resultSet.getString("firstname"),
                    middleName = resultSet.getString("middlename"),
                    phone = resultSet.getString("phone"),
                    telegram = resultSet.getString("telegram"),
                    email = resultSet.getString("email"),
                    git = resultSet.getString("git")
                )
            )
        }
        val totalStudents = getTotalStudents()
        return Data_list(studentShortList, totalStudents)
    }
}