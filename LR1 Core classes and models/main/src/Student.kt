package main.src

class Student(
    var id: Int,
    var lastName: String,
    var firstName: String,
    var middleName: String,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var git: String? = null)
{
    init{
        if(!isValidLastName(lastName) || lastName == ""){
            throw IllegalArgumentException("Неправильный формат фамилия")
        }
        if(!isValidFirstName(firstName) || firstName == ""){
            throw IllegalArgumentException("Неправильный формат имя")
        }
        if (phone != null && !isValidPhone(phone!!)) {
            throw IllegalArgumentException("Неправильный формат телефона")
        }
    }

    fun set_contacts(phone: String? = null, telegram: String? = null, email: String? = null, git: String? = null) {
        if (phone != null) {
            if (isValidPhone(phone)) {
                this.phone = phone
            } else {
                throw IllegalArgumentException("Неправильный формат номера телефона")
            }
        }
        if (telegram != null) {
            if (isValidTelegram(telegram)) {
                this.telegram = telegram
            } else {
                throw IllegalArgumentException("Неверный формат телеграма")
            }
        }
        if (email != null) {
            if (isValidEmail(email)) {
                this.email = email
            } else {
                throw IllegalArgumentException("Неверный формат почты")
            }
        }
        if (git != null) {
            if (isValidGit(git)) {
                this.git = git
            } else {
                throw IllegalArgumentException("Неверный формат гита")
            }
        }
    }

    fun getFullName(): String {
        return "$lastName $firstName $middleName".trim()
    }

    override fun toString(): String {
        return "Student(id=$id, fullName='${getFullName()}', phone=$phone, telegram=$telegram, email=$email, git=$git)"
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

    constructor(studentArgs: HashMap<String,Any?>) : this(
        id = studentArgs["id"] as Int,
        firstName = studentArgs["firstName"].toString(),
        lastName = studentArgs["lastName"].toString(),
        middleName = studentArgs["middleName"].toString(),
        phone = studentArgs.getOrDefault("phone",null) as String?,
        telegram = studentArgs.getOrDefault("telegram",null) as String?,
        email = studentArgs.getOrDefault("email",null) as String?,
        git = studentArgs.getOrDefault("git",null) as String?
    )

    companion object Examination{
        fun isValidPhone(phone: String): Boolean {
            return phone.matches(Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{10}$"))
        }
        fun isValidTelegram(telegram: String): Boolean {
            return telegram.matches(Regex("^@[0-9a-zA-Z]{3,}$"))
        }
        fun isValidEmail(email: String): Boolean {
            return email.matches(Regex("^([a-zA-Z0-9_-]{3,}@[a-zA-Z]+\\.[a-zA-Z]+)$"))
        }
        fun isValidGit(git: String): Boolean {
            return git.matches(Regex("^https://github\\.com/[a-zA-Z0-9_-]+/?\$"))
        }
        fun isValidFirstName(firstName: String): Boolean {
            return firstName.matches(Regex("^[А-ЯA-ZЁ]{1}[а-яa-zё]{2,}$"))
        }
        fun isValidLastName(lastName: String): Boolean {
            return lastName.matches(Regex("^[А-ЯA-ZЁ]{1}[а-яa-zё]{2,}$"))
        }
        fun isValidMiddleName(middleName: String): Boolean {
            return middleName.matches(Regex("^[А-ЯA-ZЁ]{1}[а-яa-zё]{2,}$"))
        }

        private fun validateFIO(lastName: String, firstName: String, middleName: String): Boolean {
            return isValidLastName(lastName) && isValidFirstName(firstName) && isValidMiddleName(middleName)
        }
    }
}



















class Person {
    var name: String
    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    constructor(name: String) : this(name, 0)
}