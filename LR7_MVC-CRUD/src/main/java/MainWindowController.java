import main.src.Student_short;

import java.util.ArrayList;
import java.util.List;

public class MainWindowController {

    private final Student_list_DB database;

    public MainWindowController() {
        this.database = Student_list_DB.Companion.getInstance(); // Получаем экземпляр базы данных
    }

    // Метод для получения студентов с поддержкой пагинации
    public List<Student_short> getStudents(int pageSize, int currentPage) {
        List<Student_short> students = fetchStudentsFromDataSource();
        // Вычисляем индексы для страницы
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(currentPage * pageSize, students.size());

        // Возвращаем только студентов для текущей страницы
        return students.subList(startIndex, endIndex);
    }

    private List<Student_short> fetchStudentsFromDataSource() {
        List<Student_short> students = new ArrayList<>();
        // Kotlin метод вызова get_k_n_student_short_list
        try {
            Student_list_DB dbInstance = Student_list_DB.Companion.getInstance();
            List<Student_short> fetched = dbInstance.get_k_n_student_short_list(50, 1).getList();
            students.addAll(fetched);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Метод для добавления нового студента
    public void addStudent(Student_short student) {
        try {
            database.addStudent(student); // Метод для добавления студента в базу данных
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для обновления информации о студенте
    public void updateStudent(Student_short student) {
        try {
            database.updateStudent(1,student); // Метод для обновления студента в базе данных
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления студента по его ID
    public void deleteStudent(int studentId) {
        try {
            database.deleteStudent(studentId); // Метод для удаления студента из базы данных
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для получения общего количества студентов
    public int getTotalRecords() {
        try {
            return database.getTotalStudents();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
