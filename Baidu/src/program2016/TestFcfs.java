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
        //����FCFS�㷨
        List<Double> list=FCFS(works);
        System.out.println("FCFS���㷨��ƽ����תʱ��"+list.get(0));
        System.out.println("FCFS���㷨��ƽ����Ȩ��תʱ��"+list.get(1));

    }
    /**
     * �����ȷ�������㷨
     * @param works
     * @return
     */
    public static List<Double> FCFS(List<Work> works){
        double avgCircleTime=0;
        double avgCircleTimeWP=0;
        List<Double> lst=new ArrayList<Double>();
        for (int i = 0; i < works.size(); i++) {

            if(i!=0){
                //����һ����ҵ��Ŀ�ʼʱ������Ϊ��һ����ҵ��ִ�н���ʱ��
                works.get(i).setBeginTime(works.get(i-1).getEndTime());
                System.out.println("��ҵ" + works.get(i).name + "ִ��" + works.get(i).serviceTime);
            }else{
                //��һ����ҵ�Ŀ�ʼʱ������Ϊ����ҵ�ĵ���ʱ��
                works.get(i).setBeginTime(works.get(i).getArrivalTime());
                System.out.println("��ҵ" + works.get(i).name + "ִ��" + works.get(i).serviceTime);
            }
            //��ҵ�Ľ���ʱ������Ϊ��ҵ�Ŀ�ʼʱ����Ϸ���ʱ��
            works.get(i).setEndTime(works.get(i).getBeginTime()+works.get(i).getServiceTime());
            //��ҵ����תʱ��(��ҵ�����ʱ��-��ҵ�ĵ���ʱ��)�ۼ�
            avgCircleTime+=works.get(i).getCircleTime();
            //��ҵ�Ĵ�Ȩ��תʱ��((��ҵ�����ʱ��-��ҵ�ĵ���ʱ��)/���õķ���ʱ��)�ۼ�
            avgCircleTimeWP+=works.get(i).getCircleWPTime();
        }
        avgCircleTime/=works.size();
        avgCircleTimeWP/=works.size();
        lst.add(avgCircleTime);
        lst.add(avgCircleTimeWP);
        return lst;
    }
}
