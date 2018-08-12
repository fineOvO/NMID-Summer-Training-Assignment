package libraryservice;

public class LibraryServiceTset {
    public static void main(String[] args) {
        // 建立一个图书馆对象
        Library library = new Library();
        // 建立实现Runnable接口类对象
        Borrower borrower = new Borrower(library);
        Returner returner = new Returner(library);
        // 借书和还书线程各建立5个
        Thread T_borrow1 = new Thread(borrower);
        Thread T_borrow2 = new Thread(borrower);
        Thread T_borrow3 = new Thread(borrower);
        Thread T_borrow4 = new Thread(borrower);
        Thread T_borrow5 = new Thread(borrower);

        Thread T_return1 = new Thread(returner);
        Thread T_return2 = new Thread(returner);
        Thread T_return3 = new Thread(returner);
        Thread T_return4 = new Thread(returner);
        Thread T_return5 = new Thread(returner);

        /* 由于每个线程执行过程较短，先锁住临界区，
           等10个线程全都开启后才释放锁，来保证10个线程执行的并发程度和随机性 */
        library.lock.lock();

        T_borrow1.start();
        T_borrow2.start();
        T_borrow3.start();
        T_borrow4.start();
        T_borrow5.start();

        T_return1.start();
        T_return2.start();
        T_return3.start();
        T_return4.start();
        T_return5.start();

        library.lock.unlock();

    }
}