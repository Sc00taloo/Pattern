import main.src.Student

fun main() {
    val student1 = Student.createStudent(
        mapOf(
            "id" to 1,
            "lastName" to "Иван",
            "firstName" to "Попов",
            "middleName" to "Викторович",
            "phone" to "+78005553535",
            "git" to "https://github.com/Sc00taloo"
        )
    )
    val student2 = Student.createStudent(
        mapOf(
            "id" to 2,
            "lastName" to "Николай",
            "firstName" to "Палыч",
            "middleName" to "Победоносный",
            "email" to "sobaka12@gmail.com",
            "git" to "https://github.com/SlivkiShow"
        )
    )
    val student3 = Student.createStudent(
        mapOf(
            "id" to 3,
            "lastName" to "Денисова",
            "firstName" to "Анастасия",
            "middleName" to "Давидовна",
            "telegram" to "@denis",
            "git" to "https://github.com/Denisochka"
        )
    )
    val student4 = Student.createStudent(
        mapOf(
            "id" to 4,
            "lastName" to "Пономарёв",
            "firstName" to "Максим",
            "middleName" to "Фёдорович",
            "phone" to "+79451239009",
            "telegram" to "@pomafyo123",
            "email" to "pomafyo123@mail.ru",
            "git" to "https://github.com/Killer2016"
        )
    )
    val student5 = Student.createStudent(
        mapOf(
            "id" to 5,
            "lastName" to "Моисеева",
            "firstName" to "Арина",
            "middleName" to "Арсентьева",
            "phone" to "+79451239009",
            "telegram" to "@moise11",
            "git" to "https://github.com/TrueProg"
        )
    )
    println(student1)
    println(student1?.validate())
    println(student2)
    println(student2?.validate())
    println(student3)
    println(student3?.validate())
    println(student4)
    println(student4?.validate())
    println(student5)
    println(student5?.validate())

    student2?.set_contacts(email = "nikolai1970@mail.ru")
    student5?.set_contacts(telegram = "@hackmoise11")
    println(student2)
    println(student2?.validate())
    println(student5)
    println(student5?.validate())

    try {
        student1?.set_contacts(phone = "+73124")
    } catch (e: IllegalArgumentException) {
        println("Ошибка: ${e.message}")
    }
}