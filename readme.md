# game-of-life

This program was my honors project for a course covering Object Oriented Programming.

It simulates Conway's Game of Life and is written in Java using the Swing framework for the GUI.

<h2>Class Breakdown</h2>

<h3>Conway</h3>

The main class which creates a JFrame displaying an instance of...

<h3>LifeGUI</h3>

Exactly what it says on the tin. Handles the graphic aspect of the program and manages an instance of...

<h3>Life</h3>

Implements the game logic. The rules are quite simple. A grid of cells steps evolves under the following conditions:

<ul>
<li>A live cell with less than two live neighbors dies.</li>
<li>A live cell with two or three live neighbors stays alive.</li>
<li>A dead cell with three live neighbors comes to life.</li>
<li>A live cell with more than three live neighbors dies.</li>
</ul>

<h2>Status</h2>

Stable, if not optimized. While the program has no performance issues, it is a naïve implementation consisting solely of a two-dimensional which is fully copied to calculate the next generation.

<h3>Possible Updates</h3>

<ul>
<li>Reduce memory usage by using line buffers rather than an entirely new array.</li>
<li>Implement tracking of dead zones (i.e. skip cells guaranteed not to come to life).</li>
<li>Implement more sophisticated algorithm. Hashlife? Perhaps as a challenge.</li>
</ul>
