package com.trainingWithObjects;

public interface TestInterface {

    String name = "";

    String description ="";

    int id = 0;

    String getSomeTestName();

    default String getDefaultString(){
        return "Default String"; // если нет дефолт то надо переопределить
    }
}
