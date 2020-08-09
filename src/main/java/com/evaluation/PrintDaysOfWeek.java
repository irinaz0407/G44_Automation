package com.evaluation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class PrintDaysOfWeek extends ParentDaysOfWeek {

    private final static Logger LOG = LogManager.getLogger(PrintDaysOfWeek.class);

    private String testName;
    private int testId;

        /**
        * Task 3 sorting and printing
        */
        public void WorkWithArr3(List<String> tmp){
        /*
        * получаем список через параметр и сортируем его (честно позаимствовано из инета)
        */
        Collections.sort(tmp);
        /*
        * в цикле for выводим элементы списка в консоль
        */
        for (int w = 0; w < tmp.size();w++){
            LOG.debug(tmp.get(w));
        }
    }
        /*
         * метод-конструктор без параметров выводит текст с именем класса
         */

        public PrintDaysOfWeek() {
            super("PrintDaysOfWeek - default");
            LOG.info("New empty class entity <"+this.getClass().getSimpleName()+">");
    }
        /*
         * метод-конструктор с параметрами которые сохраняются во внутренних переменных потомка и родителя(через сеттер)
         */

    public PrintDaysOfWeek(String testName,
                           int testId) {
        super(testName);
        this.setParentName(testName);
        this.testName = testName;
        this.testId = testId;
    }

}
