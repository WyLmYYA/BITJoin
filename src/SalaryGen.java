import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class SalaryGen {

	private static int numberOfRows = 349525;
	private static int iterations = 1;
	private static int delta = 100;
	private static int range = 10;
	private static String folder = ".";
	private static int depatments = 5;
	private static String head = "Name(String),Dept(Integer),Salary(Integer),Tax(Integer)";

	private static String name[] = {"Smith",
									"Johnson",
									"Williams",
									"Jones",
									"Brown",
									"Davis",
									"Miller",
									"Wilson",
									"Moore",
									"Taylor",
									"Anderson",
									"Thomas",
									"Jackson",
									"White",
									"Harris",
									"Martin",
									"Thompson",
									"Garcia",
									"Martinez",
									"Robinson",
									};


	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Start");
		long start, end;

		while(iterations-- > 0 ){
			System.out.print("\t-> generating dataset with " + (numberOfRows/1000000 ) + " million rows");
			start = System.currentTimeMillis();
			genGround();
			genInput();
			end = System.currentTimeMillis();
			System.out.println(" [ " + (end - start) + " ms ]");
			numberOfRows += delta; 
		}

	}


	private static void genInput() throws FileNotFoundException {
		int errLimit = 1;
		PrintWriter pw = new PrintWriter(folder + "/inputDB_" + numberOfRows + "_"+errLimit+".csv");
		pw.println(head);

		int salary = 100;
		int row = 0;
		Random r = new Random();
		while(row++ < numberOfRows){
			int n = r.nextInt(name.length);
			pw.print(name[n] + ",");
			n = r.nextInt(depatments);
			pw.print(n + ",");
			pw.print(salary + ",");
			salary += 100;
			int error = r.nextInt(100);
			if(error <= errLimit)
				pw.println((salary / 100) - (range + 1));
			else
				pw.println(salary / 100);
		}

		pw.close();
	}


	private static void genGround() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(folder + "/groundDB_" + numberOfRows + ".csv");
		pw.println(head);

		int salary = 100;
		int row = 0;
		Random r = new Random();
		while(row++ < numberOfRows){
			int n = r.nextInt(name.length);
			pw.print(name[n] + ",");
			n = r.nextInt(depatments);
			pw.print(n + ",");
			pw.print(salary + ",");
			salary += 100;
			pw.println(salary / 100);
		}

		pw.close();
	}

 
}