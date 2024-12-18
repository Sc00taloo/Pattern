package main.src
import java.io.File
import java.lang.Exception

open class SuperStudent(
    var id: Int,
    var lastName: String,
    var firstName: String,
    var middleName: String,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var git: String? = null
) {
    init {
        if (!validate()) {
            throw IllegalArgumentException("У студента должен быть хотя-бы один контакт или гит.")
        }
    }
    // Проверка наличия гита
    fun hasGit(): Boolean {
        return git?.isNotBlank() == true
    }
    // Проверка наличия хотя бы одного контакта
    fun hasContact(): Boolean {
        return !phone.isNullOrBlank() || !telegram.isNullOrBlank() || !email.isNullOrBlank()
    }
    fun validate() : Boolean {
        return hasGit() && hasContact()
    }

    fun FIO(): String {
        val initials = "${firstName.first()}.${middleName.firstOrNull()?.toString() ?: ""}."
        val tet = "$lastName $initials".trim()
        return tet
    }
    fun contactMethod(): String{
        val contact = when {
            telegram != null -> "Telegram $telegram"
            email != null -> "Email $email"
            phone != null -> "Phone $phone"
            else -> "Нет доступных средств связи"
        }
        return contact
    }
    //Новое getInfo
    fun getInfo(): String {
        val fio = FIO()
        val contact = contactMethod()
        return "Инициаллы:$fio; Git:$git; Контакт:$contact"
    }

    override fun toString(): String {
        return "Student(id=$id, fullName='${getInfo()}', phone=$phone, telegram=$telegram, email=$email, git=$git)"
    }
}