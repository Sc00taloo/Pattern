package main.src

class Student(val id: Int, var lastName: String, var firstName: String, var middleName: String? = null, var phone: String? = null, var telegram: String? = null, var email: String? = null, var git: String? = null){
    fun getId(): Int = id

    fun getLastName(): String = lastName
    fun setLastName(value: String) {
        lastName = value
    }

    fun getFirstName(): String = firstName
    fun setFirstName(value: String) {
        firstName = value
    }

    fun getMiddleName(): String? = middleName
    fun setMiddleName(value: String?) {
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
        return "$lastName $firstName ${middleName ?: ""}".trim()
    }
}