import main.src.*
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentTestsTXT {

    private lateinit var adapter: Students_List_txt_adapter
    private val testFilePath = "src/test/resources/students.txt"

    @BeforeEach
    fun setup() {
        val txtInstance = Student_list_txt()
        adapter = Students_List_txt_adapter(txtInstance)
    }

    @Test
    fun testAddStudent() {
        val newStudent = Student(0, "Nowie", "Тест", "Тестович", "+79991234567", "@new", "test@mail.ru", "https://github.com/new")
        val newId = adapter.addStudent(newStudent)
        val addedStudent = adapter.getStudentById(newId)
        assertNotNull(addedStudent)
        assertEquals("Nowie", addedStudent?.lastName)
    }

    @Test
    fun testRemoveStudent() {
        adapter.removeStudentById(1)
        val student = adapter.getStudentById(1)
        assertNull(student, "Студент должен быть удалён")
    }

}