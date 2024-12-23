import main.src.Student
import main.src.Student_short

interface Student_List_Adapter {
    fun getStudentById(id: Int): Student_short?
    fun get_k_n_student_short_list(n: Int, k: Int,gitSubstring: String?, filterType: String): Data_list<Student_short>
    fun addStudent(student: Student_short): Int
    fun replaceStudentById(id: Int, newStudent: Student_short)
    fun removeStudentById(id: Int)
    fun getStudentShortCount(): Int
}