package main.src

class Student(private val id: Int, private var lastName: String, private var firstName: String, private var middleName: String, private var phone: String? = null, private var telegram: String? = null, private var email: String? = null, private var git: String? = null){
    fun getID(): Int = id

    fun getLastName(): String = lastName
    fun setLastName(value: String) {
        lastName = value
    }

    fun getFirstName(): String = firstName
    fun setFirstName(value: String) {
        firstName = value
    }

    fun getMiddleName(): String = middleName
    fun setMiddleName(value: String) {
        middleName = value
    }

    fun getPhone(): String? = phone
    fun setPhone(value: String?) {
        phone = value
    }

    fun getTelegram(): String? = telegram
    fun setTelegram(value: String?) {
        telegram = value
    }

    fun getEmail(): String? = email
    fun setEmail(value: String?) {
        email = value
    }

    fun getGit(): String? = git
    fun setGit(value: String?) {
        git = value
    }

    fun getFullName(): String {
        return "$lastName $firstName $middleName".trim()
    }

    override fun toString(): String {
        return "Student(id=$id, fullName='${getFullName()}', phone=$phone, telegram=$telegram, email=$email, git=$git)"
    }
}