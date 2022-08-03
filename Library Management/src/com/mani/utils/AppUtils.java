package com.mani.utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AppUtils {

    public void print(String msg) {
        System.out.println(msg);
    }

    public void options(List<String> options) {

        StringBuilder sb = new StringBuilder();

        AtomicInteger count = new AtomicInteger(1);
        options.forEach(option -> {
            sb.append(count.getAndIncrement()).append(". ").append(option).append("  ");
        });
        print(sb.toString());
        print("Enter an option: ");
    }
}
