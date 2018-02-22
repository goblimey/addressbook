package com.goblimey.addressbook;

import java.util.Date;

/**
 * Created by simon on 22/02/18.
 */
public class ContactInMemoryImpl implements Contact {

    String name;
    Gender gender;
    Date dob;

    public ContactInMemoryImpl() {
        super();
    }

    public ContactInMemoryImpl(String name, Gender gender, Date dob) {
        super();
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Date getDOB() {
        return dob;
    }

    @Override
    public void setDOB(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "ContactInMemoryImpl{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
