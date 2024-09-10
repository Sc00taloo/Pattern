package main.src

class Student(var id: Int, var lastName: String, var firstName: String, var middleName: String){
    var phone: String = ""
    var telegram: String = ""
    var email: String = ""
    var git: String = ""

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String, _telegram: String, _email: String, _git: String) : this(_id, _firstName, _lastName, _middleName) {
        if (!isValidPhone(_phone) || _phone == "") {
            throw IllegalArgumentException("Неправильный формат номера телефона")
        }
        this.phone = _phone
        this.telegram = _telegram
        this.email = _email
        this.git = _git
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String, _telegram: String, _email: String) : this(_id, _firstName, _lastName, _middleName) {
        if (!isValidPhone(_phone) || _phone == "") {
            throw IllegalArgumentException("Неправильный формат номера телефона")
        }
        this.phone = _phone
        this.telegram = _telegram
        this.email = _email
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String, _telegram: String) : this(_id, _firstName, _lastName, _middleName) {
        if (!isValidPhone(_phone) || _phone == "") {
            throw IllegalArgumentException("Неправильный формат номера телефона")
        }
        this.phone = _phone
        this.telegram = _telegram
    }

    constructor(_id: Int, _lastName: String, _firstName: String, _middleName: String, _phone: String) : this(_id, _firstName, _lastName, _middleName) {
        if (!isValidPhone(_phone) || _phone == "") {
            throw IllegalArgumentException("Неправильный формат номера телефона")
        }
        this.phone = _phone
    }

    fun getFullName(): String {
        return "$lastName $firstName $middleName".trim()
    }

    override fun toString(): String {
        return "Student(id=$id, fullName='${getFullName()}', phone=$phone, telegram=$telegram, email=$email, git=$git)"
    }

    companion object {
        fun isValidPhone(phone: String): Boolean {
            return phone.matches(Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"))
        }
    }
}