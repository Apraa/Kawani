package com;

import com.example.login.R;
import com.google.firebase.auth.FirebaseAuth;

public class Accounts {
    private String Username;
    private String Password;
    private int IDNumber;
    private String EmailAddress;
    private String FirstName;
    private String LastName;
    private String Position;
    private String Department;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public Accounts(String username, String password, int IDNumber, String emailAddress, String firstName, String lastName, String position, String department) {
        Username = username;
        Password = password;
        this.IDNumber = IDNumber;
        EmailAddress = emailAddress;
        FirstName = firstName;
        LastName = lastName;
        Position = position;
        Department = department;
    }
}
