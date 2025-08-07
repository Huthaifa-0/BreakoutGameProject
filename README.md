# Java Breakout Game

This is a classic Breakout-style arcade game implemented in Java. Players control a paddle to bounce a ball and destroy bricks. The game features sound effects, a scoring system, and a main menu.

## Features

- Classic brick-breaking gameplay
- Paddle and ball physics
- Wall collision handling
- Dynamic brick generation and removal
- Score tracking
- Sound effects
- Simple main menu interface

## Project Structure

- `BreakoutGame.java`: Main game class, initializes the game loop and handles input.
- `Ball.java`: Manages the behavior and physics of the ball.
- `Paddle.java`: Controls paddle movement and collision.
- `Brick.java`: Represents individual bricks.
- `BrickManager.java`: Generates and manages all brick objects.
- `Wall.java`: Defines the game area boundaries.
- `Score.java`: Handles scoring logic and display.
- `Menu.java`: Manages the game’s main menu interface.
- `Audio.java`: Plays sound effects on events like paddle hits and brick destruction.

## How to Run

1. Make sure you have Java installed (JDK 8 or higher).
Steps to Compile and Run
Open Terminal / Command Prompt

2. Navigate to the project folder (where your .java files are):

   cd /path/to/your/project

3. Compile all Java files:
   
    javac *.java

5. Run the main class:
   
    java BreakoutGame

### Controls
Left Arrow / A: Move paddle left

Right Arrow / D: Move paddle right

Spacebar: Launch ball / start game

### Dependencies 
Macalester kilt graphics library 

### Future Improvements
Add power-ups and levels

Improve UI and animations

Add game over and win screens

Implement mouse controls

#### Author
Created by Huzaifa Mohammad
Macalester College - Computer Science Coursework Project

