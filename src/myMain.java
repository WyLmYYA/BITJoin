import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import datatypes.Pair;
import utils.TimeCal;

public class myMain {

	public static void main(String[] args) throws FileNotFoundException {
		int ch = 0;
		// 0 IEJoin
		// 1 Naive approach
		// 2 Cache efficient approach
		// 3 cache efficient incremental approach

//		String inputFileName=args[0];
//		int arraySize =Integer.parseInt(args[2]);

		//Input path
		String inputFileName = "testDataSets/inputDB_349525_1_sh.csv";

		//must put dataset size before hand
		// 400000
		int arraySize = 100;

		if (args.length == 1) {
			ch = Integer.parseInt(args[0]);
		} else if (args.length == 3) {
			ch = Integer.parseInt(args[0]);
			inputFileName = args[1];
			arraySize = Integer.parseInt(args[2]);
		}

		// 读入原始文件并存入数组
		Scanner sc = new Scanner(new File(inputFileName));
		// Remove first line：attribute
		sc.nextLine();

		int[][] array1 = new int[arraySize][2];

		for (int i = 0; i < arraySize; i++) {
			String[] inSt = sc.nextLine().split(",");
			array1[i][0] = Integer.parseInt(inSt[1]);
			array1[i][1] = Integer.parseInt(inSt[2]);

		}
		sc.close();

		System.out.println("*** BITJoin ***");
		long aTime = System.currentTimeMillis();
		Pair<ArrayList<Integer>, ArrayList<Integer>> resultForBit = new BITJoin()
				.join(arraySize, array1);
		long bTime = System.currentTimeMillis();
		System.out.println("time = " + (bTime - aTime));
		System.out.println("result size = " + resultForBit.getFirst().size() ) ;


//		System.out.println("*** IEJoin ***");
//		long aTime = System.currentTimeMillis();
//		Pair<ArrayList<Integer>, ArrayList<Integer>> result = new bitArray()
//				.join(arraySize, array1);
//		long bTime = System.currentTimeMillis();
//		System.out.println("time = " + (bTime - aTime));
//		System.out.println("result size = " + result.getFirst().size());
//
//
//
//		System.out.println("Get Time:" + TimeCal.time1);
//		System.out.println("Add Time:" + TimeCal.time2);




	}
}
