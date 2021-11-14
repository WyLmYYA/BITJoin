package datatypes;

public class Data<T extends Comparable<T>> implements Comparable<T> {

	private int row;
	private T value;
	private T rank;
	// 标记改数据来自源数据还是增量数据
	// false为原数据 true增量
	private boolean flag;

	public Data(int row, T value, T rank,boolean flag) {
		super();
		this.row = row;
		this.value = value;
		this.rank = rank;
		this.flag=flag;
	}

	public int getRow() {
		return row;
	}

	public T getValue() {
		return value;
	}

	public T getRank() {
		return rank;
	}

	public boolean getFlag(){
		return flag;
	}

	public int compareTo(Data<T> d) {
		return value.compareTo(d.getValue());
	}

	public int compareRank(Comparable<?> in) {
		return rank.compareTo((T) in);
	}



	@Override
	public int compareTo(T o) {
		return value.compareTo(o);
	}

	public String toString() {
		return value.toString()+"-"+rank.toString();
	}

}
