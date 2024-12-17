import main.src.*
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentsTestsYAML {

    private lateinit var adapter: Student_list_yaml_adapter
    private val testYamlPath = "src/test/resources/student_yaml.yaml"

    @BeforeEach
    fun setup() {
        val yamlInstance = Student_list_YAML()
        adapter = Student_list_yaml_adapter(yamlInstance)
    }

    @Test
    fun testGetStudentById() {
        val student = adapter.getStudentById(4)
        assertNotNull(student)
        assertEquals("Пономарёв", student?.lastName)
    }

    @Test
    fun testAddStudent() {
        val newStudent = Student(0, "Тестов", "Николай", "Петрович", "+79991234567", "@test", "test@mail.ru", "https://github.com/test")
        val newId = adapter.addStudent(newStudent)
        val addedStudent = adapter.getStudentById(newId)
        assertNotNull(addedStudent)
        assertEquals("Тестов", addedStudent?.lastName)
    }

    @Test
    fun testRemoveStudent() {
        adapter.removeStudentById(4)
        val student = adapter.getStudentById(4)
        assertNull(student, "Студент должен быть удалён")
    }

    @Test
    fun testReplaceStudent() {
        val updatedStudent = Student(3, "Обновлённая", "Анастасия", "Игоревна", "+79990000000", "@update", "update@mail.ru", "https://github.com/updated")
        adapter.replaceStudentById(3, updatedStudent)
        val student = adapter.getStudentById(3)
        assertNotNull(student)
        assertEquals("Обновлённая", student?.lastName)
    }
}