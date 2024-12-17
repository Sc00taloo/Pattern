import main.src.Student
import main.src.Student_list_JSON
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentTestsJSON {

    private lateinit var studentAdapter: Student_list_json_adapter
    private val testJsonPath = "src/test/resources/students.json"

    @BeforeEach
    fun setUp() {
        val jsonInstance = Student_list_JSON()
        studentAdapter = Student_list_json_adapter(jsonInstance)
    }

    @Test
    fun testGetStudentById() {
        val student = studentAdapter.getStudentById(1)
        assertNotNull(student, "Студент с id=1 должен существовать")
        assertEquals("Попов", student.lastName, "Фамилия студента должна совпадать")
        assertEquals("Иван", student.firstName, "Имя студента должно совпадать")
    }

    @Test
    fun testAddStudent() {
        val newStudent = Student(
            id = 0,
            firstName = "Тестов",
            lastName = "Тест",
            middleName = "Тестович",
            phone = "+78001234567",
            email = "test@mail.com",
            telegram = "@testuser",
            git = "https://github.com/testuser"
        )

        val newId = studentAdapter.addStudent(newStudent)
        val addedStudent = studentAdapter.getStudentById(newId)

        assertNotNull(addedStudent, "Добавленный студент должен существовать")
        assertEquals("Тестов", addedStudent?.firstName, "Фамилия нового студента должна совпадать")
        assertEquals("@testuser", addedStudent?.telegram, "Telegram нового студента должен совпадать")
    }

    @Test
    fun testDeleteStudent() {
        val initialSize = studentAdapter.getStudentShortCount()

        studentAdapter.removeStudentById(2)
        val deletedStudent = studentAdapter.getStudentById(2)

        assertNull(deletedStudent, "Студент с id=2 должен быть удален")
        assertEquals(
            initialSize,
            studentAdapter.getStudentShortCount(),
            "Общее количество студентов должно уменьшиться на 1"
        )
    }

    @Test
    fun testReplaceStudentById() {
        val updatedStudent = Student(
            id = 3,
            firstName = "Обновлённая",
            lastName = "Анастасия",
            middleName = "Новая",
            phone = "+79991234567",
            email = "new@mail.com",
            telegram = "@newtelegram",
            git = "https://github.com/newgit"
        )

        studentAdapter.replaceStudentById(3, updatedStudent)
        val student = studentAdapter.getStudentById(3)

        assertNotNull(student, "Студент с id=3 должен быть обновлен")
        assertEquals("Обновлённая", student?.firstName, "Фамилия студента должна быть обновлена")
        assertEquals("@newtelegram", student?.telegram, "Telegram студента должен быть обновлён")
    }
}