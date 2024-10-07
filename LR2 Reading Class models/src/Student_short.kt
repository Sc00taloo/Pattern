package main.src

class Student_short(
    id: Int,
    lastName: String,
    firstName: String,
    middleName: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
):SuperStudent(id, lastName, firstName, middleName, phone, telegram, email, git) {
    // Конструктор, принимающий объект класса Student
    constructor(student: Student) : this(
        id = student.id,
        lastName = student.lastName,
        firstName = student.firstName,
        middleName = student.middleName,
        phone = student.phone,
        telegram = student.telegram,
        email = student.email,
        git = student.git
    )
    // Конструктор, принимающий ID и строку с информацией
    constructor(id: Int, studentString: String) : this(
        id = id,
        lastName = studentString.split(",")[0],
        firstName = studentString.split(",")[1],
        middleName = studentString.split(",")[2],
        phone = studentString.split(",")[3],
        telegram = studentString.split(",")[4],
        email = studentString.split(",")[5],
        git = studentString.split(",")[6]
    )
    override fun toString(): String {
        return "Student_short(id=$id, Инициалы='${getInfo()}', git=$git, контакт='${getContactInfo()}')"
    }
}