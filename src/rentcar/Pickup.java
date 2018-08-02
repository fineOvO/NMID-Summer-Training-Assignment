package rentcar;

public class Pickup extends AbstractCar {

    protected int capacity;  // 载人数
    protected float burden;  // 载货量

    public Pickup(int no, String name, float charge, int capacity, float burden){
        // 调用父类构造方法初始化共有属性。
        super(no, name, charge);
        if(capacity>0 && burden>0){
            this.capacity = capacity;
            this.burden = burden;
        }
        else {
            System.out.println("Input Error");
        }
    }

    @Override
    public void display() {
        System.out.println("No."+no+"\t"+name+"  \t  "+charge+"                  \t"+capacity+"          \t    "+burden);
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

    // 设置载货量
    public void setBurden(int no) {
        if (burden<0) {
            System.out.println("Input Error: burden<0");
        }
        else {
            this.burden = burden;
        }
    }
}


