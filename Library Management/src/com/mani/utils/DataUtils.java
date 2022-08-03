package com.mani.utils;

import com.mani.model.LibraryBooks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataUtils {

    public static List<LibraryBooks> libraryBooks = getLibraryBooks();

    public static List<LibraryBooks> getLibraryBooks() {
        List<LibraryBooks> libraryBooks = new ArrayList<>();
        libraryBooks.add(new LibraryBooks(201, "Java", "James Gosling", LocalDate.of(1940, 12, 2),10));
        libraryBooks.add(new LibraryBooks(202, "MySql Edison", "Michael Widenius", LocalDate.of(1970, 8, 12), 6));
        libraryBooks.add(new LibraryBooks(203, "Spring Edison", "Howard Spring", LocalDate.of(1990, 8, 16), 8));
        libraryBooks.add(new LibraryBooks(204, "Angular Edison", "Deborah Kurata", LocalDate.of(1997, 2, 9), 5));
        libraryBooks.add(new LibraryBooks(205, "Microservice Edison", "Chris Richardson", LocalDate.of(1940, 12, 7), 3));
        libraryBooks.add(new LibraryBooks(206, "Hibernate Edison", "Gavin King", LocalDate.of(1960, 7, 2), 7));
        libraryBooks.add(new LibraryBooks(207, "Java 8 Edison", "Herbert Schildt", LocalDate.of(1980, 4, 18), 12));
        return libraryBooks;
    }
}
