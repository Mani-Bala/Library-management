package com.mani.service;

import com.mani.LibraryManagement;
import com.mani.exception.BusinessServiceException;
import com.mani.model.LibraryBooks;
import com.mani.utils.AppUtils;
import com.mani.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Borrower related service
 */
public class UserService {


    static AppUtils au = new AppUtils();
    static final String userName = "user";
    static final String userPassword = "User123$";
    static Scanner sc = new Scanner(System.in);

    /**
     * User login
     */
    public void login() throws BusinessServiceException {
        try {
            au.print("Username: ");
            String usernameInput = sc.next();
            au.print("Password: ");
            String passwordInput = sc.next();

            if (Objects.equals(userName, usernameInput) && Objects.equals(userPassword, passwordInput)) {
                au.print("Login successfully..");
                userDashboard();
            } else {
                au.print("Invalid username/password.");
                loginTryAgain();
            }
        } catch (Exception e) {
            throw new BusinessServiceException(e.getMessage(), e);
        }
    }

    /**
     * Try again for user login
     */
    private void loginTryAgain() throws BusinessServiceException {
        List<String> options = new ArrayList<>();
        options.add("Try again");
        options.add("Back to Main");
        au.options(options);

        int input = sc.nextInt();
        if (input > 0 && input <= options.size()) {
            if (input == 1) {
                login();
            } else {
                LibraryManagement.mainPage();
            }
        } else {
            au.print("Invalid input");
            loginTryAgain();
        }
    }

    private void userDashboard() {
        au.print("====== Welcome to User Dashboard ======");
        List<String> options = new ArrayList<>();
        options.add("Take book");
        options.add("Return book");
        options.add("Logout");
        au.options(options);

        int input = sc.nextInt();
        if (input > 0 && input <= options.size()) {
            if (input == 1) {
                takeBook();
            } else if (input == 2) {
                returnBook();
            } else {
                LibraryManagement.mainPage();
            }
        } else {
            au.print("Invalid input");
            userDashboard();
        }
    }

    /**
     * Take book
     */
    private void takeBook() {
        au.print("Search book: ");
        String searchInput = sc.next();

        if (DataUtils.libraryBooks.stream().anyMatch(book -> book.getName().trim().toLowerCase().contains(searchInput.trim().toLowerCase()))) {
            List<LibraryBooks> filtered = DataUtils.libraryBooks.stream().filter(book -> book.getName().trim().toLowerCase().contains(searchInput.trim().toLowerCase())).collect(Collectors.toList());
            filtered.forEach(System.out::println);

            enterBookIdToTake();
        } else {
            au.print("Search result not found");
            searchAgain();
        }
    }

    private void enterBookIdToTake() {
        try {
            au.print("Enter a book id to take book: ");
            Integer bookId = sc.nextInt();
            if (DataUtils.libraryBooks.stream().anyMatch(book -> book.getId() == bookId)) {
                if (DataUtils.libraryBooks.stream().anyMatch(book -> book.getId() == bookId && book.getNoOfBooks() <= 0)) {
                    au.print("Book is not available to take");
                    searchAgain();
                }
                au.print("Do you want you take book Id: " + bookId + " ? yes/No:  yes");

                String input = sc.next();
                if ("yes".equalsIgnoreCase(input)) {
                    LibraryBooks libraryBooks = DataUtils.libraryBooks.stream().filter(book -> bookId.equals(book.getId())).peek(book -> {
                        book.setNoOfBooks(book.getNoOfBooks() - 1);
                    }).findFirst().orElse(null);

                    if (Objects.nonNull(libraryBooks)) {
                        au.print("Book Details: ");
                        au.print(libraryBooks.toString());
                        au.print("Book has been taken successfully.");
                    }
                    searchAgain();
                } else if ("no".equalsIgnoreCase(input)) {
                    searchAgain();
                } else {
                    au.print("Invalid book Id");
                    searchAgain();
                }
            } else {
                au.print("Book Id not available in library");
                enterBookIdToTake();
            }
        } catch (Exception e) {
            au.print("Invalid book Id");
            enterBookIdToTake();
        }

    }

    private void searchAgain() {
        try {
            List<String> options = new ArrayList<>();
            options.add("Search again");
            options.add("Back to dashboard");
            au.options(options);

            int input = sc.nextInt();
            if (input > 0 && input <= options.size()) {
                if (input == 1) {
                    takeBook();
                } else {
                    userDashboard();
                }
            } else {
                au.print("Wrong option");
                searchAgain();
            }
        } catch (Exception e) {
            au.print("Invalid input");
            searchAgain();
        }
    }

    /**
     * Return book
     */
    private void returnBook() {

        try {
            au.print("Enter a book id to return: ");
            int bookId = sc.nextInt();
            if (DataUtils.libraryBooks.stream().anyMatch(book -> book.getId() == bookId)) {
                DataUtils.libraryBooks.forEach(book -> {
                    if (book.getId() == bookId) {
                        book.setNoOfBooks(book.getNoOfBooks() + 1);
                    }
                });
                au.print("Book returned successfully.");
                userDashboard();
            } else {
                au.print("Book Id not available in library");
                userDashboard();
            }
        } catch (Exception e) {
            au.print("Invalid book Id");
            userDashboard();
        }

    }

}
