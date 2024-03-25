package Hibernate.hw;


import java.util.List;

public class Homework {

    /**
     * 1. Создать сущность Student с полями:
     * 1.1 id - int
     * 1.2 firstName - string
     * 1.3 secondName - string
     * 1.4 age - int
     * 2. Подключить hibernate. Реализовать простые запросы: Find(by id), Persist, Merge, Remove
     * 3. Попробовать написать запрос поиска всех студентов старше 20 лет (session.createQuery)
     */
    public static void main(String[] args) {
        ConnectStudentDB connectStudentDB = new ConnectStudentDB();

        List<StudentNew> studentsOlderThan20 = connectStudentDB.findStudentsOlderThan20();

        for (StudentNew studentNew : studentsOlderThan20) {
            System.out.println(studentNew.getFirstName() + " " + studentNew.getLastName() + " is older than 20");
        }
    }
}