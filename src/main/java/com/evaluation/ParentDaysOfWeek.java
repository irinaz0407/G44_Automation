package com.evaluation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

    /**
     * класс родитель для PrintDaysOfWeek ArrayDaysOfWeek классов
     */

public class ParentDaysOfWeek {

    private final static Logger LOG = LogManager.getLogger(ParentDaysOfWeek.class);

    private String parentName;

    // метод-конструктор, сохраняетимя в protected пременной

    public ParentDaysOfWeek(String parentName) {
        this.parentName = parentName;
    }
    // метод который выводит значение protected переменной parentName

    public void workWithName() {
        LOG.info("<" + this.parentName + "> Output text");

    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }
    //  метод распечатки массива Задание 2.1 - 2.3
    public void printByLetter(List<String> inList) {
        try {
            if (inList.size()==0){ //создаем и выбрасываем исключение если список не имеет элементов
                Exception ee = new IndexOutOfBoundsException("Input array is empty or not valid!");
                throw ee;
            }
            for (int ww = 0; ww < inList.size(); ww++) { //цикл внешний
                LOG.debug ("-- Parsing array element #"+ ww);
                for (String s : inList.get(ww).split("")) { //цикл внутренний
                    LOG.info(s);
                }
            }
        } catch (NullPointerException n) { // обрабатываем исключение если входно ймассив = null
            LOG.error("Array is not defined, please check correctness of input data!");
        }
        catch (Exception e) { // обрабатываем все остальные возможные исключения
            LOG.error("Unknown error occured! <"+e.getMessage()+">");
        }
    }
}