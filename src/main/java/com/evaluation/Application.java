package com.evaluation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Application {

    private final static Logger LOG = LogManager.getLogger(Application.class);

    public static void main (String[] args) {

        LOG.info("Method main started");
        //создаем экземпляр класса родителя задавая имя параметром
           ParentDaysOfWeek parClassEntity = new ParentDaysOfWeek("Entity1");
        // вызов метода для распечатки значения протектед переменной
           parClassEntity.workWithName();

        /*
         * создание массива строк с именем daysofweekArray
         */
        String[] daysofweekArray = new String[7];

        /*
         * Task 1
         * Fill an array of strings
         * заполнение массива сторк daysofweekArray значениями
         */
        daysofweekArray[0] = "Sunday";
        daysofweekArray[1] = "Monday";
        daysofweekArray[2] = "Tuesday";
        daysofweekArray[3] = "Wednesday";
        daysofweekArray[4] = "Thursday";
        daysofweekArray[5] = "Friday";
        daysofweekArray[6] = "Saturday";
        /*
        * создание экземпляров классов с различными вариантами вызова конструктора
        */
    ArrayDaysOfWeek homework1 = new ArrayDaysOfWeek();
    homework1.workWithName();
    ArrayDaysOfWeek homework2 = new ArrayDaysOfWeek("ChildEntity1", 1);
    homework2.workWithName();
    PrintDaysOfWeek printwork1= new PrintDaysOfWeek();
    printwork1.workWithName();
    PrintDaysOfWeek printwork2= new PrintDaysOfWeek("ChildEntity2",2);
    printwork2.workWithName();

        /*
        * вызываем метод распечатки массива (описан в родительском классе, вызываем из потомка А
        */
        LOG.info("printByLetter normal call");

      homework1.printByLetter(homework1.workWithArr2(daysofweekArray));

        /*
         * вызываем метод распечатки массива вызываем из потомка А вызываем NullPointer exception
         */
        LOG.info("printByLetter with null");

        homework1.printByLetter(null);

        /*
         * вызываем метод распечатки массива вызываем из потомка Б передавая массив из потомка А
         */
        LOG.info("printByLetter from class A");

        printwork1.printByLetter(homework1.workWithArr2(daysofweekArray));

        /*
         * вызываем метод распечатки массива вызываем из потомка Б передавая new ArrayList<String>()
         */
        LOG.info("printByLetter with new ArrayList<>()");

        printwork1.printByLetter(new ArrayList<>());



        /*
         * сортируем и выводим в консоль полученный список путем вызова метода WorkWithArr3 передаем
         * как параметр stringList
         */
//        printwork1.WorkWithArr3(stringList);
    }

}
