package main.src

class Student_short(
    var id: Int,
    var FIO: String,
    var git: String,
    var contact: String
) {
    // Конструктор, принимающий объект класса Student
    constructor(student: Student) : this(
        id = student.id,
        FIO = "${student.lastName} ${student.firstName.first()}.${student.middleName.firstOrNull()}.".trim(),
        git = student.git.toString(),
        contact = student.getContactInfo()
    )

    // Конструктор, принимающий ID и строку с информацией
    constructor(id: Int, studentString: String) : this(
        id = id,
        FIO = parseFIO(studentString),
        git = parseGit(studentString).toString(),
        contact = parseContact(studentString)
    )

    companion object {
        private fun parseFIO(studentString: String): String {
            val parts = studentString.split(",")
            val lastName = parts[0].trim()
            val firstName = parts[1].trim()
            val middleName = parts.getOrNull(2)?.trim() ?: ""
            val initials = "${firstName.first()}.${middleName.firstOrNull()?.toString() ?: ""}."
            return "$lastName $initials".trim()
        }

        private fun parseGit(studentString: String): String? {
            return studentString.split(",").getOrNull(6)?.trim()
        }

        private fun parseContact(studentString: String): String {
            val contactMethod = when {
                studentString.split(",").getOrNull(3) != null -> "Phone ${studentString.split(",")[3]}"
                studentString.split(",").getOrNull(4) != null -> "Telegram ${studentString.split(",")[4]}"
                studentString.split(",").getOrNull(5) != null -> "Email ${studentString.split(",")[5]}"
                else -> "Нет доступных средств связи"
            }
            return contactMethod
        }
    }

    override fun toString(): String {
        return "Student_short(id=$id, Инициалы='$FIO', git=$git, контакт='$contact')"
    }
}