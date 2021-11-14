import datatypes.BIT;
import datatypes.Data;
import datatypes.DataComparator;
import datatypes.Pair;
import utils.TimeCal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @Author yoyuan
 * @Description: use bit to accelerate IEJoin
 * @DateTime: 2021/11/13 15:47
 */
public class BITJoin {
    public Pair<ArrayList<Integer>, ArrayList<Integer>> join(int arraySize, int[][] array1){



        ArrayList<Data<Integer>> cond1 = new ArrayList<Data<Integer>>(arraySize);
        ArrayList<Data<Integer>> cond2 = new ArrayList<Data<Integer>>(arraySize);

        for (int i = 0; i < arraySize; i++) {
            cond1.add(new Data<Integer>(i, array1[i][0], array1[i][1],false));
            cond2.add(new Data<Integer>(i, array1[i][1], array1[i][0],false));
        }

        // 按A,B排序
        // A和B两个谓词的操作符相反，所以A升序B降序
        Collections.sort(cond1, new DataComparator(true, false));
        Collections.sort(cond2, new DataComparator(false, true));


        // 按B的降序插入，所以要找到B跟A的对应关系 用permutationArray记录
        HashMap<Integer, Integer> mapForPerm = new HashMap<>(arraySize);
        for (int i = 0; i < cond1.size(); i++) {
            mapForPerm.put(cond1.get(i).getRow(), i);
        }
        int[] permutationArray = new int[cond2.size()];
        for (int k = 0; k < cond2.size(); k++) {
            permutationArray[k] = mapForPerm.get(cond2.get(k).getRow());
        }
        ArrayList<Integer> a = new ArrayList<Integer>();
        ArrayList<Integer> b = new ArrayList<Integer>();

        // 初始化树状数组，以A的升序为基础
        BIT bit = new BIT(cond1.size());
        for(int i = 0; i < permutationArray.length; ++i){
            // 找到需要插入树状数组的下标
            int index = permutationArray[i];
            // 得到结果

            bit.searchAndUpdateResult(index, b, a, cond1);
            
            //插入
            bit.addNode(index, cond2.get(i));

        }
        return new Pair<ArrayList<Integer>, ArrayList<Integer>>(a, b);
    }

}
