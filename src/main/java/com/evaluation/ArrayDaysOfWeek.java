package com.evaluation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArrayDaysOfWeek extends ParentDaysOfWeek {

    private final static Logger LOG = LogManager.getLogger(ArrayDaysOfWeek.class);

    private String testName;
    private int testId;
        /*
         * Task 2 метод возвращает первые 4 элемента из параметра типа массив строк
         */
    public List<String> workWithArr2(String[] param) {
        /*
         * создаем временный список (пустой)
         */
        List<String> tmpstringList = new ArrayList<>();
        int ii = 0;
        /*
         * в цикле переносим элементы пока не достигнем 4х или конца массива
         */
        while (ii < param.length ){
            if(ii == 4) break; // выходим из цикла, если ii равно 4
            tmpstringList.add(param[ii]);
           ii++;
        }
        /*
         * возвращаем временный заполненный список
         */
        return tmpstringList;
    }
        // метод-конструктор без параметров выводит текст с именем класса

    public ArrayDaysOfWeek() {
        super("ArrayDaysOfWeek - default");
        LOG.info("New empty class entity <"+this.getClass().getSimpleName()+">");
    }
        // метод-конструктор с параметрами которые сохраняются во внутренних переменных потомка и родителя(через сеттер)

    public ArrayDaysOfWeek(String testName,
                           int testId) {
        super(testName);
        this.setParentName(testName);
        this.testName = testName;
        this.testId = testId;

    }


    //    public <type of method> <method name> ( <type of parameter1> <parameter name1>, <type of parameter1> <parameter name1>,...)
}

