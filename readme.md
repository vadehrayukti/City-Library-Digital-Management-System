#  City Library Management System 

A Java-based **City Library Management System** demonstrating core Object-Oriented Programming and Exception Handling concepts. The project allows users to add books, issue books, return books, search books, and display the full library collection.

## YUKTI VADEHRA, ROLL NO. 2401010128, BTECH CSE(CORE)
This system is menu-driven and terminal-based, built using:

- Classes & Objects
- Arrays
- Custom Exceptions
- `try-catch`, `throw`, and `throws`
- Input validation

---

##  Features

###  Book Management
- Add new books
- Display all books
- Search for a book by Book ID

###  Issue & Return System
- Issue a book to a user
- Prevent issuing already issued books
- Return issued books
- Display issued status

###  Exception Handling
- Custom Exception: `BookNotFoundException`
- Validates:
  - Book ID existence
  - Book availability
  - Already returned books

###  Clean Exit Control
- Handles invalid input using `try-catch`
- Prevents system crash through safe error messages

---

# Sample Output
Here is a sample run of the program:

```
===== CITY LIBRARY SYSTEM =====
1. Add Book
2. Issue Book
3. Return Book
4. Search Book
5. Display All Books
6. Exit
Enter your choice: 1
Enter Book ID: 101
Enter Book Name: Java Programming
Enter Author Name: James Gosling
Book added successfully!

===== CITY LIBRARY SYSTEM =====
Enter your choice: 5

--- Library Books ---
ID: 101 | Name: Java Programming | Author: James Gosling | Status: Available

===== CITY LIBRARY SYSTEM =====
Enter your choice: 2
Enter Book ID to issue: 101
Book issued successfully!

===== CITY LIBRARY SYSTEM =====
Enter your choice: 2
Enter Book ID to issue: 101
Error: Book is already issued.

===== CITY LIBRARY SYSTEM =====
Enter your choice: 3
Enter Book ID to return: 101
Book returned successfully!

===== CITY LIBRARY SYSTEM =====
Enter your choice: 4
Enter Book ID to search: 101
Book Found:
ID: 101
Name: Java Programming
Author: James Gosling
Status: Available

===== CITY LIBRARY SYSTEM =====
Enter your choice: 6
Exiting system. Thank you!


