package libraryservice;

public class Returner implements Runnable {
    private Library library;
    public Returner(Library library) {
        this.library = library;
    }
    @Override
    public void run() {
        library.giveBack();
    }
}
