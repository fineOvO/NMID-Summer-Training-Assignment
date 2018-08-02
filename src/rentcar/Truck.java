package rentcar;

public class Truck extends AbstractCar {

    protected double burden;  // 载货量

    public Truck(int no, String name, float charge, double burden){
        // 调用父类构造方法初始化共有属性。
        super(no, name, charge);
        if(burden>0){
            this.burden = burden;
        }
        else {
            System.out.println("Input Error");
        }
    }

    @Override
    public void display() {
        System.out.println("No."+no+"\t  "+name+"  \t      "+charge+"                  \t/"+"          \t    "+burden);
    }

    // 设置载货量
    public void setBurden(double burden) {
        if (burden<0) {
            System.out.println("Input Error: burden<0");
        }
        else {
            this.burden = burden;
        }
    }
}



