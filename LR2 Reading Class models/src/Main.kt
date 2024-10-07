import main.src.Student
import main.src.Student_short
import main.src.SuperStudent

fun main() {
    val input = "students.txt"
    try {
        val studentsFromFile = Student.read_from_txt(input)
        println(studentsFromFile.forEach { println(it.getInfo()) })

    } catch (e: Exception) {
        println("Не удалось прочесть файл")
    }

    val output = "output.txt"
    try {
        Student.write_to_txt(output, Student.getStudent())
        println("True")
    } catch (e: Exception) {
        println("False")
    }
    //val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    println(student1)
//    println(student1.validate())
//    println(student2)
//    println(student2.validate())
//    println(student3)
//    println(student3.validate())
//    println(student4)
//    println(student4.validate())
//    println(student5)
//    println(student5.validate())
//    println(student6)
//    println(student6.validate())
//
//    student2.set_contacts(email = "nikolai1970@mail.ru")
//    student5.set_contacts(telegram = "@hackmoise11")
//    println(student2)
//    println(student2.validate())
//    println(student5)
//    println(student5.validate())
//
//    try {
//        student1.set_contacts(phone = "+73124")
//    } catch (e: IllegalArgumentException) {
//        println("Ошибка: ${e.message}")
//    }

//    val studentString = "Пупкин,Иван,Сергеевич,+79001234567,@pup99,,https://github.com/Testikpup"
//    val studentShort1 = Student_short(2,studentString)
//    println(studentShort1)
//
//    val studentShort2 = Student_short(student6)
//    println(studentShort2)
    //val student = Student(studentString)
    //println(student.getInfo())
    //println(student)
}