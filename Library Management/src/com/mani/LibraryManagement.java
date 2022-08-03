package com.mani;

import com.mani.exception.BusinessServiceException;
import com.mani.model.LibraryBooks;
import com.mani.service.AdminService;
import com.mani.service.UserService;
import com.mani.utils.AppUtils;
import com.mani.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagement {

    static AppUtils au = new AppUtils();

    List<LibraryBooks> libraryBooks = DataUtils.getLibraryBooks();

    public static void main(String[] args) {
        // Library management dashboard page
        mainPage();
    }

    public static void mainPage() {
        au.print("====== Library Management ======");
        List<String> options = new ArrayList<>();
        options.add("Login as Librarian");
        options.add("Login as Borrower");
        au.options(options);
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (input == 1 || input == 2) {
            try {
                if (input == 1) {
                    // Admin login
                    new AdminService().login();
                } else {
                    // User login
                    new UserService().login();
                }
            } catch (BusinessServiceException e) {
                au.print(e.getMessage());
                mainPage();
            }
        } else {
            au.print("Invalid input");
            mainPage();
        }
    }
}
