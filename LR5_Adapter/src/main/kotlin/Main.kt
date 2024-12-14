import main.src.Student
import main.src.Student_list_JSON
import main.src.Student_list_txt

fun main() {
//    val input = "students.txt"
//    val studentsFromFile = Students_list_txt(input)
//
//    println(Student.students.toString())
//    println(studentsFromFile.getStudentShortCount())
//
//    studentsFromFile.sortStudents()
//    println(Student.students.toString())
//
//    studentsFromFile.getStudentById(1)
//
//    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    val student1 = "6,Косян,Артём,Власович,+79214325674,null,null,https://github.com/Kosyan"
//    studentsFromFile.replaceStudentById(3, student6)
//    println(Student.students.toString())
//
//    studentsFromFile.writeToFile()
//
//    studentsFromFile.removeStudentById(3)
//    println(Student.students.toString())


//    val json = "student_json.json"
//    val studentsFromFileJSON = Students_list_JSON(json)
//
//    println(studentsFromFileJSON.getStudent())
//    println(studentsFromFileJSON.getStudentShortCount())
//
//    studentsFromFileJSON.sortStudents()
//    println(studentsFromFileJSON.getStudent())
//
//    studentsFromFileJSON.getStudentById(1)
//
//    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    studentsFromFileJSON.replaceStudentById(3, student6)
//    println(studentsFromFileJSON.getStudent())
//
//    studentsFromFileJSON.writeToJson()
//
//    studentsFromFileJSON.removeStudentById(3)
//    println(studentsFromFileJSON.getStudent())


//    val yaml = "student_yaml.yaml"
//    val studentsFromFileYAML = Students_list_YAML(yaml)
//
//    println(studentsFromFileYAML.getStudent())
//    println(studentsFromFileYAML.getStudentShortCount())
//
//    studentsFromFileYAML.sortStudents()
//    println(studentsFromFileYAML.getStudent())
//
//    studentsFromFileYAML.getStudentById(1)
//
//    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    studentsFromFileYAML.replaceStudentById(3, student6)
//    println(studentsFromFileYAML.getStudent())
//
//    studentsFromFileYAML.writeToFile()
//
//    studentsFromFileYAML.removeStudentById(3)
//    println(studentsFromFileYAML.getStudent())

    // с txt файлом
//    val txtStrategy = Student_list_txt()
//    val studentsListTxt = StudentsList(txtStrategy)
//    studentsListTxt.readStudents("students.txt")
//    println(studentsListTxt.getStudentShortCount())
//
//    studentsListTxt.sortStudents()
//    println(studentsListTxt.getStudents())
//
//    studentsListTxt.getStudentById(1)
//
//    val student1 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    studentsListTxt.replaceStudentById(3, student1)
//    println(studentsListTxt.getStudents())
//
//    studentsListTxt.writeStudents("output.txt")
//
//    studentsListTxt.removeStudentById(3)
//    println(studentsListTxt.getStudents())


    // с json файлом
//    val jsonStrategy = Student_list_JSON()
//    val studentsListJson = StudentsList(jsonStrategy)
//    studentsListJson.readStudents("student_json.json")
//
//    println(studentsListJson.getStudentShortCount())
//
//    studentsListJson.sortStudents()
//    println(studentsListJson.getStudents())
//
//    studentsListJson.getStudentById(1)
//
//    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    studentsListJson.replaceStudentById(3, student6)
//    println(studentsListJson.getStudents())
//
//    studentsListJson.writeStudents("output.json")
//
//    studentsListJson.removeStudentById(3)
//    println(studentsListJson.getStudents())


    // с yaml файлом
//    val yamlStrategy = Student_list_YAML()
//    val studentsListYaml = StudentsList(yamlStrategy)
//    studentsListYaml.readStudents("student_yaml.yaml")
//    println(studentsListYaml.getStudentShortCount())
//
//    studentsListYaml.sortStudents()
//    println(studentsListYaml.getStudents())
//
//    studentsListYaml.getStudentById(1)
//
//    val student6 = Student(6,"Косян","Артём","Власович", phone = "+79214325674", git="https://github.com/Kosyan")
//    studentsListYaml.replaceStudentById(3, student6)
//    println(studentsListYaml.getStudents())
//
//    studentsListYaml.strategy=Student_list_JSON()
//    studentsListYaml.writeStudents("output_j.json")
//
//    studentsListYaml.removeStudentById(3)
//    println(studentsListYaml.getStudents())

//    val url = "jdbc:postgresql://localhost:5432/postgres"
//    val user = "postgres"
//    val password = "aktirf"  // Используйте ваш пароль
//
//    try {
//        val connection = DriverManager.getConnection(url, user, password)
//        println("Successfully connected to the database!")
//        connection.close()
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }

    //DB.executeSelect()
//    val studentDB = Student_list_DB.getInstance()
//    println(studentDB.getStudentById(2))
//    println(studentDB.getTotalStudents())
//
//    val student = Student(6,"Ковальчук","Никита","Витальевич", telegram = "@nikito", git="https://github.com/niknik")
//    studentDB.addStudent(student)
//    println(studentDB.getStudentById(6))


    val dbAdapter = Students_List_DB_Adapter(Student_list_DB.getInstance())
    val studentListDB = StudentList(dbAdapter)

    val txtAdapter = Students_List_txt_adapter(Student_list_txt())
    val studentListTxt = StudentList(txtAdapter)

    //println(studentListTxt.getStudentById(6))

    for (i in 0 until studentListTxt.getStudentShortCount() + 1) {
        val student = studentListTxt.getStudentById(i)
        if (student != null) {
            studentListDB.addStudent(student)
        }
    }
}