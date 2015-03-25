>The purpose of these reviews are to take note of my thought process in approaching the problem and solving it. Then my remarks after reviewing how other people approached it and what the optimal solution was. 

###Background
>"Prime Factorization" is finding which prime numbers multiply together to make the original number.

This problem took me some time to wrap my mind around as I was unfamilar with a lot of the concepts. 

I first had to look into what prime factorization was. After I got around the basics of that I saw that I could use a factor tree to determine the prime factors. All the factors are prime and with visualizing a tree all left childs of the root which is the initial number are the lowest prime factor of the divisible number. When you reach the highest depth of the tree or the lowest level, both the left and right child are part of the factor. 

I didn't have to build out a tree data structure. I simply used a Arraylist to hold the left factors and when it reached a right value that was prime it would end the loop and add it to the factor list. 

Another part was knowing how to determine and prime number as well as efficiently getting the next prime number. 