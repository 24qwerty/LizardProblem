# Lizard Problem
Lizard Problem
Problem Description:
● Given a nxn board with given position of trees place m lizards on the board such that they do not attack each other.
● Lizards can attack each other similar to how queen can attack in the game of Chess.But a tree can obstruct the attack of the lizards.
● Given time constraints of 300 seconds find valid placement of all lizards.

Input Description:
Line 1:Algorithm to be used amongst Breadth First Search(BFS),Depth First Search(DFS) and Simulated Annealing(SA).
Line 2:n(Width and height of the board).
Line 3:m(Number of lizards to be placed).
Next n lines:nxn board.One file line per row of board.0’s for empty spot and 2’s for trees.

Output Description:
Line 1:”OK” if lizard placement is possible otherwise “FAIL”.
Next n lines:nxn board.One file line per row of board.0’s for empty spot and 2’s for trees and   1’s   for   lizards.
Sample input(input.txt) and output (output.txt) provided. 
1. Tested the algorithms on more than 50 different test cases and received 100% result.
2. Optimised the code to run on larger and complex boards in 300 seconds.
