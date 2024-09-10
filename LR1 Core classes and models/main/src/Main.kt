import main.src.Student

fun main() {
    val student1 = Student(1, "Попов", "Иван", "Викторович",
        "+78005553535",
        "@sc00taloo",
        "vanya_popov12@list.ru",
        "https://github.com/Sc00taloo"
    )

    val student2 = Student(2, "Николай", "Палыч", "Победоносный",
        _phone = "+79214441221"
    )

    val student3 = Student(
        id = 3,
        lastName = "Денисова",
        firstName = "Анастасия",
        middleName = "Давидовна",
    )

    val student4 = Student(
        _id = 4,
        _lastName = "Пономарёв",
        _firstName = "Максим",
        _middleName = "Фёдорович",
        _phone = "+79451239009",
        _telegram = "@pomafyo123",
        _email = "pomafyo123@mail.ru",
    )

    val student5 = Student(
        _id = 5,
        _lastName = "Моисеева",
        _firstName = "Арина",
        _middleName = "Арсентьева",
        _phone = "+79451239009",
        _telegram = "@moise11",
    )

    println(student1)
    println(student2)
    println(student3)
    println(student4)
    println(student5)
}