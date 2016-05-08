package program2016;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wtq on 5/5/16.
 */
public class TestFcfs {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Work A=new Work("A",0,6);
        Work B=new Work("B",2,50);
        Work C=new Work("C",5,20);
        Work D=new Work("D",5,10);
        Work E=new Work("E",12,40);
        Work F=new Work("F",15,8);
        List<Work> works=new ArrayList<Work>();
        works.add(A);
        works.add(B);
        works.add(C);
        works.add(D);
        works.add(E);
        works.add(F);
        //调用FCFS算法
        List<Double> list=FCFS(works);
        System.out.println("FCFS的算法的平均周转时间"+list.get(0));
        System.out.println("FCFS的算法的平均带权周转时间"+list.get(1));

    }
    /**
     * 先来先服务调度算法
     * @param works
     * @return
     */
    public static List<Double> FCFS(List<Work> works){
        double avgCircleTime=0;
        double avgCircleTimeWP=0;
        List<Double> lst=new ArrayList<Double>();
        for (int i = 0; i < works.size(); i++) {

            if(i!=0){
                //除第一个作业外的开始时间设置为上一个作业的执行结束时间
                works.get(i).setBeginTime(works.get(i-1).getEndTime());
                System.out.println("作业" + works.get(i).name + "执行" + works.get(i).serviceTime);
            }else{
                //第一个作业的开始时间设置为该作业的到达时间
                works.get(i).setBeginTime(works.get(i).getArrivalTime());
                System.out.println("作业" + works.get(i).name + "执行" + works.get(i).serviceTime);
            }
            //作业的结束时间设置为作业的开始时间加上服务时间
            works.get(i).setEndTime(works.get(i).getBeginTime()+works.get(i).getServiceTime());
            //作业的周转时间(作业的完成时间-作业的到达时间)累加
            avgCircleTime+=works.get(i).getCircleTime();
            //作业的带权周转时间((作业的完成时间-作业的到达时间)/作用的服务时间)累加
            avgCircleTimeWP+=works.get(i).getCircleWPTime();
        }
        avgCircleTime/=works.size();
        avgCircleTimeWP/=works.size();
        lst.add(avgCircleTime);
        lst.add(avgCircleTimeWP);
        return lst;
    }
}
