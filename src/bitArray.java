import datatypes.Data;
import datatypes.DataComparator;
import datatypes.Pair;
import utils.TimeCal;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
/**
 * IEJoin
 * */
@SuppressWarnings("all")
public class bitArray {
	public Pair<ArrayList<Integer>, ArrayList<Integer>> join(int arraySize, int[][] array1) {

	// array1存放的是数据

		//permutation
		HashMap<Integer, Integer> mapForPerm = new HashMap<>(arraySize);

		// 两个连接条件 -> 两个谓词
		ArrayList<Data<Integer>> cond1 = new ArrayList<Data<Integer>>(arraySize);
		ArrayList<Data<Integer>> cond2 = new ArrayList<Data<Integer>>(arraySize);

		for (int i = 0; i < arraySize; i++) {
			cond1.add(new Data<Integer>(i, array1[i][0], array1[i][1],false));
			cond2.add(new Data<Integer>(i, array1[i][1], array1[i][0],false));
		}

		// 按照升/降序排序决定了谓词的形式 cond1 按第一列排序， cond2按第二列排序
		Collections.sort(cond1, new DataComparator(false, true));
		Collections.sort(cond2, new DataComparator(false, true));



		// 每一行对应第一列在cond1的排名
		for (int i = 0; i < cond1.size(); i++) {
			mapForPerm.put(cond1.get(i).getRow(), i);
		}

		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();

		int[] permutationArray = new int[cond2.size()];

		// cond2中每一行对应在cond1中的排名
		for (int k = 0; k < cond2.size(); k++) {
			permutationArray[k] = mapForPerm.get(cond2.get(k).getRow());
		}

		int chunckSize = Math.min(permutationArray.length, 1024); // in bit

		// 用了bitmap优化，将bitset划分模块管理，按b升序，所以按cond1顺序插入的时候
		BitSet bitArray = new BitSet(cond2.size());
		int indexSize = cond2.size() / chunckSize;

		if (cond2.size() % chunckSize != 0)
			++indexSize;

		short[] bitIndex = new short[indexSize];

		for (int k = 0; k < bitIndex.length; k++) {
			bitIndex[k] = 0;
		}

		// System.out.println("ChunkSize " + chunckSize);
		// System.out.println("indexSize " + indexSize);

		for (int k = 0; k < permutationArray.length; k++) {
			// scan bit index
			int iter = 0;

			for (int z = (permutationArray[k] / chunckSize); z < bitIndex.length; z++) {
				if (bitIndex[z] > 0) {
					// scan the chunk
					int start = iter == 0 ? permutationArray[k] + 1 : z * chunckSize;
					int end = (z * chunckSize) + chunckSize;

					for (int l = start; l < end; l++) {
						long l1 = System.currentTimeMillis();
						if (bitArray.get(l)) {
							//Filter conditions 只要不是完全一样就行了，因为这是比较大于或者小于号
							if (cond1.get(permutationArray[k]).compareTo(cond1.get(l)) != 0
									&& cond1.get(permutationArray[k]).compareRank(cond1.get(l).getRank()) != 0) {
								a.add(cond1.get(permutationArray[k]).getRow());
								b.add(cond1.get(l).getRow());
							}
							TimeCal.time1 += (System.currentTimeMillis() - l1);
						}
					}
				}
				iter++;
			}
			bitArray.set(permutationArray[k]);
			bitIndex[permutationArray[k] / chunckSize] = (short) (bitIndex[permutationArray[k]
					/ chunckSize] + 1);
		}
		return new Pair<ArrayList<Integer>, ArrayList<Integer>>(a, b);
	}
}
