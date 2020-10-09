# Set Cover Problem

Program should read data from standard input. The data is a sequence of integers.
Data denoted in BNF notation:
> Dane ::= { Zbiór | Zapytanie }
> Zbiór ::= { Składnik } 0
> Składnik ::= Element | Nieskończony | Skończony
> Element ::= d
> Nieskończony ::= d u
> Skończony ::= d u u
> Zapytanie ::= u d

![eq1](https://latex.codecogs.com/gif.latex?d&space;\in&space;\mathbb{N}\setminus&space;\{0\}&space;\\&space;but&space;\&space;d&space;\&space;in&space;\&space;Zapytanie&space;\&space;\in&space;\{1,2,3\}\\&space;u&space;\in&space;\mathbb{Z}_{-}\\&space;Zbior&space;\&space;(set)&space;\&space;is&space;\&space;a&space;\&space;sum&space;\&space;of&space;\&space;subsets:\\&space;*&space;a&space;\&space;-the&space;\&space;subset&space;\&space;is&space;\&space;\{a\}\\&space;*&space;a&space;\&space;b&space;\&space;-the&space;\&space;subset&space;\&space;is&space;\&space;\{x|x&space;=&space;a&space;-b*k&space;\&space;for\&space;k\in\mathbb{N}\}\\&space;*&space;a&space;\&space;b&space;\&space;c&space;\&space;-the&space;\&space;subset&space;\&space;is&space;\&space;\{x|x&space;=&space;a&space;-b*k&space;\&space;for\&space;k\in\mathbb{N}\&space;\wedge&space;x&space;\leq&space;-c&space;\}\\)

Consecutive sets are enumerated and added to create a bigger set.
Zbiór describes a set, and Zapytanie is an inquiry.
Inquiry consist of elements `a` `b`:
* `b` describes the way of solving the problem
	* 3
		* naive heuristic – iterates through sets and picks one up if there exists yet non-covered element
	* 2
		* greedy heuristic – picks up sets with the most elements not covered yet
	* 1
		* precise algorithm – brute force / checks all possible covers
* `a` describes a set to be covered:
	* ![eq2](https://latex.codecogs.com/gif.latex?\{x\in\mathbb{N}|1\leq&space;x&space;\leq-a&space;\})

### Example

*Input
```
2 0

   1      0 1
 
0 -3
1
0
2 3 2 0
-3 3
-3 2
-3 1
4 -1 -5 1000000000 0
 2 -2 0
-6 3
-6 2
-6 1
1 6 0
-6 3
-6 2
-6 1
```
*Output
```
0
1 2 5
2 5
2 5
1 2 5 6 7
2 5 6 7
2 5 6 7
1 2 5 6 7
2 5 6 7
5 6 8
```
### Disclaimer

I do not own the idea for that project. I am not the author. This is merely a rough translation and a summary.
All credit goes to University of Warsaw, Faculty of Mathematics, Informatics and Mechanics.