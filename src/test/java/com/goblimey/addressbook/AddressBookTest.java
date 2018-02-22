package com.goblimey.addressbook;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by simon on 22/02/18.
 */
public class AddressBookTest {
    private static final String EXPECTED_NAME1 = "A B";
    private static final Gender EXPECTED_GENDER1 = Gender.MALE;
    private static final String EXPECTED_NAME2 = "C D";
    private static final Gender EXPECTED_GENDER2 = Gender.FEMALE;
    private static final String EXPECTED_NAME3 = "E F";
    private static final Gender EXPECTED_GENDER3 = Gender.MALE;
    private Date expected_DOB1;
    private Date expected_DOB2;
    private Date expected_DOB3;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    private AddressBook book;

    @Before
    public void setup() {
        /*
         * Prepare a list of three contacts with different birthdays and add
         * the first two to the address book.
         */

        Calendar cal = new GregorianCalendar();
        cal.set(2018, 1, 1);
        expected_DOB1 = new Date(cal.getTime().getTime());
        contact1 = new ContactInMemoryImpl(EXPECTED_NAME1, EXPECTED_GENDER1, expected_DOB1);

        cal.set(2018, 1, 2);
        expected_DOB2 = new Date(cal.getTime().getTime());
        contact2 = new ContactInMemoryImpl(EXPECTED_NAME2, EXPECTED_GENDER2, expected_DOB2);

        cal.set(2018, 1, 3);
        expected_DOB3 = new Date(cal.getTime().getTime());
        contact3 = new ContactInMemoryImpl(EXPECTED_NAME3, EXPECTED_GENDER3, expected_DOB3);

        List<Contact> l = new ArrayList<Contact>();
        l.add(contact1);
        l.add(contact2);

        book = new AddressBook(l);
    }

    @Test
    public void addContact_test() throws Exception {
        book.addContact(contact3);
        assertEquals(3, book.size());
        List<Contact> contacts = book.getAllContacts();
        assertEquals(contact1, contacts.get(0));
        assertEquals(contact2, contacts.get(1));
        assertEquals(contact3, contacts.get(2));
    }

    @Test
    public void getByGender_test() throws Exception {
        book.addContact(contact3);
        List<Contact> males = book.getContactsByGender(Gender.MALE);
        assertNotNull(males);
        assertEquals(2, males.size());
        assertEquals(contact1, males.get(0));
        assertEquals(contact3, males.get(1));
    }
}