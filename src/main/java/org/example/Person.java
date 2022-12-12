package org.example;

import java.util.List;

public class Person {
    private Name name;
    private final int age;
    private final double money;
    private final float height;
    private final float weight;
    private final List<String> knownTools;

    public Person(Name name, int age, float height, float weight,double money,  List<String> knownTools) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.money = money;
        this.knownTools = knownTools;
    }

    public List<String> getKnownTools() {
        return knownTools;
    }

    public void addKnownTools(String toolName) {
        this.knownTools.add(toolName);
    }

    public void removeKnownTool(String toolName) {
        this.knownTools.remove(toolName);
    }

    public Name getName() {
        return name;
    }

    public Person setName(Name name) {
        this.name = name;
        return this;
    }
}