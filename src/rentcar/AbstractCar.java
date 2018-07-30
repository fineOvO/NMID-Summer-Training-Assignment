package rentcar;

public abstract class AbstractCar {
    protected int no;
    protected String name;
    protected double charge;

    public  AbstractCar(int no, String name, float charge){
        if (no>0 && charge>0){
            this.no = no;
            this.name = name;
            this.charge = charge;
        }
        else {
            System.out.println("Input Error");
        }
    }

    // 打印车辆信息
    public abstract void display();

    // 设置序号
    public void setNo(int no) {
        if (no<0) {
            System.out.println("Input Error: no<0");
        }
        else {
            this.no = no;
        }
    }

    // 设置车辆名称
    public void setName(String name){
        this.name = name;
    }

    // 设置租车费用
    public void setCharge(float charge){
        if (charge<0) {
            System.out.println("Input Error: charge<0");
        }
        else {
            this.charge = charge;
        }
    }


}
