# game-of-life

This program was my honors project for a course covering Object Oriented Programming.

It simulates Conway's Game of Life and is written in Java using the Swing framework for the GUI.

## Class Breakdown

### Conway

The main class which creates a JFrame displaying an instance of...

### LifeGUI

Exactly what it says on the tin. Handles the graphic aspect of the program and manages an instance of...

### Life

Implements the game logic. The rules are quite simple. A grid of cells steps evolves under the following conditions:

* A live cell with less than two live neighbors dies.
* A live cell with two or three live neighbors stays alive.
* A dead cell with three live neighbors comes to life.
* A live cell with more than three live neighbors dies.

More information can be found on its [Wikipedia page](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

## Status

Stable, if not optimized. While the program has no performance issues, it is a naïve implementation consisting solely of a two-dimensional which is fully copied to calculate the next generation.

### Possible Updates


* Reduce memory usage by using line buffers rather than an entirely new array.
* Implement tracking of dead zones (i.e. skip cells guaranteed not to come to life).
* Implement more sophisticated algorithm. [Hashlife](https://en.wikipedia.org/wiki/Hashlife)? Perhaps as a challenge.
