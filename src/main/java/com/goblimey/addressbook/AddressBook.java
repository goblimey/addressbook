package com.goblimey.addressbook;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by simon on 22/02/18.
 */
public class AddressBook {

    private List<Contact> book = new ArrayList<Contact>();

    public AddressBook() {
        super();
    }

    public AddressBook(List<Contact> book) {
        super();
        this.book = book;
    }

    /**
     * Add a contact to the address book.
     * @param contact the contact
     */
    public void addContact(Contact contact) {
        book.add(contact);
    }

    /**
     * Get the number of contacts in the book.
     * @return the number of contacts.
     */
    protected List<Contact> getAllContacts(){
        return book;
    }

    /**
     * Get a list of contacts by gender.
     * @param gender the desired gender.
     * @return the list of the contacts of that gender.
     */
    public List<Contact> getContactsByGender(Gender gender) {
        List<Contact> result = new ArrayList<Contact>();
        for (Contact contact: book) {
            if (gender != null && gender.equals(contact.getGender())) {
                result.add(contact);
            }
        }
        return result;
    }

    /**
     * Get the oldest contact.  If two contacts have the same age,
     * return the first found.
     * @return the oldest contact.
     */
    public Contact getOldestContact() {
        Contact oldestContact = book.get(0);
        for (Contact contact: book) {
            if (contact.getDOB().before(oldestContact.getDOB())) {
                oldestContact = contact;
            }
        }
        return oldestContact;
    }

    /**
     * Get the number of contacts in the book.
     * @return the number of contacts.
     */
    public int size(){
        return book.size();
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "book=" + book +
                '}';
    }
}
