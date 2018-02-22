# addressbook
Gumtree's address book exercise

This is a gradle project.

The interface Contact defines a bean that holds the details of an address book entry,
and there is an in-memory implementation.

Using an interface would allow me to test using mocks, but in fact I only needed stubs.

The AddressBook class implements the address book by storing a list of contacts and providing
 methods for the basic logic required.
I aimed to make its methods generic, so for example there is a method getContactsByGender()
rather than a method to show how many contacts are male.

The AddressBookApp contains the main() method. This reads the file, imports it into the
address book and performs the three tasks.

I tried to use a lean programming approach, only implementing what was need for the task.
For example, the name is held as a single string, not as forename and surname, and there
is no validation of that field - anything except a comma is allowed.

There is no cloning of data, so an application could fetch a contact and then change its contents.
That would change the contents of the object in the central list.
If that was a problem, the find methods could supply clones to the caller instead.

There didn't seem to be any generic task behind getting the number of days between two birthdays,
so that's all done in the main() method.

I implemented a specific method to find the oldest person.
With hindsight I think it would have been better to create a method that produced a list
of all contacts sorted by age, and get the main method to pull out the oldest entry.

This in-memory version using an arraylist is OK for a small data set.  For a bigger set, it might
be better to use a map, presuming that you knew what kind of searches you were going to run.  If you
needed to search on multiple fields, a map for each would help.

For a very large data set, such as the address book for a email system, you would need to use a database -
noSQL or SQL depending upon whether you needed relations between contacts.
A database would also give you resiliance.

Test coverage is fairly good, but there are no tests that check the import method handling illegal input.

With more time I would run findbugs and jacoco as part of the gradle build.

