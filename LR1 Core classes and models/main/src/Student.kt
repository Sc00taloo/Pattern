package main.src

class Student(var id: Int, var lastName: String, var firstName: String, var middleName: String){
    var phone: String = ""
    var telegram: String = ""
    var email: String = ""
    var git: String = ""

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
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String, _telegram: String, _email: String, _git: String) : this(_id, _firstName, _lastName, _middleName) {
        validate(_phone, _telegram, _email, _git)
        this.phone = _phone
        this.telegram = _telegram
        this.email = _email
        this.git = _git
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String, _telegram: String, _email: String) : this(_id, _firstName, _lastName, _middleName) {
        validate(_phone, _telegram,_email)
        this.phone = _phone
        this.telegram = _telegram
        this.email = _email
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String, _telegram: String) : this(_id, _firstName, _lastName, _middleName) {
        validate(_phone, _telegram)
        this.phone = _phone
        this.telegram = _telegram
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String) : this(_id, _firstName, _lastName, _middleName) {
        validate(_phone)
        this.phone = _phone
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

    companion object {
        fun isValidPhone(phone: String): Boolean {
            return phone.matches(Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"))
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