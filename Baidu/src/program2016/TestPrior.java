package program2016;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wtq on 5/7/16.
 */
public class TestPrior {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Work A=new Work("A",0,6);
        Work B=new Work("B",2,50);
        Work H=new Work("H",2,48);
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
        List<Double> list=Priority(works);
        System.out.println("Prior���㷨��ƽ����תʱ��"+list.get(0));
        System.out.println("Prior���㷨��ƽ����Ȩ��תʱ��"+list.get(1));

    }

    public static List<Double> Priority(List<Work> works){
        List<Double> lst=new ArrayList<Double>();
        double avgCircleTime=0;
        double avgCircleTimeWP=0;
        int lastOneComeIn = works.get(works.size()-1).getArrivalTime();
        List<Work> ins=new ArrayList<Work>();
        int i=0;
        while (i <= lastOneComeIn) {
            //�൱һ��ʱ�ӵ�����
            for (int j = 0; j < works.size(); j++) {
                if (works.get(j).getArrivalTime() <= i) {
                    ins.add(works.get(j));
                    //�����������ҵҲ����ﵽʱ���ڵ�ǰʱ��֮ǰ����ôҲ����ϵͳ��
                    while (j+1<works.size()&&works.get(j + 1).getArrivalTime() <=i) {
                        ins.add(works.get(j + 1));
                        j++;
                    }
                    ins=sortByServiceTime(ins);
                    for (int k = 0; k < ins.size(); k++) {
                        //��ʼ������ҵ
                        Work last = new Work("A",0,6);
                        Work now=ins.get(0);
                        if (k != 0) {
                            //���ö�������ҵ�����ȼ�
                            for (int t = 1; t < ins.size(); t++) {
                                ins.get(t).setPriority(last.getEndTime() - ins.get(t).getArrivalTime());
                            }
                            ins = sortByPriority(ins);
                            now = ins.get(0);
                            now.setBeginTime(last.getEndTime());
                        } else {

                            now.setBeginTime(now.getArrivalTime());
                        }
                        now.setEndTime(now.getBeginTime()+now.getServiceTime());
                        last = now;
                        System.out.println(last.getPriority());
                        i=now.getEndTime();
                        ins.remove(0);
                        //������תʱ��ʹ�Ȩ��תʱ����ܺ�
                    }

                }else{
                    j--;
                    i++;
                    break;
                }
            }
            //�ж��Ƿ����е���ҵ���ѵ���ϵͳ,����ѵ��룬���˳���һ��whileѭ����
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
     * ����Ȩ�����㷨
     * @param works
     * @return
     */
    public static int PF(Work [] works){
        //TODO
        return 0;
    }
    /**
     * �Լ��뵽ϵͳ�е���ҵ���ݷ���ʱ����ٽ�������<BR>
     * Ȼ��ֱ�ӷ���
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
                    //�൱����ins��i��i+1����λ��
                    ins.remove(j);
                    ins.add(i, bw);
                }
            }
        }
        return ins;
    }

    /**
     * ����ҵ���ȼ��ɸߵ�������
     * @param ins
     * @return
     */
    public static List<Work> sortByPriority(List<Work> ins){
        for (int i = 0; i < ins.size(); i++) {
            for(int j=i+1;j<ins.size();j++){
                Work aw=ins.get(i);
                float a=aw.getPriority();
                Work bw=ins.get(j);
                float b=bw.getPriority();
                if(a<b){
                    //�൱����ins��i��i+1����λ��
                    ins.remove(j);
                    ins.add(i, bw);
                }
            }
        }
        return ins;

    }
}
