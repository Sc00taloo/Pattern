import main.src.Student

fun main() {
    val student1 = Student.studentCreate(1, "Попов", "Иван", "Викторович",phone = "+78005553535", email = "vanya_popov12@list.ru")

    val student2 = Student(2, "Николай", "Палыч", "Победоносный", phone = "+79214441221")
    //student2.set_contacts(phone = "+79214441221")


    val student3 = Student.studentCreate(
        id = 3,
        lastName = "Денисова",
        firstName = "Анастасия",
        middleName = "Давидовна",
    )

    val student4 = Student.studentCreate(
        id = 4,
        lastName = "Пономарёв",
        firstName = "Максим",
        middleName = "Фёдорович",
        phone = "+79451239009",
        telegram = "@pomafyo123",
        email = "pomafyo123@mail.ru")
    //student4.set_contacts(phone = "+79451239009", telegram = "@pomafyo123", email = "pomafyo123@mail.ru")


    val student5 = Student.studentCreate(
        id = 5,
        lastName = "Моисеева",
        firstName = "Арина",
        middleName = "Арсентьева",
        phone = "+79451239009",
        telegram = "@moise11")
    //student5.set_contacts(phone = "+79451239009", telegram = "@moise11")

    println(student1)
    println(student2)
    println(student3)
    println(student4)
    println(student5)
}