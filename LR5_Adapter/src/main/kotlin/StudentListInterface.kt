package main.src

import main.src.Student
import main.src.Student_short

interface StudentListInterface {
    fun readFromFile(filePath: String): List<Student>
    fun writeToFile(filePath: String, students: List<Student>)
}