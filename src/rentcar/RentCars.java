package rentcar;
import java.util.ArrayList;
import java.util.Scanner;

public class RentCars {

    public int days; // 租车天数
    public int[] nums; // 存放各车租用数量数组的引用，在inputDays()方法中指向实例。

    // 父类类型的ArrayList集合。
    private ArrayList<AbstractCar> cars = new ArrayList<AbstractCar>();

    //向cars集合中添加拥有车辆
    public void addCars() {
        // 运用了多态的特性，用父类类型的集合存放各子类的对象。
        // 实际运用时使用文件读入更合理。
        cars.add(new PassengerCar(1, "小轿车", 40, 4));
        cars.add(new PassengerCar(2, "面包车", 65, 7));
        cars.add(new PassengerCar(3, "大巴车", 180, 20));

        cars.add(new Truck(4, "低栏车", 30, 1.5));
        cars.add(new Truck(5, "高栏车", 47, 2.5));
        cars.add(new Truck(6, "厢式车", 80, 4.5));

        cars.add(new Pickup(7, "日式皮卡车", 60, 5, 1));
        cars.add(new Pickup(8, "美式皮卡车", 60, 2, 3));
    }

    // 展示所有汽车
    public void displayAll(){
        System.out.println("序号\t车辆名称\t租车费用（元/（车·天））\t最大载人（人）\t最大载货（吨）");
        // forEach遍历,打印各车信息。
        for(AbstractCar car:cars){
            car.display();
        }
    }

    // 输入各车租用数量
    public void inputNums(){
        Scanner in = new Scanner(System.in);
        // 现有多少种车就定义多大的数组存放各车的租用数量。
        nums = new int[cars.size()];

        // forEach遍历,输入各车租用数量。
        for(AbstractCar car:cars){
            System.out.println("请输入序号"+car.no+"（"+car.name+"）的租用数量：");
            nums[car.no-1]= in.nextInt();
        }
    }

    // 输入租用天数
    public void inputDays(){
        Scanner in = new Scanner(System.in);
        days = in.nextInt();
    }

    //数据的最终结算与展示
    public void CheckOut(){

        // 计算并展示总载人数和总载货量。
        int capacitySum = 0;
        double burdenSum = 0;
        for(AbstractCar car:cars){
            if (car instanceof PassengerCar){
                PassengerCar passengerCar = (PassengerCar)car;
                capacitySum += passengerCar.capacity*nums[car.no-1];
            } else if (car instanceof Truck) {
                Truck truck = (Truck)car;
                burdenSum += truck.burden*nums[car.no-1];
            } else {
                Pickup pickup = (Pickup)car;
                capacitySum += pickup.capacity*nums[car.no-1];
                burdenSum += pickup.burden*nums[car.no-1];
            }
        }
        System.out.println("总载人数： "+capacitySum+" 人");
        System.out.println("总载货量： "+burdenSum+" 吨");

        // 计算并展示总租金。
        double chargeSum = 0;
        for(AbstractCar car:cars){
            chargeSum += car.charge*days*nums[car.no-1];
        }
        System.out.println("总租金： "+chargeSum+" 元");

        // 展示租车天数
        System.out.println("租用时间： "+days+" 天");

        // 展示租车总数
        int numsSum = 0;
        for(int num:nums){
            numsSum += num;
        }
        System.out.println("租用车辆总数： "+numsSum+" 辆");

        // 展示各个车辆的租用数目
        System.out.println("\n各车辆租用数量：");
        System.out.println("序号\t车辆名称\t租用数量");
        for(AbstractCar car:cars){
            System.out.println(String.format("No.%d\t%-5s\t   %d",car.no,car.name,nums[car.no-1]));
        }
    }
}
