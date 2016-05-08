package program2016;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wtq on 5/4/16.
 */
public class Test {


        /**
         * @param args
         */
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
            //list.remove(0);
            //list.remove(1);
            list=SJF(works);
            System.out.println("SJF的算法的平均周转时间"+list.get(0));
            System.out.println("SJF的算法的平均带权周转时间"+list.get(1));
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
                //  works.get(i).getArrivalTime();
                //  works.get(i).getServiceTime();
                if(i!=0){
                    //除第一个作业外的开始时间设置为上一个作业的执行结束时间
                    works.get(i).setBeginTime(works.get(i-1).getEndTime());
                }else{
                    //第一个作业的开始时间设置为该作业的到达时间
                    works.get(i).setBeginTime(works.get(i).getArrivalTime());
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
        /**
         * 短作业优先调度算法
         * @param works
         * @return
         */
        public static List<Double> SJF(List<Work> works){
            List<Double> lst=new ArrayList<Double>();
            double avgCircleTime=0;
            double avgCircleTimeWP=0;
            int lastOneComeIn = works.get(works.size()-1).getArrivalTime();
            List<Work> ins=new ArrayList<Work>();
            int i=0;
            while (i <= lastOneComeIn) {
                //相当一个时钟的作用
                for (int j = 0; j < works.size(); j++) {
                    if (works.get(j).getArrivalTime() <= i) {
                        ins.add(works.get(j));
                        //如果后续的作业也满足达到时间在当前时间之前，那么也加入系统中
                        while (j+1<works.size()&&works.get(j + 1).getArrivalTime() <=i) {
                            ins.add(works.get(j + 1));
                            j++;
                        }
                        ins=sortByServiceTime(ins);
                        for (int k = 0; k < ins.size(); k++) {
                            //开始按段作业
                            Work now=ins.get(k);
                            if (k != 0) {
                                Work last=ins.get(k-1);
                                now.setBeginTime(last.getEndTime());
                            } else {

                                now.setBeginTime(now.getArrivalTime());
                            }
                            now.setEndTime(now.getBeginTime()+now.getServiceTime());
                            i=now.getEndTime();
                            ins.remove(k);
                            //计算周转时间和带权周转时间的总和
                        }

                    }else{
                        j--;
                        i++;
                        break;
                    }
                }
                //判断是否所有的作业都已调入系统,如果已调入，则退出第一个while循环体
                if(i>lastOneComeIn){
                    break;
                }
            }
            for (int m = 0; m< works.size(); m++) {
                avgCircleTime+=works.get(m).getCircleTime();
                avgCircleTimeWP+=works.get(m).getCircleWPTime();
            }
            avgCircleTime=avgCircleTime/works.size();
            avgCircleTimeWP=avgCircleTimeWP/works.size();
            lst.add(avgCircleTime);
            lst.add(avgCircleTimeWP);
            return lst;
        }
        /**
         * 优先权调度算法
         * @param works
         * @return
         */
        public static int PF(Work [] works){
            //TODO
            return 0;
        }
        /**
         * 对加入到系统中的作业依据服务时间多少进行排序<BR>
         * 然后直接返回
         * @param ins
         * @return ins
         */
        public static List<Work> sortByServiceTime(List<Work> ins){
            for (int i = 0; i < ins.size(); i++) {
                for(int j=i+1;j<ins.size();j++){
                    Work aw=ins.get(i);
                    int a=aw.getServiceTime();
                    Work bw=ins.get(j);
                    int b=bw.getServiceTime();
                    if(a>b){
                        //相当于在ins中i与i+1调换位置
                        ins.remove(j);
                        ins.add(i, bw);
                    }
                }
            }
            return ins;
        }

}
