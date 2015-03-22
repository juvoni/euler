public class even_fib{
	public static long sum(int n){
		long[] fib = new long[n+1];
		fib[0] = 0;
		fib[1] = 1;
		long sum = 0;
		for(int i = 2; i<=n;i++){
			fib[i] = fib[i-1]+fib[i-2];
			if(fib[i] % 2 == 0) sum+= fib[i];
		}
		return sum;
	}
	public static void main(String[] args) {
		int limit = 33; // Fibonnaci value should not exceed 4million. fib(33) is max value below that limit.
		System.out.println(sum(limit));
	}
}