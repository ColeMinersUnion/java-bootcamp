package com.academy.library;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void displaySummaryReport() {
        int totalBooks, borrowedBooks, availableBooks, totalMembers;
        totalBooks = libraryService.getBooks().size();
        borrowedBooks = libraryService.getBorrowRecords().size();
        availableBooks = totalBooks - borrowedBooks;
        totalMembers = libraryService.getMembers().size();

        System.out.printf("Report\n" +
                "Books: %d\n" +
                "Books Borrowed: %d\n" +
                "Books Available: %d\n" +
                "Members: %d\n" +
                "Most popular category: %s\n",
                totalBooks, borrowedBooks, availableBooks, totalMembers, findMostPopularCategory());

    }

    public Path exportReportToFile(String fileName) throws IOException {
        int totalBooks, borrowedBooks, availableBooks, totalMembers;
        totalBooks = libraryService.getBooks().size();
        borrowedBooks = libraryService.getBorrowRecords().size();
        availableBooks = totalBooks - borrowedBooks;
        totalMembers = libraryService.getMembers().size();

        FileWriter fileEditor = new FileWriter(fileName);

        String report = String.format("Report\n" +
                        "Books: %d\n" +
                        "Books Borrowed: %d\n" +
                        "Books Available: %d\n" +
                        "Members: %d\n" +
                        "Most popular category: %s\n",
                totalBooks, borrowedBooks, availableBooks, totalMembers, findMostPopularCategory());

        fileEditor.write(report);
        fileEditor.close();

        return Path.of(fileName);

    }

    private String findMostPopularCategory() {
        // TODO: max entry by value from getCategoryBookCount(); orElse "N/A"
        // throw new UnsupportedOperationException("TODO");

        String mostPopularCategory = "N/A";
        int mostPopularMax = 0;

        for (Map.Entry<String, Integer> entry : libraryService.getCategoryBookCount().entrySet()){
            if(entry.getValue() > mostPopularMax){
                mostPopularCategory = entry.getKey();
                mostPopularMax = entry.getValue();
            }
        }

        return mostPopularCategory;

    }
}
