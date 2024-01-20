package com.example.a2023aparty;

public class HostData {

    private String stageName, fullName, address, username, password, role;
    private int age, hp;

    public HostData(String stageName, String fullName, String address, String username, String password, String role, int age, int hp) {
        this.stageName = stageName;
        this.fullName = fullName;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
        this.age = age;
        this.hp = hp;
    }

    public HostData() {
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}