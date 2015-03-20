public class multiple_3_5{

	public static void find(int belowSum){
		int i = 0, sum = 0;
		while( i < belowSum){
			if((i % 3 == 0 || i % 5 == 0)) sum+=i;
			i++;
		}
		System.out.println(sum);
	}
	public static void main(String[] args) {
		multiple_3_5.find(1000);
	}
}