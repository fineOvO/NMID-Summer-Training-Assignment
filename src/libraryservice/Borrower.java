package libraryservice;

public class Borrower implements Runnable {
    private Library library;
    public Borrower(Library library) {
        this.library = library;
    }
    @Override
    public void run() {
        library.borrow();
    }
}