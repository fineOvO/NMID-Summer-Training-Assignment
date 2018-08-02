package rentcar;

import java.util.Scanner;

public class TestRentCars {
    public static void main(String[] args){
        // 建立租车事务对象
        RentCars rentCars = new RentCars();

        // 载入现有车辆
        rentCars.addCars();

        // 展示所有车的信息
        System.out.println("Available Cars For Now :");
        rentCars.displayAll();

        // 确认租车数量
        System.out.println("\nPlease check num of cars you need rent :");
        rentCars.inputNums();
        System.out.println("Check over.Thank You!\n");

        // 确认租车天数
        System.out.println("How many days you want to rent these cars:");
        rentCars.inputDays();
        System.out.println("Check over.Thank You!\n");

        //数据的最终结算与展示
        System.out.println("Please checkout the following renting information:");
        rentCars.CheckOut();
        System.out.println("Press enter to finish check.");
        // 等待用户回车确认
        Scanner in = new Scanner(System.in);
        in.nextLine();
        System.out.println("Check over.Welcome!");







    }
}

