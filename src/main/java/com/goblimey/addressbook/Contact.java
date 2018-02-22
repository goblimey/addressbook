package com.goblimey.addressbook;

import java.util.Date;
import java.util.List;

/**
 * Interface for address book bean.
 * Created by simon Rtchie on 22/02/18.
 */
public interface Contact {
    String getName();
    void setName(String name);
    Gender getGender();
    void setGender(Gender gender);
    Date getDOB();
    void setDOB(Date dob);
}
