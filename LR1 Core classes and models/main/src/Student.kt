package main.src

data class Student(
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
        if(!isValidMiddleName(middleName) || middleName == ""){
            throw IllegalArgumentException("Неправильный формат отчества")
        }
        // Проверка номера телефон
        if (!Examination.isValidPhone(phone!!) || phone == "") {
            throw IllegalArgumentException("Неправильный формат номера телефона")
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

    private fun validate(vararg contacts: String) {
        if (contacts.any { isValidGit(it) || (isValidPhone(it) || isValidTelegram(it) || isValidEmail(it)) }) {
            //pass
        } else {
            throw IllegalArgumentException("Неверный формат контакта или гит")
        }
    }

    companion object Examination{
        fun createStudent(params: Map<String, Any?>): Student? {
            val id = params["id"] as? Int ?: return null
            val lastName = params["lastName"] as? String ?: return null
            val firstName = params["firstName"] as? String ?: return null
            val middleName = params["middleName"] as? String ?: return null
            val phone = params["phone"] as? String
            val telegram = params["telegram"] as? String
            val email = params["email"] as? String
            val git = params["git"] as? String
            if (validateFIO(lastName, firstName, middleName) &&
                (phone == null || isValidPhone(phone)) &&
                (telegram == null || isValidTelegram(telegram)) &&
                (email == null || isValidEmail(email)) &&
                (git == null || isValidGit(git))
            ) {
                return Student(id, lastName, firstName, middleName, phone, telegram, email, git)
            } else {
                println("Студент не создался")
                return null
            }
        }
        private fun validateFIO(lastName: String, firstName: String, middleName: String): Boolean {
            return isValidLastName(lastName) && isValidFirstName(firstName) && isValidMiddleName(middleName)
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
    }
}