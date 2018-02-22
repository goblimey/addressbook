package com.goblimey.addressbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import java.time.temporal.ChronoUnit;

/**
 * Address Book Application.
 * Created by simon Ritchie on 22/02/18.
 */
public class AddressBookApp {


    public static void main(String args[]) {

        if (args.length != 1) {
            System.err.printf("expected one argumnent, got %d", args.length);
            System.exit(-1);
        }

        /*
         * Read the file given by the first argument into a list of Strings,
         * one per line.
          */

        List<String> lines = new ArrayList<String>();
        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {

            stream.forEach((line)-> lines.add(line));

        } catch (IOException e) {
            e.printStackTrace();
        }

        AddressBook book = new AddressBook();

        try {
            book.importContacts(lines);
        } catch(ParseException e) {
            System.err.printf("ParseException - %s", e.getMessage());
            e.printStackTrace();
        }

        List<Contact> males = book.getContactsByGender(Gender.MALE);
        System.out.printf("There are %d males in the address book\n",
                males.size());

        Contact oldest = book.getOldestContact();
        System.out.printf("%s is the oldest contact in the address book\n",
                oldest.getName());

        List<Contact> list = book.getContactsByName("Bill McKnight");
        if (list == null || list.size() < 1) {
            System.err.printf("Cannot find contact Bill McKnight");
            System.exit(-1);
        }
        Contact bill = list.get(0);

        list = book.getContactsByName("Paul Robinson");
        if (list == null || list.size() < 1) {
            System.err.printf("Cannot find contact Paul Robinson");
            System.exit(-1);
        }
        Contact paul = list.get(0);

        long daysBetween = ChronoUnit.DAYS.between(bill.getDOB().toInstant(), paul.getDOB().toInstant());

        System.out.printf("Bill and Paul were born %d days apart\n",
                daysBetween);
    }

}
