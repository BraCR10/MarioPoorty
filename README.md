# Mario Poorty  
This game was developed as project 2 of the object oriented programming course at the Instituto Tecnológico de Costa Rica (TEC).

Welcome to **Mario Poorty**, a multiplayer game inspired by the fun and excitement of Mario Party! 🎉  
This project brings together players in a thrilling board game experience complete with mini-games, challenges, and a chat feature.  

---

## 🛠 Features  

- **Multiplayer Gameplay**:  
  - Supports 2 to 6 players in a single match.  
  - Login system to choose from multiple unique characters.  
- **Dynamic Board Game**:  
  - A randomized board layout with interactive tiles.  
  - Roll dice to move and land on tiles that trigger mini-games, punishments, rewards, or special events like:  
    - **Flower Tiles**: Gain bonuses.  
    - **Ice Tiles**: Lose a turn or resources.  
    - **Other elements**
    - **Jail Tiles**: Skip your next turn.  
- **Mini-Games**:  
  - Play from a selection of 9 unique mini-games, adding variety and competition.  
- **Winning Objective**:  
  - Complete one full lap around the board and land exactly on the starting point to win!  
- **Real-Time Chat**:  
  - Built-in chat system for player communication using broadcast messages.  

---

## 🚀 Technologies Used  

- **JSwing**: Provides a graphical user interface for the board, dice, mini-games, and more.  
- **Threads**: Handles concurrent gameplay elements like dice rolls, player moves, and mini-game logic.  
- **Sockets**:  
  - Facilitates client-server connections for multiplayer functionality.  
  - Broadcast system for real-time chat between players.  

---


## 🎮 How to Play  

1. **Launch the Game**  
   - Start the server application to host the game.  
   - Players connect to the server by launching the client application.  

2. **Login**  
   - Select a unique character and join the match.  

3. **Game Mechanics**  
   - Players take turns rolling the dice to move across the board.  
   - Interact with tiles for mini-games, rewards, or penalties.  
   - Use strategic thinking to maximize your chances of winning.  

4. **Victory Condition**  
   - Be the first to complete one lap around the board and land exactly on the starting point.  

---

## 💻 Running the Game  

### Prerequisites  
- Java Development Kit (JDK) 8 or higher.  
- A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans).  

### Steps  
1. **Start the Server**  
   - Navigate to the `src` folder and compile the server code:  
     ```bash
     javac src/main/java/MainGame/GameServer.java 
     java -cp out MainGame.GameServer 
     ```  
     or using a java IDLE
2. **Run the Client**  
   - Compile and start the client application:  
     ```bash
     javac src/main/java/MainGame/Player.java
     java -cp out MainGame.Player  
     ```
     or using a java IDLE

3. **Select a character and number to play**  
   

