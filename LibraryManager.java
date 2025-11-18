
import java.io.*;
import java.util.*;

public class LibraryManager {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    private final String BOOKS_FILE = "books.txt";
    private final String MEMBERS_FILE = "members.txt";

    public LibraryManager() {
        loadFromFile();
    }

    // ---------- File Handling ----------
    public void loadFromFile() {
        try {
            File bookFile = new File(BOOKS_FILE);
            File memberFile = new File(MEMBERS_FILE);

            if (!bookFile.exists()) bookFile.createNewFile();
            if (!memberFile.exists()) memberFile.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                Book b = new Book(id, data[1], data[2], data[3]);
                if (data[4].equals("true")) b.markAsIssued();
                books.put(id, b);
            }
            br.close();

            br = new BufferedReader(new FileReader(MEMBERS_FILE));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                Member m = new Member(id, data[1], data[2]);
                if (data.length > 3) {
                    for (int i = 3; i < data.length; i++) {
                        m.addIssuedBook(Integer.parseInt(data[i]));
                    }
                }
                members.put(id, m);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error loading files: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE));
            for (Book b : books.values()) {
                bw.write(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getCategory() + "," + b.isIssued());
                bw.newLine();
            }
            bw.close();

            bw = new BufferedWriter(new FileWriter(MEMBERS_FILE));
            for (Member m : members.values()) {
                bw.write(m.getMemberId() + "," + m.getIssuedBooks().toString());
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            System.out.println("Error saving files: " + e.getMessage());
        }
    }


    public void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Title: ");
            String title = sc.nextLine();
            System.out.print("Enter Author: ");
            String author = sc.nextLine();
            System.out.print("Enter Category: ");
            String cat = sc.nextLine();

            Book b = new Book(id, title, author, cat);
            books.put(id, b);
            saveToFile();

            System.out.println("Book added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public void addMember() {
        try {
            System.out.print("Enter Member ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            if (!email.contains("@")) {
                System.out.println("Invalid email format.");
                return;
            }

            Member m = new Member(id, name, email);
            members.put(id, m);
            saveToFile();

            System.out.println("Member added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public void issueBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bid = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Member ID: ");
            int mid = Integer.parseInt(sc.nextLine());

            Book b = books.get(bid);
            Member m = members.get(mid);

            if (b == null || m == null) {
                System.out.println("Book or Member not found.");
                return;
            }
            if (b.isIssued()) {
                System.out.println("Book already issued.");
                return;
            }

            b.markAsIssued();
            m.addIssuedBook(bid);
            saveToFile();

            System.out.println("Book issued successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public void returnBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bid = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Member ID: ");
            int mid = Integer.parseInt(sc.nextLine());

            Book b = books.get(bid);
            Member m = members.get(mid);

            if (b == null || m == null) {
                System.out.println("Book or Member not found.");
                return;
            }
            if (!b.isIssued()) {
                System.out.println("Book is not issued.");
                return;
            }

            b.markAsReturned();
            m.returnIssuedBook(bid);
            saveToFile();

            System.out.println("Book returned successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public void searchBooks() {
        System.out.print("Search by Title: ");
        String key = sc.nextLine().toLowerCase();

        books.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(key))
                .forEach(Book::displayBookDetails);
    }

    public void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());
        Collections.sort(list); // by title

        System.out.println("Sorted Books (Title):");
        list.forEach(Book::displayBookDetails);
    }

    public void menu() {
        while (true) {
            System.out.println("\n===== City Library Digital Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1: addBook(); break;
                case 2: addMember(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: searchBooks(); break;
                case 6: sortBooks(); break;
                case 7: saveToFile(); System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        new LibraryManager().menu();
    }
}
