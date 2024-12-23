import main.src.Student_short;

import java.util.ArrayList;
import java.util.List;

public class MainWindowController {

    private final Student_list_DB database;
    private Data_list<Student_short> allStudents;

    public MainWindowController() {
        this.database = Student_list_DB.Companion.getInstance(); // Получаем экземпляр базы данных
    }

    public Data_list loadAllStudents() {
        try {
            // Предполагаем, что database.getAllStudents() возвращает полный список студентов
            allStudents = database.getAllStudent(); // Убедитесь, что это возвращает Data_list
            // Теперь используем getData() для итерации
            return allStudents;
        } catch (Exception e) {
            e.printStackTrace();
            allStudents = null; // Если произошла ошибка, оставляем список пустым
        }
        return null;
    }


    // Метод для получения студентов с поддержкой пагинации
    public List<Student_short> getStudents(int pageSize, int page) {
        try {
            Student_list_DB dbInstance = Student_list_DB.Companion.getInstance();
            return dbInstance.get_k_n_student_short_list(pageSize, page).getList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Возвращаем пустой список при ошибке
        }
    }

    private List<Student_short> fetchStudentsFromDataSource() {
        List<Student_short> students = new ArrayList<>();
        try {
            Student_list_DB dbInstance = Student_list_DB.Companion.getInstance();
            List<Student_short> fetched = dbInstance.get_k_n_student_short_list(50, 1).getList();
            students.addAll(fetched);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Data_list_student_short getTableData(int pageSize, int page) {
        try {
            // Получаем список студентов для текущей страницы
            List<Student_short> students = database.get_k_n_student_short_list(pageSize, page).getList();
            int totalStudents = database.getTotalStudents();

            // Возвращаем Data_list_student_short с полученными данными
            return new Data_list_student_short(students, totalStudents);
        } catch (Exception e) {
            e.printStackTrace();
            return new Data_list_student_short(new ArrayList<>(), 0);
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

}
