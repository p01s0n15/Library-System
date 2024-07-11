package org.example;
import java.util.*;
class Book {
    private int bookId;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(int bookId, String title, String author, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void increaseAvailableCopies() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }
}

class Member {
    private int memberId;
    private String name;
    private int age;
    private String phoneNumber;
    private List<Book> borrowedBooks;
    private double balance;

    public Member(int memberId, String name, int age, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = new ArrayList<>();
        this.balance = 0.0;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public double getBalance() {
        return balance;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() < 2 && book.getAvailableCopies() > 0) {
            borrowedBooks.add(book);
            book.decreaseAvailableCopies();
        } else {
            System.out.println("Unable to borrow the book. Check book availability or your limit.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.increaseAvailableCopies();
            // Calculate and apply fines if needed
        } else {
            System.out.println("You didn't borrow this book from the library.");
        }
    }

    public void payDues(double amount) {
        if (amount >= 0) {
            balance -= amount;
            System.out.println("Payment successful. Your current balance: " + balance);
        } else {
            System.out.println("Invalid payment amount.");
        }
    }
    public boolean login(String name, String phoneNumber) {
        return this.name.equals(name) && this.phoneNumber.equals(phoneNumber);
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Member> getAllMembers() {
        return members;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("Library Portal Initialized....");
            System.out.println("---------------------------------");
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
            System.out.println("---------------------------------\n");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    librarianMenu(scanner, library);
                    break;
                case 2:
                    memberMenu(scanner, library);
                    break;
                case 3:
                    System.out.println("---------------------------------");
                    System.out.println("Thanks for visiting!");
                    System.out.println("---------------------------------");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void librarianMenu(Scanner scanner, Library library) {
        while (true) {
            System.out.println("---------------------------------");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            System.out.println("---------------------------------\n");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("---------------------------------");
                    System.out.print("Member ID: ");
                    int memberId = scanner.nextInt();
                    System.out.print("Name: ");
                    String name = scanner.next();
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.next();
                    Member newMember = new Member(memberId, name, age, phoneNumber);
                    library.registerMember(newMember);
                    System.out.println("---------------------------------\n");
                    System.out.println("Member Successfully Registered with <Member ID>!");
                    break;
                case 2:
                    System.out.print("Enter Member ID to remove: ");
                    int memberIdToRemove = scanner.nextInt();
                    Member memberToRemove = null;
                    for (Member member : library.getAllMembers()) {
                        if (member.getMemberId() == memberIdToRemove) {
                            memberToRemove = member;
                            break;
                        }
                    }
                    if (memberToRemove != null) {
                        library.removeMember(memberToRemove);
                        System.out.println("Member removed successfully.");
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                case 3:
                    System.out.println("---------------------------------");
                    System.out.print("Book ID: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Title: ");
                    String title = scanner.next();
                    System.out.print("Author: ");
                    String author = scanner.next();
                    System.out.print("Total Copies: ");
                    int totalCopies = scanner.nextInt();
                    Book newBook = new Book(bookId, title, author, totalCopies);
                    library.addBook(newBook);
                    System.out.println("---------------------------------\n");
                    System.out.println("Book added successfully.");
                    break;
                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    int bookIdToRemove = scanner.nextInt();
                    Book bookToRemove = null;
                    for (Book book : library.getAllBooks()) {
                        if (book.getBookId() == bookIdToRemove) {
                            bookToRemove = book;
                            break;
                        }
                    }
                    if (bookToRemove != null) {
                        library.removeBook(bookToRemove);
                        System.out.println("Book removed successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 5:
                    for (Member member : library.getAllMembers()) {
                        System.out.println("Member ID: " + member.getMemberId());
                        System.out.println("Name: " + member.getName());
                        System.out.println("Books Borrowed: " + member.getBorrowedBooks().size());
                        System.out.println("Balance: " + member.getBalance());
                        System.out.println("---------------------------------");
                    }
                    break;
                case 6:
                    for (Book book : library.getAllBooks()) {
                        System.out.println("Book ID: " + book.getBookId());
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Total Copies: " + book.getTotalCopies());
                        System.out.println("Available Copies: " + book.getAvailableCopies());
                        System.out.println("---------------------------------");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void memberMenu(Scanner scanner, Library library) {
        while (true) {
            System.out.println("---------------------------------");
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Books:");
                    List<Book> availableBooks = library.getAllBooks();
                    for (Book book : availableBooks) {
                        if (book.getAvailableCopies() > 0) {
                            System.out.println("Book ID: " + book.getBookId());
                            System.out.println("Title: " + book.getTitle());
                            System.out.println("Author: " + book.getAuthor());
                            System.out.println("Available Copies: " + book.getAvailableCopies());
                            System.out.println("---------------------------------");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Your Books:");
                    List<Book> myBooks = library.getAllMembers().get(0).getBorrowedBooks();
                    for (Book book : myBooks) {
                        System.out.println("Book ID: " + book.getBookId());
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("---------------------------------");
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int bookIdToBorrow = scanner.nextInt();
                    Book bookToBorrow = null;
                    for (Book book : library.getAllBooks()) {
                        if (book.getBookId() == bookIdToBorrow && book.getAvailableCopies() > 0) {
                            bookToBorrow = book;
                            break;
                        }
                    }
                    if (bookToBorrow != null) {
                        library.getAllMembers().get(0).borrowBook(bookToBorrow);
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book not found or not available.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    Book bookToReturn = null;
                    Member currentMember = library.getAllMembers().get(0);
                    for (Book book : currentMember.getBorrowedBooks()) {
                        if (book.getBookId() == bookIdToReturn) {
                            bookToReturn = book;
                            break;
                        }
                    }
                    if (bookToReturn != null) {
                        currentMember.returnBook(bookToReturn);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("You didn't borrow this book.");
                    }
                    break;
                case 5:
                    System.out.print("Enter the amount to pay: ");
                    double amountToPay = scanner.nextDouble();
                    Member memberToPay = library.getAllMembers().get(0);
                    if (amountToPay >= 0) {
                        memberToPay.payDues(amountToPay);
                    } else {
                        System.out.println("Invalid payment amount.");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}




