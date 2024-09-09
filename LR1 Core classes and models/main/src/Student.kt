package main.src

class Student(val id: Int, var lastName: String, var firstName: String, var middleName: String, var phone: String? = null, var telegram: String? = null, var email: String? = null, var git: String? = null){
    fun getFullName(): String {
        return "$lastName $firstName $middleName".trim()
    }

    override fun toString(): String {
        return "Student(id=$id, fullName='${getFullName()}', phone=$phone, telegram=$telegram, email=$email, git=$git)"
    }
}