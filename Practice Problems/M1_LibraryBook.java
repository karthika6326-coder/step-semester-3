class LibraryBook {
    String title, isbn;

    LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    LibraryBook(String title) {
        this(title, "PENDING");
    }
}

public class M1_LibraryBook {
    public static void main(String[] args) {
        String[] t = {"Clean Code","Untitled Draft","1984","Notes"};
        String[] i = {"978-0132350884","","9780451524935",""};

        for (int x = 0; x < t.length; x++) {
            LibraryBook b = i[x].equals("")
                    ? new LibraryBook(t[x])
                    : new LibraryBook(t[x], i[x]);

            System.out.println(b.title + " | " + b.isbn + " | Catalogued: true");
        }
    }
}
