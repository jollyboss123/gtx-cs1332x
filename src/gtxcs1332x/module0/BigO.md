## Big O

- the mathematical notation that describes the limiting behavior of a function
- belongs to a family of asymptotic notation
- for computer science, it is an approach used to analyze the efficiency of algorithms and data structures
  - this measure needs to be able to account for both time and space efficiency
  - independent of hardware and software environments
- the measure will tell how well the algorithm scales with the size of the inputs
  - we are quantifying the time complexity of the algorithm as a function of the amount of input

### Primitive Operations
- low level instructions that will execute in little time
- so it is said to execute in constant time
- e.g.
  - assign value
  - arithmetic operation
  - comparing 2 entities
  - method call or return from method
  - access element or follow a reference
- assumption that these operations can be done quickly is only valid if we are working with manageable, small units
  - e.g. if we are multiplying 2 integers, if the numbers are small enough to fit into an `int` (32-bit) or a `long` (64-bit) typing, then the multiplication will be fast since Java optimizes computations with primitive types
  - if the numbers are very long, then it may pose a significant computation task since there is no longer a simple low-level instruction that can do it for us

### Measuring efficiency as a function
- *f(n)* represents a function of primitive operations on an input of size *n*
- there are 3 cases:
  - **worst case**:
    - the algorithm running with the worst set of data, worst performance
  - **best case**:
    - the algorithm running with the best designed set of data, fastest performance
  - **average case**:
    - somewhere between, difficult to compute
- we want the worst case analysis
- *O(f(n))* to represent the most accurate upper bound

### Common complexities
| Complexity | Big-O Notation   |
|------------|------------------|
| constant | O(1)             |
| logarithmic | O(log(n)         |
| linear | O(n)             |
| log-linear | O(nlog(n))       |
| quadratic | O(n<sup>2</sup>) |
| cubic | O(n<sup>3</sup>) |
| exponential | O(a<sup>n</sup>), a constant |

### Program run duration vs time complexity
| Algorithm's operation complexity | Program elapsed time on a processor with clock speed 100 Hz when n = 1000 | Program elapsed time on a processor with clock speed 10 GHz when n = 1 million |
|--------------------|----------------|--------------|
| 1 | 10 milliseconds | 0.1 nanoseconds |
| log(n) | 99.66 milliseconds | 2 nanoseconds |
| n | 10 seconds | 0.1 milliseconds |
| nlog(n) | 1-2 minutes | 2 milliseconds |
| n<sup>2</sup> | 2-3 hours | 1-2 minutes |
| n<sup>3</sup> | 115 days | over 3 years |
| 2<sup>n</sup> | heat death of the universe | heat death of the universe |

- we can see that even for *n = 1000*, the program becomes intractable after the time complexity is *n<sup>2</sup>* or more
- the 2nd example is more reasonable by today's standards
- at the time of writing, the fastest computer's processor has a clock speed slower than 10 GHz (about billion operations per second) 
- *n = 1 million* may seem large, but if we equate 1 input as 1 byte, then this size of *n* is about 1 MB
- large difference between *n<sup>3</sup>* and *2<sup>n</sup>*
  - the heat death of the universe is roughly *10<sup>100</sup>* away
  - even on a processor with clock speed 10 GHz, if *n >= 400*, the heat death of the universe will come before the program terminates
  - if we want our program to finish before a day passes, then *n <= 50* is needed
- the difference between polynomial and exponential time is the highlight of the famous *P != NP* problem in computer science
