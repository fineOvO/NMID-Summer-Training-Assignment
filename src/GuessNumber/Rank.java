package GuessNumber;


public class Rank {
    int top10[]={999,999,999,999,999,999,999,999,999,999};
    //更新排行榜
    void entering(int count){
        for(int i=0;i<10;i++){
            if(count<=top10[i]){
                for(int j=9;j>=i+1;j--){
                    top10[j]=top10[j-1];
                }

                top10[i]=count;
                break;
            }
        }
    }
    //输出排行榜
    void printing(){
        System.out.println("\n排行榜");
        for(int i=0;i<10;i++){
            if(top10[i]==999)
                System.out.println(String.format("NO.%-2d: 无   ",i+1));
            else
            System.out.println(String.format("NO.%-2d: %d次   ",i+1,top10[i]));
        }
        System.out.println();
    }
}
