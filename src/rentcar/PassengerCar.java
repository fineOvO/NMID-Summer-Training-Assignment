package rentcar;

public class PassengerCar extends AbstractCar {

    protected int capacity;  // 载人数

    public PassengerCar(int no, String name, float charge, int capacity){
        // 调用父类构造方法初始化共有属性。
        super(no, name,charge);
        if(capacity>0){
            this.capacity = capacity;
        }
        else {
            System.out.println("Input Error");
        }
    }

    @Override
    public void display() {
        System.out.println("No."+no+"\t  "+name+"  \t      "+charge+"                  \t"+capacity+"\t             /");
    }

    // 设置载人数
    public void setCapacity(int no) {
        if (capacity<0) {
            System.out.println("Input Error: capacity<0");
        }
        else {
            this.capacity = capacity;
        }
    }
}


