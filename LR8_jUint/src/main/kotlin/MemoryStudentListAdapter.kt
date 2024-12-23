//import main.src.Student
//import main.src.Student_short
//
//class MemoryStudentListAdapter : Student_List_Adapter {
//    private val students = mutableListOf<Student>()
//
//    override fun getStudentById(id: Int): Student? {
//        return students.find { it.id == id }
//    }
//
//    override fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student> {
//        val startIndex = (k - 1) * n
//        val endIndex = (startIndex + n).coerceAtMost(students.size)
//        val studentShortList = students.subList(startIndex, endIndex).map { Student(it) }
//        return Data_list_student_short(studentShortList, students.size)
//    }
//
//    override fun addStudent(student: Student): Int {
//        students.add(student)
//        return student.id
//    }
//
//    override fun replaceStudentById(id: Int, newStudent: Student) {
//        val index = students.indexOfFirst { it.id == id }
//        if (index != -1) {
//            students[index] = newStudent
//        }
//    }
//
//    override fun removeStudentById(id: Int) {
//        students.removeIf { it.id == id }
//    }
//
//    override fun getStudentShortCount(): Int {
//        return students.size
//    }
//}