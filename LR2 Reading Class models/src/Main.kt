import main.src.Student
import main.src.Student_short
import main.src.SuperStudent

fun main() {
//    val input = "students.txt"
//    try {
//        val studentsFromFile = Student.read_from_txt(input)
//        println(studentsFromFile.forEach { println(it.getInfo()) })
//
//    } catch (e: Exception) {
//        println("Не удалось прочесть файл")
//    }
//
//    val output = "output.txt"
//    try {
//        Student.write_to_txt(output, Student.getStudent())
//        println("True")
//    } catch (e: Exception) {
//        println("False")
//    }
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

    // создание объекта Data_table
    val stringTable = Data_table(arrayOf(
        arrayOf(1,"Попов","Иван","Викторович","+78005553535",null,"iptru@mail.ru","https://github.com/Sc00taloo"),
        arrayOf(2,"Николай","Палыч","Победоносный","+79214326586",null,null,"https://github.com/SlivkiShow"),
        arrayOf(3,"Пономарёв","Максим","Максимович",null,"@pomafyo123","pomafyo123@mail.ru","https://github.com/Killer2016")
    ))

    // получение количества строк и столбцов
    println("Количество строк: ${stringTable.getRowCount()}")
    println("Количество столбцов: ${stringTable.getColumnCount()}")
    // отображение таблицы
    stringTable.displayTable()


    val students = listOf(
        Student_short(1,"Попов","Иван","Викторович","+78005553535","@scooty",null,"https://github.com/Sc00taloo"),
        Student_short(2,"Николай","Палыч","Победоносный",null,"@nikitochka","sobaka12@gmail.com","https://github.com/SlivkiShow"),
        Student_short(3,"Пономарёв","Максим","Максимович",null,null,"pomafyo123@mail.ru","https://github.com/Killer2016")
    )
    // создание объекта Data_list_student_short
    val dataList = Data_list_student_short(students)
    dataList.select(0)
    dataList.select(2)

    //dataList.select(4)

    // получение индексов
    val selectedIndices = dataList.get_selected()
    println("Выбранные индексы: $selectedIndices")

    // получение данных студентов
    val data = dataList.get_data()
    println("Данные студентов:")
    data.forEach { println(it) }

    // получение всего кроме id
    val names = dataList.get_names()
    println("Наименования атрибутов:")
    names.forEach { println(it) }
}