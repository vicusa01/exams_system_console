package com.company.data;

import com.company.utils.FileWorker;

import java.util.Scanner;

public class Data {

    public static final String USERSTORAGE_FILENAME = "files/auth.cfg";
    public static Scanner scanner;

    public static final String ENROLLEE_FILENAME = "files/enrolee.cfg";
    public static final String FACULTIES_FILENAME = "files/faculties.cfg";

    public static EnrolleeStorage enrolleeStorage;
    public static FacultyStorage facultyStorage;
    public static UserStorage userStorage;

    static FileWorker fileWorker;

    public Data() throws Exception {
        fileWorker = new FileWorker();

        facultyStorage = new FacultyStorage();
        facultyStorage.loadList(fileWorker.readObject(FACULTIES_FILENAME));
        enrolleeStorage = new EnrolleeStorage();
        enrolleeStorage.loadList(fileWorker.readObject(ENROLLEE_FILENAME));

        userStorage = new UserStorage();
        userStorage.loadList(fileWorker.readObject(USERSTORAGE_FILENAME));

        scanner = new Scanner(System.in);
    }

}
