The Problem Solving Framework : 'UPER'
U = "Understand"
P = "Plan"
E = "Execute"
R = "Reflect" / "Refactor"
1. Understanding the Problem
* * * * * * * *
BLOCKERS- 
1. I don’t know how to connect Java to a front end
2. Does each instance of Piece need an unique name? If so, can I still dynamically create them?
3. Would a piece have a square or vise versa or both? If the answer is both (as I think) would that violate the Dependency Inversion Principle?
4. Where/how should I instantiate pieces(enums, in Game, or in Board)
5. How do I communicate between the board and the pieces
6. How to create/traverse multi-dimensional array for board
7. How to calculate path of Knight
8. How to communicate if captured(how to kill opponent piece)
9. How to keep King from moving into check
10. How to declare checkmate
11. How to castle
12. How to undo
13. I don't know how to use a game library
14. Add two design patterns
15. Apply SOLID principles



2. Planning the Solution
* * * * * * * *
1. There is a ton of logic involved in a chess game so I will concentrate on that and not worry about a front end until I am finished with the game, aka I probably won't waste my time learning how to use a game library I probably won't use again. So I am skipping 1, 12, and 13

2. My research shows that instances do not need unique variable names, it will introduce some complication for selecting the piece but I was able to research how to dynamically create pieces. I refactored multiple times as I tried to decide the best way to implement Pieces. My 2nd idea, and the one I liked best, was to make every piece a enum but I was unable to do so since Piece was an abstract class. I ended up initializing it in Board.

3. and 5. On reflection, these 2 are related. I am still unsure if it is bad practice for them to be doubly composite linked. But as of right now, I am going to let the board control the pieces and the pieces won't know its location. this is the easiest way initially although I think it will limit me later (like with avoiding check). Edit- Craig informed me that my notion of having double composition being bad design was correct.  I ended up deciding on the board having pieces which was the easiest way to establish each piece's move logic but checkmate would have been much easier to perform if I had chosen vise versa. I did give King a location as that let me avoid looping through the whole board twice for Check and Checkmate.

4. I initially thought the best way was in Enums but I was unable to do this with the super class of Pieces because it was abstract. Right now I am planning to initialize board and pieces simultaneously. Edit- yes I instantiated all Pieces in board simultaneosly with the instantiation of all 64 squares.

6. is related to 2. I dynamically created nested arrays much like I created multiple instances of the same piece

7. The knight didn't end up being a blocker like I thought. In fact, it was the easiest Piece to implement since I didin't have to check whether or not the path is blocked. Originally, I planned for each piece to have a list of all possible moves but I decided to simplify and insted validate whether the chosen move was valid which eliminated this blocker.

8. I should be able to do this from Game.java I will set a type of the opposing team to null. I might want to refactor my team to a boolean to make it easier to switch teams. Edit- I did indeed refactor to a boolean. I felt that Single Responsibility dictated that I should get the opposite team within the Team class but I instead did so through the Game class (which controlled gameflow) which was easy.

9. I will have to loop through EVERY enemy piece and check to see if it can reach the King's desired destination. It will be a pain in the ass but ideally I could make it more efficient by eliminating Pawns and Kings depending on their position. Edit- To avoid a double dependency, I eliminated the piece's position so instead I have to loop through the entire board, check to see if it has a piece, and if so whether that square's location to the King was a valid move (aka I used the existing isValidMove for Piece and just applied it differently)

10. This will be difficult because not only do I need to check if the King can move to an unpressured square, I will also need to see check if it's own pieces will be able to block the pressure. So hypothetically I will have to check EVERY piece. This will be difficult and something I will leave towards the end. Edit- This was the hardest blocker of all the actual logic (aka everything but SOLID principles) but I eventually got it done.

11. It shouldn't be too difficult to see if the pieces in between the King and Rook are unoccupied but it will be hard to check whether or not the King or Rook had previously moved. I was planning on having a list of moves to display so I will be able to use that to loop through it and make sure neither piece has moved yet

12. Can use a memento, will have to learn this design pattern

13. Will probably avoid these blockers and instead print board to terminal

14. Singleton proved easy to implement, Memento has been more difficult

15. This has been by far the biggest blocker that took up the majority of my time. I refactored my code many, many times trying to achieve a design that 100% followed the SOLID principles and I still didn't quite get there.

Chess Game Outline
******************************************************************************************
Game - Main Class: encapsulates all other classes, 
instantiates 16 white and 16 black pieces, 2 players, and board

Players - 2 players take turns. 1 = white, 2=black, name property

Board Class - renders board.
8x8 squares each with a unique ID. letter for row and number for column ie A1
Instantiate pieces from the board, each takes up a set square

Square class - 
Square inherits from board, probably using composition
Board HAS A piece
Can only have 1 piece. So 1 property 
Extra- Alternating Color


Abstract Piece Class - blueprint for pieces/ maybe broken up into white/black
String Color
Square Position
select() select specific piece 
move() changes position
moves() logic for the position the piece IS ABLE to move(arraylist?)

King
Queen 
Rook 2
Knight 2
Bishop 2
Pawn 8
Do I want to split these classes based on color or have color as a characteristic?

Can’t move off the board
Can’t move past enemy piece
If lands on piece that enemy piece resides on, piece becomes null
Can’t move on or past teammate, exception for Knight
Stream- either a list of possible moves or past moves (also use lambda)

Extras- list of possible moves displayed to user/ either by text or highlight(clickable)
 operates on touch as well as text



3. Executing the Plan
* * * * * * * *
After refactoring back and forth several times, I have decided that a piece will not have access to its location. This seems to be the most correct way to structure my code although it would make some things easier.

I will have to add a method to check to see if it can check the King to account for the King not being able to move into check in addition to checkmate. Also, I had a method move that I ended up not using. At the moment I have a class Move that can track everyones move's to use for streaming and lambda's but I am not sure that is the best way. Actually I know that the best way would be to just make it a String and add to an array but I need to use a lambda and interface somewhere and I have yet to find a better use for either.

I am going to change all the names in black to be opposite case so that they stand out more.

BLOCKER-------------------------------
After I set up my chess game, I start a simple loop 
while (active) {
            selectPiece(player1, gameboard);
            gameboard.showBoard();
            selectPiece(player2, gameboard);
            gameboard.showBoard();
        }
This initially works, but after 5-10 moves(3 to 5 loops) in, it gets wonky and will start repeating (or skipping) turns, sometimes player1 or sometimes player2. It isn't tied to any specific event, and so I littered my code with System.logs and everything seems to work exactly as it should until it randomly reads a phantom input(always after the function has been completed) which leads it to believe that a turn hasn't been made, even though the board has already been updated with the desired move. The phantom input always seems to be one of the "extra" options like printing the board 888. Because of this I have split up my my selectPiece and movePiece functions. They were too large anyways. As of right now, the user has to type in an extra number to navigate from the pre-select and pre-move functions into the originals which is not a good user experience. Instead I will pass in action as a parameter so that the extras and the moves can be done at once, but hopefully without the bugs.

----------------------------------Blocker

Checks
Memento Undo a move
Netlify instructions
Add 1 to Board


4. Reflection / Refactor
* * * * * * * *
I should have started with public as a regular class until I figured out the best way to factor my code because I caused myself a lot of unneccessary work refactoring each class as I tried out the different ways to use the children of Piece while deciding on the best practice.

I am refactoring my Player class to change the team name to a boolean for easier switching. Plus, I am bringing over some methods from Game.java that I think will be better suited to player, plus it will help break up my giant Game class

-----------------------------------------------SOLID PRINCIPLES --------------------------------------------

1. I have spent a lot of time contemplating the Single Responsibility Principle as it relates to this assignment (and also burger). This principle is very easy to implement but very tough to implement it 100%. My Game.java(entry point) breaks the Single Responsibilty but I am unsure of how to do it better. I would love to see a demonstration of either this assignment, or the burger assignment that completely follows the Single Responsibility Principle.

2. Piece is open for extension and closed for modification which demonstrates the Open Closed Principle. All subclasses of piece inherits (extends) its behavior and adds unique behavior without modifying Piece itself. Any unique properties of a piece are isolated to that piece such as King having a location

3. The children are substituted seemlessly for the parent Piece which is the Liskov Substitution Principle. My game would not work otherwise because the vast majority of my calls to the children of Piece are actually to Piece itself even though Piece is an abstract class and cannot actually perform any of the methods I call to it. The only times I do not call Piece are for special rules such as Pawn Promotion or Castling which require a specific subclass.

4. The abstraction interfaces are implemented 100%. They are fine grained interface that are client specific with a well-defined purpose and only exposes behavior that is aligned with its purpose. 

5. I attempted to make my program depend on abstractions instead of concretions and I did so with all the abstractions that I used. But did I follow this rule 100%? My Piece class did but I had other multiple other classes that had no need for extension such as square. There are 64 instances of square that made up the board (via composition) but all had identical behavior so I did not make an abstract class or interface for it. Thus, any interaction I had with square depended on the concrete Square class. I hvng believe this violates the Dependency Inversion Principle although I do not see how this would benefit my program.

In recap, I applied Principles 2-4 throughout the project and I did my best to use the Single Responsibilty and Dependency Inversion Principles but upon reflection, I wasn't able to understand them well enough to fully implement.

Craig was able to greatly increase my understanding of proper code practices but with my inexperience, completely achieving best practices seems like attempting to travel at the speed of light. I can always do better but I can never quite reach it. I spent at least 75% of my time on this project refactoring my code.

I either spent way too much time on unicode or not nearly enough time.

