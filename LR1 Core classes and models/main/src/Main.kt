import main.src.Student

fun main() {
    val student1 = Student(
        id = 1,
        lastName = "Попов",
        firstName = "Иван",
        middleName = "Викторович",
        phone = "+78005553535",
        telegram = "@sc00taloo",
        email = "vanya_popov12@list.ru",
        git = "https://github.com/Sc00taloo"
    )

    val student2 = Student(
        id = 2,
        lastName = "Николай",
        firstName = "Палыч",
        middleName = "Победоносный",
        phone = "+79214441221"
    )

    val student3 = Student(
        id = 3,
        lastName = "Денисова",
        firstName = "Анастасия",
        middleName = "Давидовна",
        email = "davidovna2024@gmail.com"
    )

    val student4 = Student(
        id = 4,
        lastName = "Пономарёв",
        firstName = "Максим",
        middleName = "Фёдорович",
        email = "pomafyo123@mail.ru",
        git = "https://github.com/MAXimus"
    )

    val student5 = Student(
        id = 5,
        lastName = "Моисеева",
        firstName = "Арина",
        middleName = "Арсентьева",
        phone = "+79451239009",
        email = "moise11@gmail.com",
    )

    println(student1)
    println(student2)
    println(student3)
    println(student4)
    println(student5)
}