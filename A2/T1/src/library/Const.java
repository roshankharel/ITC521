package library;


public final class Const {
    private Const() {}

    public static final String GREETING = "## Welcome to the Home Library System ##";
    public static final String BOOK_REPRESENTATION = "Title: %s\nAuthor: %s\nYear: %s\nISBN: %s";

    public static class Menu {
        private Menu() {}

        public static class Heading {
            private Heading() {}

            public static final String Application = "Please select one of the following options";
            public static final String SearchBook = "Search for a book by";
            public static final String SearchBookDeletion = "Search a book for deletion by";
        }

        public static class Order {
            private Order() {}

            // application menu order constants
            public static final int ADD_BOOK = 1;
            public static final int DELETE_BOOK = 2;
            public static final int SEARCH_BOOKS = 3;
            public static final int DISPLAY_ALL_BOOKS = 4;
            public static final int EXIT = 5;

            // search book order constants
            public static final int CANCEL_ACTION = 0;
            public static final int BY_TITLE = 1;
            public static final int BY_AUTHOR = 2;
            public static final int BY_YEAR = 3;
            public static final int BY_ISBN = 4;
        }

        public static class Description {
            private Description() {}

            public static final String ADD_BOOK = "Add a book";
            public static final String DELETE_BOOK = "Delete a book";
            public static final String SEARCH_BOOKS = "Search for a book";
            public static final String DISPLAY_ALL_BOOKS = "Display all books";
            public static final String EXIT = "Exit";

            // search book order constants
            public static final String CANCEL_ACTION = "Cancel & goto previous menu";
            public static final String BY_TITLE = "Title";
            public static final String BY_AUTHOR = "Author";
            public static final String BY_YEAR = "Year of Publication";
            public static final String BY_ISBN = "ISBN number";
        }
    }

    public static class Message {

        private Message() {}

        public static class Info {
            private Info() {}

            public static final String NO_BOOKS = "Info: Currently, there are no books in the library.";
            public static final String SAVING_BOOKS = "Info: Saving books to persistence";
        }

        public static class Error {
            private Error() {}

            public static final String SAVING_BOOKS = "Error: There was an error when saving books";
        }

        public static final String ALL_BOOKS = "These are all the books added to the library.";
        public static final String FAREWELL = "Thank you for using the program.\nGood bye!";

        public static class Success {
            private Success() {}

            public static final String ADD_BOOK = "Success: Book has been added to the library.";
        }

        public static class Validation {
            private Validation() {}

            public static final String REQUIRED = "Error: %s field cannot be blank or only whitespace(s).";
            public static final String POSITIVE_INT = "Error: %s field must be a positive integer, '%s' given.";
            public static final String INT = "Error: %s field must be an integer, '%s' given.";
            public static final String ISBN = "Error: %s field is not in valid ISBN format i.e 10 digit number, '%s' given.";
            public static final String IN_RANGE = "Error: Please select from available options %s.";
            public static final String YEAR = "Error: %s field must be of 4 digit number with no leading zeros, '%s' given.";
        }
    }
}
