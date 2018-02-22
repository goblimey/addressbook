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

    @Test
    public void getOldestContact_test() throws Exception {
        Contact contact = book.getOldestContact();
        assertEquals(contact1, contact);
    }

    @Test
    public void getOldestContactWithSomeEqualAge_test() throws Exception {
        /*
         * The standard data won't do for this test.  We want two contacts with the
         * same DOB and older than any others.
         */

        book = new AddressBook();
        Contact c1 = new ContactInMemoryImpl(EXPECTED_NAME1, EXPECTED_GENDER1, expected_DOB2);
        book.addContact(c1);
        Contact c2 = new ContactInMemoryImpl(EXPECTED_NAME2, EXPECTED_GENDER2, expected_DOB1);
        book.addContact(c2);
        Contact c3 = new ContactInMemoryImpl(EXPECTED_NAME3, EXPECTED_GENDER3, expected_DOB1);
        book.addContact(c3);
        Contact contact = book.getOldestContact();

        assertEquals(c2, contact);
    }

    @Test
    public void getContactsByName_test() throws Exception {
        List<Contact> list = book.getContactsByName(EXPECTED_NAME2);
        assertEquals(1, list.size());
        assertEquals(contact2, list.get(0));
    }

    @Test
    public void getContactsByNameWithSomeNamesEqual_test() throws Exception {
        /*
         * The standard data won't do for this test.  We want two contacts with the
         * same name.
         */

        book = new AddressBook();
        Contact c1 = new ContactInMemoryImpl(EXPECTED_NAME1, EXPECTED_GENDER1, expected_DOB1);
        book.addContact(c1);
        Contact c2 = new ContactInMemoryImpl(EXPECTED_NAME2, EXPECTED_GENDER2, expected_DOB2);
        book.addContact(c2);
        Contact c3 = new ContactInMemoryImpl(EXPECTED_NAME2, EXPECTED_GENDER3, expected_DOB3);
        book.addContact(c3);
        List<Contact> list = book.getContactsByName(EXPECTED_NAME2);

        assertEquals(c2, list.get(0));
        assertEquals(c3, list.get(1));
    }

    @Test
    public void emptyBook_test() throws Exception {
        /*
         * Check that all get methods return sensible results with an empty book.
         */

        book = new AddressBook();
        List<Contact> list = book.getAllContacts();
        assertEquals(0, list.size());

        list = book.getContactsByName(EXPECTED_NAME2);
        assertEquals(0, list.size());

        list = book.getContactsByGender(EXPECTED_GENDER1);
        assertEquals(0, list.size());

        Contact contact = book.getOldestContact();
        assertNull(contact);

        int size = book.size();
        assertEquals(0, size);

    }
}