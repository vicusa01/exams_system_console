package com.company;

import com.company.data.Data;
import com.company.entities.*;
import com.company.utils.AuthorizationHandler;
import com.company.utils.FileWorker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.company.data.Data.*;

public class Main {

    static AuthorizationHandler authorizationHandler;

    static void printMenu() {
        System.out.println("Выберите режим работы: ");
        System.out.println("1. Войти в систему как преподаватель");
        System.out.println("2. Регистрация (для студентов)");
        System.out.println("0. Выход");
    }

    public static void writeExampleInfo() throws FileNotFoundException {

        File fileinputstream1 = new File(ENROLLEE_FILENAME);
        boolean enrolleeFileBodyExists = fileinputstream1.length() != 0;

        if(enrolleeFileBodyExists)
        {
            return;
        }


        FileWorker worker = new FileWorker();
        List<TeacherUser> list = new ArrayList<>();
        list.add(new TeacherUser("petrov@bsuir.by", "123456"));
        list.add(new TeacherUser("alexeev@bsuir.by", "654321"));
        list.add(new TeacherUser("smirnov@bsuir.by", "987654"));
        worker.writeObject(USERSTORAGE_FILENAME, list);
        List<Faculty> faculties = new ArrayList<>();
        Faculty fkp = new Faculty("ФКП", "petrov@bsuir.by", 20);
        fkp.addExam(new Exam("Математика", "Продифференцировать константу и дважды транспонировать матрицу"));
        fkp.addExam(new Exam("Физика", "Вычислить импульс яблока при ударе по голове Ньютону, " +
                "если масса яблока 0.2кг, а высота падения - 4 м"));
        Faculty ksis = new Faculty("КСИС", "alexeev@bsuir.by", 30);
        ksis.addExam(new Exam("Математика", "Расскажите о функциях многих переменных"));
        ksis.addExam(new Exam("Информатика", "Способы задания графа"));
        Faculty fre = new Faculty("ФРЭ", "smirnov@bsuir.by", 15);
        fre.addExam(new Exam("Физика", "Расскажите о полупроводниках"));
        fre.addExam(new Exam("Радиоэлектроника", "Усилители на биполярных транзисторах"));
        faculties.add(fkp);
        faculties.add(ksis);
        faculties.add(fre);

        List<Enrollee> enrollees = new ArrayList<>();
        Enrollee enrollee1 = new Enrollee("Валерий Смирнов", "ФКП");
        enrollee1.addAnswer(new Answer("Математика", "Очень смешно) 0, та же матрица"));
        enrollee1.addAnswer(new Answer("Физика", "p=mv; gh=1/2*v^2; v = sqrt(2gh) ~ 9 м/с, " +
                "тогда импульс примерно 4.5кг*м/с"));
        Enrollee enrollee2 = new Enrollee("Алексей Федотов", "ФКП");
        enrollee2.addAnswer(new Answer("Математика", "Производная константы 0, матрица не изменится"));
        enrollee2.addAnswer(new Answer("Физика", "p=mv; gh=1/2*v^2; v = sqrt(2gh) ~ 9 м/с, " +
                "тогда импульс примерно 4.5кг*м/с"));

        Enrollee enrollee3 = new Enrollee("Павел Бизулин", "КСИС");
        enrollee3.addAnswer(new Answer("Математика", "Функцией многих переменных называется закон, по " +
                "которому каждому из значений независимых переменных  (аргументов) из области определения соответствует " +
                "значение зависимой переменной  (функции)."));
        enrollee3.addAnswer(new Answer("Информатика", "геометрический (рисунки, схемы, диаграммы), " +
                "простое перечисление вершин и ребер, табличный"));

        Enrollee enrollee4 = new Enrollee("Михаил Иванов", "КСИС");
        enrollee4.addAnswer(new Answer("Математика", "ФМП - закон, по " +
                "которому каждому из значений независимых переменных  (аргументов) из области определения соответствует " +
                "значение зависимой переменной  (функции)."));
        enrollee4.addAnswer(new Answer("Информатика", "перечисление всех рёбер, таблица смежности" +
                ", таблица инцидентности, др"));

        Enrollee enrollee5 = new Enrollee("Алекснадр Петров", "ФРЭ");
        enrollee5.addAnswer(new Answer("Физика", "Полупроводники — материалы, по своей удельной " +
                "проводимости занимающие промежуточное место между " +
                "проводниками и диэлектриками, и отличающиеся от проводников сильной зависимостью удельной проводимости" +
                " от концентрации примесей, температуры и воздействия различных видов излучения."));
        enrollee5.addAnswer(new Answer("Радиоэлектроника", "Принцип работы транзисторного усилителя " +
                "основан на том, что с помощью небольших изменений напряжения или тока во входной цепи транзистора можно " +
                "получить значительно большие изменения напряжения или тока в его выходной цепи."));

        Enrollee enrollee6 = new Enrollee("Вадим Днищенко", "ФРЭ");
        enrollee6.addAnswer(new Answer("Физика", "Полупроводник — это кристаллический материал, " +
                "который проводит электричество не столь хорошо, как металлы."));
        enrollee6.addAnswer(new Answer("Радиоэлектроника", "Усилитель осуществляет увеличение энергии " +
                "управляющего сигнала за счет энергии вспомогательного источника. Входной сигнал является как бы " +
                "шаблоном, в соответствии с которым регулируется поступление энергии от источника к потребителю " +
                "усиленного сигнала"));
        enrollees.add(enrollee1);
        enrollees.add(enrollee2);
        enrollees.add(enrollee3);
        enrollees.add(enrollee4);
        enrollees.add(enrollee5);
        enrollees.add(enrollee6);
        fkp.registerEnrollee(enrollee1);
        fkp.registerEnrollee(enrollee2);
        fre.registerEnrollee(enrollee3);
        fre.registerEnrollee(enrollee4);
        ksis.registerEnrollee(enrollee5);
        ksis.registerEnrollee(enrollee6);

        worker.writeObject(ENROLLEE_FILENAME, enrollees);
        worker.writeObject(FACULTIES_FILENAME, faculties);

    }

    public static void main(String[] args) {

        System.out.println("Система Вступительные экзамены");

        try {
            writeExampleInfo();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            new Data();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке данных!");
            return;
        }

        authorizationHandler = new AuthorizationHandler();
        boolean state = false;
        while (!state) {
            printMenu();
            if (scanner.hasNextInt()) {
                int k = Integer.parseInt(scanner.nextLine());
                switch (k) {
                    case 1: {
                        state = authorizationHandler.modeLogin();
                        break;
                    }
                    case 2: {
                        state = authorizationHandler.modeRegister();
                        break;
                    }
                    default: {
                        return;
                    }
                }
            } else {
                scanner.nextLine();
                System.out.println("Вы не ввели число!");
                state = false;
            }
        }
    }
}
