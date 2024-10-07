import main.src.Student

fun main() {
//    val student1 = Student(
//        id = 1,
//        lastName = "Иван",
//        firstName = "Попов",
//        middleName = "Викторович",
//        phone = "+78005553535",
//        git = "https://github.com/Sc00taloo"
//    )
//    val student2 = Student(
//        id = 2,
//        lastName = "Николай",
//        firstName = "Палыч",
//        middleName = "Победоносный",
//        email = "sobaka12@gmail.com",
//        git = "https://github.com/SlivkiShow"
//    )
//    val student3 = Student(
//        id = 3,
//        lastName = "Денисова",
//        firstName = "Анастасия",
//        middleName = "Давидовна",
//        telegram = "@denis",
//        git = "https://github.com/Denisochka"
//    )
//    val student4 = Student(
//        id = 4,
//        lastName = "Пономарёв",
//        firstName = "Максим",
//        middleName = "Фёдорович",
//        phone = "+79451239009",
//        telegram = "@pomafyo123",
//        email = "pomafyo123@mail.ru",
//        git = "https://github.com/Killer2016"
//    )
//    val student5 = Student(
//        id = 5,
//        lastName = "Моисеева",
//        firstName = "Арина",
//        middleName = "Арсентьева",
//        phone = "+79451239009",
//        telegram = "@moise11",
//        git = "https://github.com/TrueProg"
//    )
//
//    val student6 = Student(hashMapOf(
//        Pair("id", 6),
//        Pair("lastName", "Косян"),
//        Pair("firstName", "Артём"),
//        Pair("middleName", "Власович"),
//        Pair("phone","+79451239009"),
//        Pair("git","https://github.com/Github")
//    ))
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

    val studentString = "7,Пупкин,Иван,Сергеевич,+79001234567,@pup99,,https://github.com/Testikpup"
    val student = Student(studentString)
    println(student)
}