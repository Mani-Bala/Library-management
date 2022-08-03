package com.mani.service;

import com.mani.LibraryManagement;
import com.mani.exception.BusinessServiceException;
import com.mani.utils.AppUtils;
import com.mani.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Librarian related service
 */
public class AdminService {

    AppUtils au = new AppUtils();
    final String adminName = "admin";
    final String adminPassWord = "Admin123$";
    Scanner sc = new Scanner(System.in);

    /**
     * User login
     */
    public void login() throws BusinessServiceException {
        try {
            au.print("Username: ");
            String usernameInput = sc.next();
            au.print("Password: ");
            String passwordInput = sc.next();

            if (Objects.equals(adminName, usernameInput) && Objects.equals(adminPassWord, passwordInput)) {
                au.print("Login successfully..");
                adminDashboard();
            } else {
                au.print("Invalid username/password.");
                loginTryAgain();
            }
        } catch (Exception e) {
            throw new BusinessServiceException(e.getMessage(), e);
        }

    }

    /**
     * Try again for admin login
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

    /**
     * Admin dashboard
     */
    private void adminDashboard() {
        au.print("====== Welcome to Admin Dashboard ======");
        List<String> options = new ArrayList<>();
        options.add("Available books");
        options.add("Logout");
        au.options(options);

        int input = sc.nextInt();
        if (input > 0 && input <= options.size()) {
            if (input == 1) {
                DataUtils.libraryBooks.forEach(System.out::println);
                adminDashboard();
            } else {
                LibraryManagement.mainPage();
            }
        } else {
            au.print("Invalid input");
            adminDashboard();
        }
    }
}
