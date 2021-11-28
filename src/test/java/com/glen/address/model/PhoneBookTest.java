package com.glen.address.model;

import com.mongodb.lang.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    PhoneBook phoneBook = new PhoneBook("Work",  123456789,  "Glen", "Rapaj" );

    @Test
    void getType() {
        assertEquals( "Work",
                phoneBook.getType(),
                "phone Book Type don't correspond to the real one." );
    }

    @Test
    void getNumber() {
        assertEquals( 123456789,
                phoneBook.getNumber(),
                "Number in phone Book Entry Type don't correspond to the real one." );
    }

    @Test
    void getFirst() {
        assertEquals( 4,
                phoneBook.getFirst().length(),
                "First Name in phone Book Entry Type don't correspond to the real one." );
    }

    @Test
    void getLast() {
        assertEquals( "Rapaj",
                phoneBook.getLast(),
                "Last Name in phone Book Entry Type don't correspond to the real one." );
    }
}