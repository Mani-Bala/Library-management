package com.mani.model;

import java.time.LocalDate;
import java.util.Date;

public class LibraryBooks {
    private int id;
    private String name;
    private String author;
    private LocalDate publishDate;
    private int noOfBooks;

    public LibraryBooks(int id, String name, String author, LocalDate publishDate, int noOfBooks) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.noOfBooks = noOfBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNoOfBooks() {
        return noOfBooks;
    }

    public void setNoOfBooks(int noOfBooks) {
        this.noOfBooks = noOfBooks;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "LibraryBooks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", noOfBooks=" + noOfBooks +
                '}';
    }

}
