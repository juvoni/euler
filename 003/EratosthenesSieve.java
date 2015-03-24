import java.util.Arrays;
//CREDIT: SO user @rolfl
//http://codereview.stackexchange.com/questions/38084/suggestions-for-improvement-on-project-euler-7/38095#38095
public class EratosthenesSieve {
    // Since we play by inverting the logic of a boolean array,
    // I have constants that make the logic easier to see.
    private static final boolean PRIME = false;
    private static final boolean NOTPRIME = true;

    // Initialize to the first 5 primes.
    // Note, by seeding at least one odd prime, we never have to consider even
    // values in the sieve.
    private int[] primes = { 2, 3, 5, 7, 11 }; // seed initial primes.

    /**
     * Get the n<sup>th</sup> prime
     *
     * @param nth
     *            The prime to get.
     * @return the n'th prime.
     */
    public int getNthPrime(final int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException("Finding the " + nth
                    + " prime number is not possible");
        }

        while (nth >= primes.length) {
            // have not yet cached this prime.

            // NOTE: Integer.MAX_VALUE happens to be prime.
            // Maybe there is something we can do with that.

            // The estimated n'th prime: from Wikipedia:
            // http://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
            final int lastprime = primes[primes.length - 1];
            // calculate forward to 25% of where we are, or 10% + 30 more than
            // the wiki guess.
            final long longapproxsize = Math.max((long) lastprime
                    + ((long) lastprime >> 2),
                    30 + (long) (nth * Math.log(nth) * 1.1));
            if (longapproxsize > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("The " + nth
                        + " prime number is probably too large to calculate.");
            }
            // calculate at most 1Million primes in a loop (sieve size 1M)
            // (reduces maximum memory footprint);
            final int approxsize = Math.min((int) longapproxsize, lastprime + 1000000);
            // only need to create a sieve for what we have not yet calculated.
            // odd number after last prime.
            final int startfrom = lastprime + 2;

            final boolean[] sieve = new boolean[approxsize - startfrom];
            for (final int prime : primes) {
                // mark multiples of previously-calculated primes.
                if (prime == 2) {
                    // can ignore 2...
                    continue;
                }
                // calculating an odd-valued start position allows us to
                // optimize
                // by doing double-prime steps.
                int first = (prime - (startfrom % prime)) % prime;
                if (((first + startfrom) & 1) == 0) {
                    first += prime;
                }
                for (int j = first; j < sieve.length; j += prime + prime) {
                    sieve[j] = NOTPRIME;
                }
            }

            // OK, the sieve has been extended. But only the primes from the
            // previous round have been filtered...
            // our last prime was recorded in primes array.
            // we need to complete the prime sequence, then extract the new
            // primes.
            int pcnt = primes.length;
            int[] tmprime = Arrays.copyOf(primes, approxsize >> 1);
            int root = (int) (Math.sqrt(approxsize) + 1);
            int maxj = (int)(Math.sqrt(root) + 1);
            for (int i = 0; i < sieve.length; i += 2) {
                if (sieve[i] == PRIME) {
                    // this is a newly discovered prime.
                    // record it
                    int prime = i + startfrom;
                    tmprime[pcnt++] = prime;
                    // clear any future multiples of this prime.
                    // optimize by starting from square, and ignoring even
                    // multiples.
                    // need j > 0 to avoid overflow issues.
                    if (prime > maxj) {
                        continue;
                    }
                    for (int j = prime * prime; j > 0 && j < root; j += prime + prime) {
                        if (j - startfrom < 0 || j - startfrom >= sieve.length) {
                            throw new ArrayIndexOutOfBoundsException(String.format("Cannot access index %d in array with length %d", j - startfrom, sieve.length));
                        }
                        sieve[j - startfrom] = NOTPRIME;
                    }
                }
            }
            // OK, we have our new primes recorded.
            primes = Arrays.copyOf(tmprime, pcnt);

        }
        return primes[nth - 1];
    }
}
