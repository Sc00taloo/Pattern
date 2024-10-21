import main.src.Student
import main.src.Student_short
import main.src.Students_list_txt
import main.src.Students_list_JSON
import main.src.SuperStudent

fun main() {
//    val input = "students.txt"
//    val studentsFromFile = Students_list_txt(input)
//
//    println(Student.students.toString())
//    println(studentsFromFile.getStudentShortCount())
//
//    studentsFromFile.sortStudents()
//    println(Student.students.toString())
//
//    studentsFromFile.getStudentById(1)
//
//    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    val student1 = "6,Косян,Артём,Власович,+79214325674,null,null,https://github.com/Kosyan"
//    studentsFromFile.replaceStudentById(3, student6)
//    println(Student.students.toString())
//
//    studentsFromFile.write_to_txt()
//
//    studentsFromFile.removeStudentById(3)
//    println(Student.students.toString())

    val json = "student_json.json"
    val studentsFromFileJSON = Students_list_JSON(json)

    println(studentsFromFileJSON.getStudent())
    println(studentsFromFileJSON.getStudentShortCount())

    studentsFromFileJSON.sortStudents()
    println(studentsFromFileJSON.getStudent())

    studentsFromFileJSON.getStudentById(1)

    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
    studentsFromFileJSON.replaceStudentById(3, student6)
    println(studentsFromFileJSON.getStudent())

    studentsFromFileJSON.writeToJson()

    studentsFromFileJSON.removeStudentById(3)
    println(studentsFromFileJSON.getStudent())

}