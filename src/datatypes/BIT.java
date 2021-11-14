package datatypes;

import utils.TimeCal;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @Author yoyuan
 * @Description: Binary Index Tree
 * @DateTime: 2021/11/13 15:35
 */
public class BIT {

    private List<List<Data<Integer>>> nodes;

    private int len;

    public BIT(int length) {
        length += 1;
        this.nodes = new ArrayList<>();
        for(int i = 0; i < length; ++i){
            nodes.add(new ArrayList<>());
        }
        this.len = length;
    }
    public void addNode(int index, Data<Integer> value){
        index += 1;
        while(index < len){
            nodes.get(index).add(value);
            index += lowBit(index);
        }
    }
    public boolean searchAndUpdateResult(int index, ArrayList<Integer>res, ArrayList<Integer>a,ArrayList<Data<Integer>>cond1 ){
        index += 1;
        int tmp = index;
        int before = res.size();
        while(index > 0){
            for(int i = 0; i < nodes.get(index).size(); ++i){
                Data<Integer> tmp2 = nodes.get(index).get(i);
                long l1 = System.currentTimeMillis();
                if(cond1.get(tmp - 1).compareTo(tmp2.getRank()) != 0 && cond1.get(tmp - 1).compareTo(tmp2.getValue()) != 0) {
                    res.add(tmp2.getRow());
                    a.add(cond1.get(tmp - 1).getRow());
                }
                TimeCal.time2 += (System.currentTimeMillis() - l1);
            }
            index -= lowBit(index);
        }
        if(res.size() > before){
            return true;
        }else {
            return false;
        }
    }
    private int lowBit(int x ){
        return x & (-x);
    }
}
