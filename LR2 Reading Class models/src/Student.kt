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
    //Новое getInfo
    fun getInfo(): String {
        val initials = "${firstName.first()}.${middleName.firstOrNull()?.toString() ?: ""}."
        val FIO = "$lastName $initials".trim()
        return "Инициалы:$FIO; git:$git; связь:${getContactInfo()}"
    }
    //Новое getInfo
    fun getContactInfo(): String {
        val contactMethod = when {
            telegram != null -> "Telegram $telegram"
            email != null -> "Email $email"
            phone != null -> "Phone $phone"
            else -> "Нет доступных средств связи"
        }
        return contactMethod
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

    //Вот это новое. Принимает на вход строку, которую парсит
//    constructor(studentString: String) : this(
//        id = studentString.split(",")[0].toInt(),
//        lastName = studentString.split(",")[1],
//        firstName = studentString.split(",")[2],
//        middleName = studentString.split(",")[3],
//        phone = studentString.split(",").getOrNull(4),
//        telegram = studentString.split(",").getOrNull(5),
//        email = studentString.split(",").getOrNull(6),
//        git = studentString.split(",").getOrNull(7)
//    )

    // Новый конструктор, принимающий строку
    constructor(studentString: String) : this(
        id = studentString.split(",")[0].toInt(),
        lastName = parseLastName(studentString),
        firstName = parseFirstName(studentString),
        middleName = parseMiddleName(studentString).toString(),
        phone = parsePhone(studentString),
        telegram = parseTelegram(studentString),
        email = parseEmail(studentString),
        git = parseGit(studentString)
    )

    companion object Examination{
        //Парсинг фамилии
        private fun parseLastName(studentString: String): String {
            val lastName = studentString.split(",").getOrNull(1) ?: throw IllegalArgumentException("Нет фамилии!")
            if (!isValidLastName(lastName)) throw IllegalArgumentException("Неправильный формат фамилии")
            return lastName
        }
        //Парсинг имя
        private fun parseFirstName(studentString: String): String {
            val firstName = studentString.split(",").getOrNull(2) ?: throw IllegalArgumentException("Нет имени!")
            if (!isValidFirstName(firstName)) throw IllegalArgumentException("Неправильный формат имени")
            return firstName
        }
        //Парсинг отчества
        private fun parseMiddleName(studentString: String): String? {
            val middelName = studentString.split(",").getOrNull(3)
            if (middelName != null && !isValidMiddleName(middelName)) throw IllegalArgumentException("Неправильный формат отчества")
            return middelName
        }
        //Парсинг телефона
        private fun parsePhone(studentString: String): String? {
            val phone = studentString.split(",").getOrNull(4)
            if (phone == null || phone == "") return null
            else if (!isValidPhone(phone)) throw IllegalArgumentException("Неправильный формат телефона")
            return phone
        }
        //Парсинг телеграмма
        private fun parseTelegram(studentString: String): String? {
            val telegram =  studentString.split(",").getOrNull(5)
            if (telegram == null || telegram == "") return null
            else if (!isValidTelegram(telegram)) throw IllegalArgumentException("Неправильный формат телеграмма")
            return telegram
        }
        //Парсинг почты
        private fun parseEmail(studentString: String): String? {
            val email = studentString.split(",").getOrNull(6)
            if (email == null || email == "") return null
            else if (!isValidEmail(email)) throw IllegalArgumentException("Неправильный формат почты")
            return email
        }
        //Парсинг гита
        private fun parseGit(studentString: String): String {
            val git = studentString.split(",").getOrNull(7) ?: throw IllegalArgumentException("Нет гита!")
            if (!isValidGit(git)) throw IllegalArgumentException("Неправильный формат гита")
            return git
        }

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