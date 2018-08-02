package libraryservice;

import java.util.concurrent.locks.*;

class Library{
    // 书本库存量，初始库存为1
    private int bookStock = 1;
    // 最大库存量
    private final int MAX_STOCK = 3;
    // 空库存量
    private final int EMPTY_STOCK = 0;
    // 创建一把锁
    protected Lock lock = new ReentrantLock();
    // 创建stockFull条件队列并绑定在lock上，当还书线程发现书本库存满时，没有书可还，等待在该条件上。
    private Condition stockFull = lock.newCondition();
    // 创建stockEmpty条件队列并绑定在lock上，当借书线程发现书本库为空时等待在该条件上。
    private Condition stockEmpty = lock.newCondition();


    // 还书功能
    public void giveBack(){
        lock.lock();
        try{
            // 当书本库存满时，没有书可还，结束
            if (bookStock==MAX_STOCK){
                System.out.println(Thread.currentThread().getName()+" 还书失败：没有书借出，无需还书。\n");
                return;
            } else {    // 书本库存未满，进行还书操作。
                // 还一本书，库存加一。
                bookStock++;
                // 显示图书馆剩余书本数量
                System.out.println(Thread.currentThread().getName()+" 还书成功！\n" + showStock()+"\n");
                // 还书成功一次后，库存肯定不为空，唤醒一个因库存为空而等待在stockEmpty条件下的借书线程。
                stockEmpty.signal();
            }
        }
        finally{
            // 不管还书是否成功，退出时都要释放锁。
            lock.unlock();
        }
    }

    //借书功能
    public void borrow(){
        lock.lock();
        try{
            //当书本库存空无书可借时，等待
            while(bookStock==EMPTY_STOCK){
                System.out.println(Thread.currentThread().getName()+" 借书失败：抱歉，图书库存为空，请等候。\n");
                stockEmpty.await();
            }
            // 书本库存不为空时，进行借书操作。
            // 借走一本，库存减一
            bookStock--;
            // 显示图书馆剩余书本数量
            System.out.println(Thread.currentThread().getName()+" 借书成功！\n"+showStock()+"\n");
            // 借书成功一次后，一定有书可还，唤醒一个等待在stockFull条件下的还书线程。
            stockFull.signal();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            // 不管借书是否成功，退出时都要释放锁。
            lock.unlock();
        }
    }

    public String showStock(){
        return "图书馆剩余库存为："+bookStock+" 本";
    }
}