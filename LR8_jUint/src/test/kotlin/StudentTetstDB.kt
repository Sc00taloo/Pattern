import org.junit.jupiter.api.*
import main.src.Student
import java.sql.DriverManager
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentTestsDB{

    private lateinit var dbInstance: Student_list_DB
    private lateinit var adapter: Students_List_DB_Adapter

    @BeforeAll
    fun setup() {
        // Настройка подключения к тестовой базе данных
        val connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres",
            "postgres",
            "aktirf"
        )
        dbInstance = Student_list_DB.getInstance()
        adapter = Students_List_DB_Adapter(dbInstance)
    }

    @Test
    fun testAddStudent() {
        val student = Student(
            id = 0, // ID назначится автоматически
            lastName = "Иванов",
            firstName = "Петр",
            middleName = "Сергеевич",
            phone = "+79001112233",
            telegram = "@ivanovp",
            email = "ivanovp@mail.ru",
            git = "https://github.com/ivanovp"
        )

        val totalBefore = adapter.getStudentShortCount()
        adapter.addStudent(student)
        val totalAfter = adapter.getStudentShortCount()

        assertEquals(totalBefore + 1, totalAfter, "Количество студентов не увеличилось на 1")
    }

    @Test
    fun testReplaceStudentById() {
        val student = Student(
            id = 55,
            lastName = "Косян",
            firstName = "Артём",
            middleName = "Власович",
            phone = "+79214325674",
            telegram = null,
            email = null,
            git = "https://github.com/Kosyan"
        )

        val newId = adapter.addStudent(student)

        val updatedStudent = student.copy(
            lastName = "Смирнов",
            email = "smirnov@mail.ru"
        )

        adapter.replaceStudentById(newId, updatedStudent)
        val fetchedStudent = adapter.getStudentById(newId)

        assertNotNull(fetchedStudent, "Обновленный студент не найден")
        assertEquals("Смирнов", fetchedStudent?.lastName, "Фамилия не обновилась")
        assertEquals("smirnov@mail.ru", fetchedStudent?.email, "Email не обновился")
    }

    @Test
    fun testDeleteStudent() {
        val student = Student(
            id = 0, // ID назначится автоматически
            lastName = "Иванов",
            firstName = "Петр",
            middleName = "Сергеевич",
            phone = "+79001112233",
            telegram = "@ivanovp",
            email = "ivanovp@mail.ru",
            git = "https://github.com/ivanovp"
        )

        val newId = adapter.addStudent(student)
        val totalBefore = adapter.getStudentShortCount()

        adapter.removeStudentById(newId)
        val totalAfter = adapter.getStudentShortCount()

        val fetchedStudent = adapter.getStudentById(newId)
        assertEquals(totalBefore - 1, totalAfter, "Количество студентов не уменьшилось на 1")
        assertEquals(null, fetchedStudent, "Студент не был удален из базы данных")
    }
}