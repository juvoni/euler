import java.util.ArrayList;

public class prime_factor{
	public static EratosthenesSieve sieve = new EratosthenesSieve();

	public static ArrayList<Integer> find(long num){
		ArrayList<Integer> factors = new ArrayList<Integer>();
		int left = 2;
		long right = num;
		if(isPrime(right)){
			factors.add((int)right);
			return factors;
		}
		while(!isPrime(right)){
			if(right % left == 0){
				right = right / left;
				factors.add(left);
			}else{
				left = getNextPrime(left);
			}
		}
		factors.add((int)right);

		return factors;
	}

	public static int getNextPrime(int n){
		int i = 1;
		while(sieve.getNthPrime(i) < n || sieve.getNthPrime(i) == n){
			i++;
		}
		return sieve.getNthPrime(i);
	}

	public static boolean isPrime(long n) {
	    if(n > 2 && (n & 1) == 0)
	       return false;
	    for(int i = 3; i * i <= n; i += 2)
	        if (n % i == 0)
	            return false;
	    return true;
	}
	public static void main(String[] args) {
		long value = 600851475143L;
		ArrayList<Integer> result = prime_factor.find(value);
		System.out.println(result.toString());
		System.out.printf("Largest Factor: %d\n",result.get(result.size()-1));
	}
}