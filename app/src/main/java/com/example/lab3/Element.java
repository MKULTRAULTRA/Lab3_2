package com.example.lab3;

public class Element {
    private String id;
    private String second_name;
    private String first_name;
    private String patronymic;
    private String time;
    public Element(String id, String second_name, String first_name, String patronymic, String time){
        this.id=id;
        this.second_name=second_name;
        this.first_name=first_name;
        this.patronymic=patronymic;
        this.time=time;
    }
    public String getId(){
        return this.id;
    }
    public String getSecondName(){
        return this.second_name;
    }
    public String getFirstName(){
        return this.first_name;
    }
    public String getPatronymic(){
        return this.patronymic;
    }
    public String getTime(){
        return this.time;
    }
}
