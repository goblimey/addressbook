package com.goblimey.addressbook;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Basic tests for in-memory address book implementation.
 * Created by simon on 22/02/18.
 */
public class AddressBookInMemoryImplTest {

    private static final String EXPECTED_NAME = "Simon Ritchie";
    private static final Gender EXPECTED_GENDER = Gender.MALE;

    @Test
    public void constructor_test() throws Exception {
        Contact book = new ContactInMemoryImpl(EXPECTED_NAME, EXPECTED_GENDER, new Date());

        assertEquals(EXPECTED_NAME, book.getName());
        assertEquals(EXPECTED_GENDER, book.getGender());
        assertNotNull(book.getDOB());
    }

    @Test
    public void getter_setter_test() throws Exception {
        Contact book = new ContactInMemoryImpl();
        book.setName(EXPECTED_NAME);
        book.setGender(EXPECTED_GENDER);
        book.setDOB(new Date());

        assertEquals(EXPECTED_NAME, book.getName());
        assertEquals(EXPECTED_GENDER, book.getGender());
        assertNotNull(book.getDOB());
    }
}