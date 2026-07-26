package com.academy.library;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class LibraryService {

    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Member> members = new ArrayList<>();
    private final HashSet<String> bookIds = new HashSet<>();
    private final HashSet<String> memberIds = new HashSet<>();
    private final HashMap<String, String> borrowRecords = new HashMap<>(); //key,
    private final TreeSet<String> categories = new TreeSet<>();
    private final TreeMap<String, Integer> categoryBookCount = new TreeMap<>();
    private final ArrayList<BorrowRecord> borrowHistory = new ArrayList<>();
    private final HashMap<String, Integer> borrowFrequency = new HashMap<>();

    private final Scanner scanner;
    private final ReportService reportService;

    public LibraryService(Scanner scanner) {
        this.scanner = scanner;
        this.reportService = new ReportService(this);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public HashMap<String, String> getBorrowRecords() {
        return borrowRecords;
    }

    public TreeSet<String> getCategories() {
        return categories;
    }

    public TreeMap<String, Integer> getCategoryBookCount() {
        return categoryBookCount;
    }

    public ArrayList<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void addBook() {
        System.out.print("Book ID : ");
        String bookId = scanner.nextLine().trim();

        if (bookIds.contains(bookId)) {
            System.out.println("Book already exists.");
            return;
        }

        System.out.print("Title : ");
        String title = scanner.nextLine().trim();
        System.out.print("Author : ");
        String author = scanner.nextLine().trim();
        System.out.print("Category : ");
        String category = scanner.nextLine().trim();
        double price = readPositiveDouble("Price : ");

        Book book = new Book(bookId, title, author, category, price);
        books.add(book);
        bookIds.add(bookId);
        categories.add(category);
        categoryBookCount.merge(category, 1, Integer::sum);

        System.out.println("Book Added Successfully");
    }

    public void removeBook() {
        System.out.print("Book ID to remove : ");
        String bookId = scanner.nextLine().trim();
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (borrowRecords.containsKey(bookId)) {
            System.out.println("Cannot remove a borrowed book.");
            return;
        }

        books.remove(book);
        bookIds.remove(bookId);
        updateCategoryCount(book.getCategory(), -1);
        System.out.println("Book removed successfully.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("--- Traditional For Loop ---");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }

        System.out.println("--- Enhanced For Loop ---");
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("--- Iterator ---");
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("--- forEach() ---");
        books.forEach(System.out::println);
    }

    public void registerMember() {
        System.out.print("Member ID : ");
        String memberId = scanner.nextLine().trim();

        if (memberIds.contains(memberId)) {
            System.out.println("Member already exists.");
            return;
        }

        System.out.print("Name : ");
        String name = scanner.nextLine().trim();
        System.out.print("Email : ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone : ");
        String phone = scanner.nextLine().trim();

        members.add(new Member(memberId, name, email, phone));
        memberIds.add(memberId);
        System.out.println("Member Registered Successfully");
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        members.forEach(System.out::println);
    }

    public void searchBook() {
        System.out.println("Search by: 1-ID  2-Title  3-Author  4-Category  5-Partial Title");
        System.out.print("Choice : ");
        String choice = scanner.nextLine().trim();
        System.out.print("Search value : ");
        String value = scanner.nextLine().trim();

        List<Book> matches = new ArrayList<>();

        switch (choice) {
            case "1" -> {
                Book book = findBookById(value);
                if (book != null) {
                    matches.add(book);
                }
            }
            case "2" -> matches.addAll(searchByField(value, SearchField.TITLE));
            case "3" -> matches.addAll(searchByField(value, SearchField.AUTHOR));
            case "4" -> matches.addAll(searchByField(value, SearchField.CATEGORY));
            case "5" -> matches.addAll(searchPartialTitle(value));
            default -> System.out.println("Invalid search option.");
        }

        if (matches.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }

        System.out.println("Search Results:");
        matches.forEach(System.out::println);
    }

    public void borrowBook() {
        System.out.print("What book are you trying to borrow: ");
        String bookID = scanner.nextLine().trim();

        System.out.print("\nWhat is your member ID? ");
        String memberID = scanner.nextLine().trim();

        if (!memberIds.contains(memberID)){
            System.out.println("You are not registered in the system. Please register before borrowing a book.");
            return;
        }

        if(!bookIds.contains(bookID)){
            System.out.println("The requested book ID does not exist");
            return;
        }

        if(borrowRecords.containsKey(bookID)){
            System.out.println("The requested book ID has already been lent.");
            return;
        }

        // Valid Borrow
        // Unavailable now, create a borrow record, increase borrowFrequency
        BorrowRecord record = new BorrowRecord(bookID, memberID, LocalDate.now());
        borrowRecords.put(bookID, memberID);
        borrowHistory.add(record);

        int freq;
        if (borrowFrequency.containsKey(bookID)){
            freq = borrowFrequency.get(bookID);
            borrowFrequency.put(bookID, freq + 1);
        } else {
            borrowFrequency.put(bookID, 1);
        }

        System.out.printf("You have successfully checked out %s.\n", bookID);
    }

    public void returnBook() {

        System.out.print("What book are you returning? ");
        String bookID = scanner.nextLine().trim();

        if(!borrowRecords.containsKey(bookID)){
            System.out.println("This book was not checked out.");
            return;
        }

        //remove from borrow records
        borrowRecords.remove(bookID);

        //update borrow record with return date
        //find applicable record.
        //last record with this bookID
        String iter_bookID;
        ListIterator<BorrowRecord> borrow_iter = borrowHistory.listIterator(borrowHistory.size());
        while(borrow_iter.hasPrevious()){
            BorrowRecord record = borrow_iter.previous();
            iter_bookID = record.getBookId();
            if (bookID.equals(iter_bookID)){
                record.setReturnDate(LocalDate.now());
                break;
            }
        }

        System.out.println("Book returned Successfully");

    }

    public void displayBorrowedBooks() {
        if (borrowRecords.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        borrowRecords.forEach((bookId, memberId) -> {
            Book book = findBookById(bookId);
            Member member = findMemberById(memberId);
            System.out.printf("Book: %s (%s) borrowed by %s (%s)%n",
                    bookId,
                    book != null ? book.getTitle() : "Unknown",
                    memberId,
                    member != null ? member.getName() : "Unknown");
        });
    }

    public void displayAvailableBooks() {
        List<Book> availableBooks = books.stream()
                .filter(Book::isAvailable)
                .toList();

        if (availableBooks.isEmpty()) {
            System.out.println("No available books.");
            return;
        }

        availableBooks.forEach(System.out::println);
    }

    public void sortBooks() {
        if (books.isEmpty()) {
            System.out.println("No books to sort.");
            return;
        }

        System.out.println("Sort by: 1-Title  2-Price  3-Author  4-Category");
        System.out.print("Choice : ");
        String choice = scanner.nextLine().trim();

        List<Book> sortedBooks = new ArrayList<>(books);

        switch (choice) {
            case "1" -> Collections.sort(sortedBooks);
            case "2" -> sortedBooks.sort(new BookComparator());
            case "3" -> sortedBooks.sort(Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER));
            case "4" -> sortedBooks.sort(Comparator.comparing(Book::getCategory, String.CASE_INSENSITIVE_ORDER));
            default -> {
                System.out.println("Invalid sort option.");
                return;
            }
        }

        System.out.println("Sorted Books:");
        sortedBooks.forEach(System.out::println);
    }

    public void displayReports() {
        reportService.displaySummaryReport();
    }

    public void exportReport() {
        try {
            Path outputPath = reportService.exportReportToFile("library-report.txt");
            System.out.println("Report exported to: " + outputPath.toAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Failed to export report: " + ex.getMessage());
        }
    }

    public void displayBorrowHistory() {
        if (borrowHistory.isEmpty()) {
            System.out.println("No borrow history.");
            return;
        }

        borrowHistory.forEach(BorrowRecord::display);
    }

    public void displayTopBorrowedBooks() {
        if (borrowFrequency.isEmpty()) {
            System.out.println("No borrow data available.");
            return;
        }

        System.out.println("Top 5 Most Borrowed Books");
        borrowFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    Book book = findBookById(entry.getKey());
                    String title = book != null ? book.getTitle() : "Unknown";
                    System.out.printf("%s (%s) - %d borrows%n", entry.getKey(), title, entry.getValue());
                });
    }

    public void displayCategoryInsights() {
        System.out.println("Categories (TreeSet - sorted, unique):");
        categories.forEach(category -> System.out.println("- " + category));

        System.out.println();
        System.out.println("Books per Category (TreeMap - sorted keys):");
        categoryBookCount.forEach((category, count) ->
                System.out.printf("%s : %d%n", category, count));
    }

    public void runPerformanceComparison() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    private List<Book> searchByField(String value, SearchField field) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            String candidate = switch (field) {
                case TITLE -> book.getTitle();
                case AUTHOR -> book.getAuthor();
                case CATEGORY -> book.getCategory();
            };

            if (candidate.equalsIgnoreCase(value)) {
                results.add(book);
            }
        }
        return results;
    }

    private List<Book> searchPartialTitle(String partialTitle) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(partialTitle.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    private void updateCategoryCount(String category, int delta) {
        int updated = categoryBookCount.getOrDefault(category, 0) + delta;
        if (updated <= 0) {
            categoryBookCount.remove(category);
            categories.remove(category);
        } else {
            categoryBookCount.put(category, updated);
        }
    }

    private double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Price must not be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price. Please try again.");
            }
        }
    }

    private enum SearchField {
        TITLE, AUTHOR, CATEGORY
    }
}
