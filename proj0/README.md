Hi there 👋

This project is to implement the popular game "2048", a single-player computer game written by Gabriele Cirulli, 
and based on an earlier game “1024” by Veewo Studio ([see his on-line version of 2048](https://play2048.co/)). 

The game itself is quite simple. It’s played on a 4×4 grid of squares, each of which can either be empty or 
contain a tile bearing an integer–a power of 2 greater than or equal to 2. Before the first move, the application 
adds a tile containing either 2 or 4 to a random square on the initially empty board. The choice of 2 or 4 is random, 
with a 75% chance of choosing 2 and a 25% chance of choosing 4.

The player then chooses a direction via their arrow keys to tilt the board: north, south, east, or west. 
All tiles slide in that direction until there is no empty space left in the direction of motion (there might not be 
any to start with). A tile can possibly merge with another tile which earns the player points.

The below GIF is an example to see what the result of a few moves looks like.

![example-2048](https://user-images.githubusercontent.com/88932816/211224036-7cee85f3-48c1-4a79-827a-00ee38c2eb89.gif)



The main codes of the game logic is implemented in `game2048/Model.java`. 

To play the game that I developed, download all the files in this folder, run the `Main` class, 
then you should see the game pop up. Have fun! 🙌
