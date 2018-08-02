package GuessNumber;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberGuess {
    // 随机数的最大值和最小值
    static final int MAX = 100;
    static final int MIN = 1;
    Rank r = new Rank();
    private int number;  //number 为用户需要猜的随机数


    public void startGame() {
        //玩完一次猜数游戏后就进入系统阶段，让用户选择再玩一次，查看排行榜，或退出游戏。
        while (true) {
            number = (int) (Math.random() * 100) + 1;   // 产生一个 1~ 100 的随机值并赋给 number
            systemStage();                         //进入系统阶段
        }
    }

    public void systemStage() {
        String controller = null;
        final String BEGIN = "begin";
        final String END = "shut down";
        final String RANK = "rank";

        do {
            if (RANK.equals(controller)) {
                /*显示排行榜*/
                r.printing();
            } else if (controller != null) {
                System.out.println("Input error");
            }

            System.out.println("\nPlease Input:\n          \"begin\" to start a new game\n          "
                    + "\"shut down\" to exit game\n          \"rank\" to check in rank\n");

            Scanner sc = new Scanner(System.in);
            controller = sc.nextLine();

        } while (!(END.equals(controller) || BEGIN.equals(controller)));

        if (END.equals(controller)) {
            System.out.println("Game Over!");
            System.exit(0);
        } else
            gameStage();
    }


    public void gameStage() {
        int count = 0, max = MAX, min = MIN;
        while (true) {

            // 键盘录入数据
            System.out.println("请输入你猜的数：(" + min + "~" + max + ")");
            Scanner sc = new Scanner(System.in);
            try {
                count++;
                int guessNumber = sc.nextInt();
                // 判断
                if (guessNumber > number) {
                    System.out.println("你猜大了");
                    max = guessNumber - 1;
                } else if (guessNumber < number) {
                    System.out.println("你猜小了");
                    min = guessNumber + 1;
                } else {
                    System.out.println("恭喜你,花了" + count + "次就猜中了");
                    //录入排行榜
                    r.entering(count);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("你输入的数据有误");
            }
        }
    }

}
