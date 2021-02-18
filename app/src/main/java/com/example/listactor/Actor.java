package com.example.listactor;

public class Actor {
    private String name;
    private int age;
    private String country;
    private String imageName;

    public Actor(String name, int age, String country, String imageName) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.age + " - " + this.country;
    }
}
