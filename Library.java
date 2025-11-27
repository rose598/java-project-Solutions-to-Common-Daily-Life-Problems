public class Library {
    // 修正变量名拼写及命名规范
    String title[] = new String[50];
    String titleBorrowed[] = new String[50];
    String address;
    int count = 0;  // 修正拼写错误：cout -> count
    int countBorrowed = 0;  // 修正命名：cout_borrowed -> countBorrowed

    public Library(String address) {
        this.address = address;
    }

    void addBook(Book book) {
        // 增加数组容量检查，避免越界
        if (count < title.length) {
            title[count] = book.title;
            count++;
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    static void printOpeningHours() {
        System.out.println("Libraries are open daily from 9am to 5pm.");
    }

    void printAddress() {
        System.out.println(address);
    }

    void borrowBook(String bookTitle) {
        boolean flag = false;
        boolean flagBorrowed = false;  // 修正命名规范

        // 移除不必要的Book实例创建
        for (int i = 0; i < count; i++) {
            if (title[i] != null && title[i].equals(bookTitle)) {  // 增加非空判断
                // 寻找可存放的借阅位置
                for (int j = 0; j < titleBorrowed.length; j++) {
                    if (titleBorrowed[j] == null || titleBorrowed[j].isEmpty()) {
                        titleBorrowed[j] = bookTitle;
                        break;
                    }
                }
                // 标记书籍状态
                new Book(bookTitle).borrowed();  // 直接使用临时实例
                title[i] = "";
                flag = true;
                countBorrowed++;
                break;
            }
        }

        if (flag) {
            count--;
        } else {
            // 检查是否已被借阅
            for (int q = 0; q < countBorrowed; q++) {
                if (titleBorrowed[q] != null && titleBorrowed[q].equals(bookTitle)) {  // 增加非空判断
                    System.out.println("You have already borrowed " + bookTitle);
                    flagBorrowed = true;
                    break;
                }
            }
            if (!flagBorrowed) {
                System.out.println("Sorry, this book is not in our catalog.");
            }
        }
    }

    void returnBook(String bookTitle) {
        Book book = new Book(bookTitle);
        book.returned();  // 先执行归还逻辑

        // 放回可借列表
        for (int i = 0; i < title.length; i++) {
            if (title[i] == null || title[i].isEmpty()) {  // 增加非空判断
                title[i] = bookTitle;
                count++;
                break;
            }
        }

        // 从已借列表移除
        for (int i = 0; i < titleBorrowed.length; i++) {
            if (titleBorrowed[i] != null && titleBorrowed[i].equals(bookTitle)) {  // 增加非空判断
                titleBorrowed[i] = "";
                countBorrowed--;
                break;
            }
        }
    }

    void printAvailableBooks() {
        boolean hasBooks = false;
        for (int i = 0; i < title.length; i++) {
            if (title[i] != null && !title[i].isEmpty()) {  // 增加非空判断
                System.out.println(title[i]);
                hasBooks = true;
            }
        }
        if (!hasBooks) {
            System.out.println("No available books.");  // 增加无书提示
        }
    }

    public static void main(String[] args) {
        // 保持main方法逻辑不变
        Library firstLibrary = new Library("10 Main St");
        Library secondLibrary = new Library("228 Liberty St.");

        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));
        firstLibrary.printAvailableBooks();

        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();

        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
}

class Book {
    String title;
    boolean borrowed;

    public Book(String bookTitle) {
        title = bookTitle;
        borrowed = false;  // 显式初始化借阅状态
    }

    public void borrowed() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("You have borrowed " + title);
        } else {
            System.out.println("This book has already been borrowed.");
        }
    }

    public void returned() {
        if (borrowed) {
            borrowed = false;
            System.out.println("You have returned " + title);
        } else {
            System.out.println("This book has already been returned.");
        }
    }

    // 简化判断逻辑
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }
}