package org.example;

import java.util.HashMap;

public class Applicant {
    public String name;
    public String surname;
    public HashMap<String,Object> data;

    public Applicant(String name,String surname, HashMap<String, Object> data) {
        this.name = name;
        this.surname = surname;
        this.data = data;
    }
}
