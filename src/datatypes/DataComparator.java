package datatypes;

import java.util.Comparator;

public class DataComparator implements Comparator<Data<Integer>> {

	boolean asc1;
	boolean asc2;

	public DataComparator(boolean asc1, boolean asc2) {
		this.asc1 = asc1;
		this.asc2 = asc2;
	}

	public int compare(Data<Integer> o1, Data<Integer> o2) {

		if (asc1) {
			int dff = o1.getValue().compareTo(o2.getValue());
			if (dff == 0) {//如果被比较的两个对象相等
				//System.out.println(o1.getValue()+"-"+o1.getRank()+":::"+o2.getValue()+"-"+o2.getRank());
				if (asc2) {
					// System.out.println(o1.compareRank(o2.getRank()));
					return o1.compareRank(o2.getRank());
				} else {
					// System.out.println(o2.compareRank(o1.getRank()));
					return o2.compareRank(o1.getRank());
				}
			}
			return dff;

		} else {
			int dff = o2.getValue().compareTo(o1.getValue());
			if (dff == 0) {
				//System.out.println(o1.getValue()+"-"+o1.getRank()+":::"+o2.getValue()+"-"+o2.getRank());
				if (asc2) {
					// System.out.println(o1.compareRank(o2.getRank()));
					return o1.compareRank(o2.getRank());
				} else {
					//System.out.println(o2.compareRank(o1.getRank()));
					return o2.compareRank(o1.getRank());
				}
			}
			return dff;

		}

	}

}
