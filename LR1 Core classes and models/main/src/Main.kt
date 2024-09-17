import main.src.Student

fun main() {
    val student1 = Student.createStudent(mapOf("id" to 1, "lastName" to "Иван", "firstName" to "Попов", "middleName" to "Викторович", "phone" to "+78005553535", "git" to "https://github.com/Sc00taloo"))

    //val student2 = Student(2, "Николай", "Палыч", "Победоносный", phone = "+79214441221")
    val student2 = Student.createStudent(mapOf("id" to 2, "lastName" to "Николай", "firstName" to "Палыч", "middleName" to "Победоносный", "phone" to "+79214441221"))
    //student2.set_contacts(phone = "+79214441221")


//    val student3 = Student.studentCreate(
//        id = 3,
//        lastName = "Денисова",
//        firstName = "Анастасия",
//        middleName = "Давидовна",
//    )
    val student3 = Student.createStudent(mapOf("id" to 3, "lastName" to "Денисова", "firstName" to "Анастасия", "middleName" to "Давидовна"))

//    val student4 = Student.studentCreate(
//        id = 4,
//        lastName = "Пономарёв",
//        firstName = "Максим",
//        middleName = "Фёдорович",
//        phone = "+79451239009",
//        telegram = "@pomafyo123",
//        email = "pomafyo123@mail.ru")
    val student4 = Student.createStudent(mapOf("id" to 4, "lastName" to "Пономарёв", "firstName" to "Максим", "middleName" to "Фёдорович", "phone" to "+79451239009","telegram" to "@pomafyo123", "emal" to "pomafyo123@mail.ru"))
    //student4.set_contacts(phone = "+79451239009", telegram = "@pomafyo123", email = "pomafyo123@mail.ru")


//    val student5 = Student.studentCreate(
//        id = 5,
//        lastName = "Моисеева",
//        firstName = "Арина",
//        middleName = "Арсентьева",
//        phone = "+79451239009",
//        telegram = "@moise11")
    val student5 = Student.createStudent(mapOf("id" to 5, "lastName" to "Моисеева", "firstName" to "Арина", "middleName" to "Арсентьева", "phone" to "+79451239009", "telegram" to "@moise11"))
    //student5.set_contacts(phone = "+79451239009", telegram = "@moise11")

    println(student1)
    println(student2)
    println(student3)
    println(student4)
    println(student5)
}