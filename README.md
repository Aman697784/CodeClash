# ⚔️ Code Clash — Online Quiz Battle

> A real-time multiplayer coding and quiz battle Android application built with Kotlin, XML, Firebase Authentication, and Firebase Realtime Database.

**Developer:** AMAN RAJ  
**Platform:** Android  
**Project Type:** Academic / Personal Android Project  
**Development Status:** In Development

---

## 📌 Project Overview

**Code Clash** is an online multiplayer quiz-battle application designed to make programming and technical learning more competitive and engaging.

Players can enter the game, create a private room, share a room code, and allow another player to join. Once the match starts, players answer timed questions and compete based on their scores.

The project is designed to demonstrate Android development, Firebase integration, real-time database communication, authentication, timers, multiplayer game logic, and a competitive user experience.

---

## 🎯 Main Objectives

- Create an interactive online quiz battle.
- Allow players to create and join rooms.
- Generate a unique room code for multiplayer matches.
- Use Firebase for authentication and real-time game data.
- Display questions with multiple-choice answers.
- Add a countdown timer.
- Track player scores in real time.
- Display the final result after the match.
- Provide a foundation for leaderboards, XP, achievements, and player statistics.

---

## 🎮 Planned Game Flow

```text
Open Code Clash
       ↓
Guest / Anonymous Login
       ↓
        Home
       ↓
 ┌───────────────┐
 │               │
Create Room   Join Room
 │               │
 ↓               ↓
Generate       Enter Room Code
Room Code          ↓
 │              Verify Room
 └───────┬───────┘
         ↓
     Waiting Room
         ↓
      Start Game
         ↓
    Quiz Battle
         ↓
 Questions + Options
         ↓
      Timer
         ↓
    Live Scoring
         ↓
       Result
         ↓
Leaderboard / XP / Achievements
```

---

## ✨ Main Features

### 🔐 Anonymous Login
Players can enter the game without creating a traditional account.

Firebase Anonymous Authentication creates a temporary authenticated user that can be identified using a unique UID.

### 🎮 Create Room
A player can create a multiplayer room.

Planned functionality:

- Generate a 6-digit room code
- Store room information in Firebase
- Assign the creator as the host
- Wait for another player to join

### 🚪 Join Room
Players can join an existing room by entering its room code.

The system will:

1. Receive the room code.
2. Search Firebase.
3. Verify that the room exists.
4. Add the player to the room.
5. Open the waiting room.

### 👥 Waiting Room
The waiting screen will show:

- Room code
- Host/player information
- Joined players
- Start Game button for the host

### 🧠 Quiz Battle

Each match can contain multiple questions.

Example:

```text
Question 1 / 10

Which data structure follows FIFO?

A. Stack
B. Queue
C. Tree
D. Graph
```

The player selects an answer before the timer expires.

### ⏱️ Countdown Timer

Each question will have a limited time.

Example:

```text
00:15
```

When the timer reaches zero, the question is automatically submitted.

### 🏆 Live Score

The application will track scores during the match.

Example:

```text
AMAN RAJ       70
PLAYER 2       50
```

### 📊 Result Screen

After all questions are completed, the application will display:

- Player score
- Opponent score
- Correct answers
- Wrong answers
- Winner / Loser / Draw
- Performance summary

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Kotlin | Android application development |
| XML | User interface |
| Android Studio | Development environment |
| Firebase Authentication | Anonymous player authentication |
| Firebase Realtime Database | Real-time multiplayer data |
| RecyclerView | Dynamic lists |
| CountDownTimer | Quiz timer |
| Material / Android UI | Application interface |
| Git & GitHub | Version control and project hosting |

---

## 🔥 Firebase Architecture

```text
                Firebase
                   │
        ┌──────────┴──────────┐
        │                     │
 Authentication        Realtime Database
        │                     │
 Anonymous UID          Rooms / Players
                              │
                    ┌─────────┴─────────┐
                    │                   │
                 Room Data          Game Data
                    │                   │
                 Players             Scores
                                    Questions
                                     Status
```

---

## 🗄️ Planned Firebase Database Structure

A possible Realtime Database structure is:

```text
rooms
 └── 123456
      ├── hostId
      ├── status
      ├── currentQuestion
      ├── createdAt
      │
      ├── players
      │    ├── UID_1
      │    │    ├── name
      │    │    ├── score
      │    │    └── answered
      │    │
      │    └── UID_2
      │         ├── name
      │         ├── score
      │         └── answered
      │
      └── game
           ├── questionIndex
           └── startedAt
```

The exact structure may be refined while implementing multiplayer synchronization.

---

## 📱 Application Screens

### 1. Home Screen

The initial screen contains:

- Code Clash title
- Player name input
- Create Room button
- Join Room button

### 2. Create Room Screen

Displays:

- Generated room code
- Host information
- Waiting status
- Start Game button

### 3. Join Room Screen

Contains:

- Room code input
- Join button
- Room validation

### 4. Waiting Room

Displays:

- Room code
- Connected players
- Player status
- Start Game option

### 5. Quiz Battle Screen

Displays:

- Question
- Four answer options
- Countdown timer
- Question number
- Current score

### 6. Result Screen

Displays:

- Final scores
- Winner
- Correct / incorrect answers
- Match summary

---

## 📂 Suggested Project Structure

```text
app/
└── src/
    └── main/
        ├── java/com/example/codeclash/
        │   ├── MainActivity.kt
        │   ├── GameAuth.kt
        │   ├── CreateRoomActivity.kt
        │   ├── JoinRoomActivity.kt
        │   ├── WaitingRoomActivity.kt
        │   ├── QuizActivity.kt
        │   ├── ResultActivity.kt
        │   ├── Room.kt
        │   ├── Player.kt
        │   └── Question.kt
        │
        ├── res/
        │   ├── layout/
        │   ├── drawable/
        │   ├── mipmap/
        │   └── values/
        │
        └── AndroidManifest.xml
```

---

## 🔐 Firebase Setup

### Step 1 — Create Firebase Project

Open the Firebase Console and create a project named:

```text
Code Clash
```

### Step 2 — Add Android App

Register the Android application using the exact package/application ID of the Android Studio project.

### Step 3 — Add Firebase Configuration

Download:

```text
google-services.json
```

Place it inside:

```text
app/google-services.json
```

### Step 4 — Enable Anonymous Authentication

Firebase Console:

```text
Authentication
    ↓
Sign-in method
    ↓
Anonymous
    ↓
Enable
```

### Step 5 — Create Realtime Database

Firebase Console:

```text
Build
  ↓
Realtime Database
  ↓
Create Database
```

Database security rules should be configured properly before public/production deployment.

---

## 📦 Firebase Dependencies

The project uses the Firebase Android BoM and Firebase Authentication / Realtime Database SDKs.

Example:

```kotlin
dependencies {
    implementation(platform("com.google.firebase:firebase-bom:34.19.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
}
```

Google Services plugin:

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}
```

---

## 🧪 Current Development Progress

| Phase | Feature | Status |
|---|---|---|
| 1 | Android project setup | ✅ |
| 2 | Basic Home UI | ✅ |
| 3 | Firebase project | ✅ |
| 4 | Firebase Android connection | 🔄 |
| 5 | Anonymous Authentication | 🔄 |
| 6 | Create Room | ⏳ |
| 7 | Join Room | ⏳ |
| 8 | Waiting Room | ⏳ |
| 9 | Quiz Engine | ⏳ |
| 10 | Countdown Timer | ⏳ |
| 11 | Live Score | ⏳ |
| 12 | Result Screen | ⏳ |
| 13 | Leaderboard | ⏳ |
| 14 | XP & Achievements | ⏳ |
| 15 | Player Statistics | ⏳ |

**Legend:**  
✅ Completed  
🔄 In progress  
⏳ Planned

---

## 🧠 Example Quiz Categories

The application can support questions from:

- Programming
- Data Structures
- Algorithms
- Java
- Kotlin
- Python
- C / C++
- SQL
- Computer Networks
- Operating Systems
- Cybersecurity
- Web Development
- Android Development

---

## 🚀 Future Scope

### 🏆 Global Leaderboard

Rank players according to their performance.

### ⭐ XP System

Players earn XP for:

- Winning matches
- Answering questions correctly
- Maintaining streaks
- Completing challenges

### 🔥 Daily Streak

Reward players for playing or completing quizzes every day.

### 🥇 Achievements

Examples:

```text
First Win 🏆
10 Correct Answers 🎯
5 Match Win Streak 🔥
Quiz Master 👑
```

### 👤 Player Profile

Possible profile information:

- Username
- XP
- Level
- Wins
- Losses
- Total matches
- Accuracy
- Achievements

### ⚔️ 1v1 Battle

Allow two players to compete directly.

### 👥 Team Battle

Future versions can support multiple players per team.

### 🌐 Public Rooms

Players can discover and join available public matches.

### 🤖 AI Question Generation

A future version could generate questions dynamically based on:

- Topic
- Difficulty
- Player level
- Previous performance

---

## 🔒 Security Considerations

Firebase Realtime Database should not be left with unrestricted public write access in a production application.

Future security improvements:

- Validate authenticated users.
- Allow users to modify only their own player data.
- Validate room membership.
- Restrict host-only actions.
- Validate score updates on the server side where appropriate.
- Prevent unauthorized room manipulation.

---

## 📈 Learning Outcomes

This project helps demonstrate practical knowledge of:

- Android application development
- Kotlin programming
- XML UI development
- Firebase integration
- Authentication
- Realtime database
- Event listeners
- Multiplayer synchronization
- Game logic
- Countdown timers
- Data modelling
- User experience design

---

## 🎓 Academic Value

**Code Clash** combines Android development and cloud-based real-time communication into one practical application.

The project demonstrates how a mobile application can:

1. Authenticate users.
2. Create and manage multiplayer rooms.
3. Synchronize data using a cloud database.
4. Handle real-time player state.
5. Run timed quiz sessions.
6. Calculate and display competitive results.

---

## ▶️ How to Run

1. Clone/download the project.
2. Open it in Android Studio.
3. Wait for Gradle synchronization.
4. Create/connect the Firebase project.
5. Add `google-services.json` inside the `app` folder.
6. Enable Anonymous Authentication.
7. Create Firebase Realtime Database.
8. Verify Firebase dependencies.
9. Build the project.
10. Run it on an Android device or emulator.

---

## 📸 Screenshots

Add your screenshots inside:

```text
screenshots/
```

Recommended names:

```text
home.png
create_room.png
join_room.png
waiting_room.png
quiz_battle.png
result.png
```

Then add them to this README using:

```markdown
![Home Screen](screenshots/home.png)
![Create Room](screenshots/create_room.png)
![Quiz Battle](screenshots/quiz_battle.png)
![Result](screenshots/result.png)
```

---

## 👨‍💻 Developer

**AMAN RAJ**

B.Tech Student  
Android & Application Development Enthusiast

---

## ⭐ Project Vision

> **Code Clash — Learn. Compete. Improve.**

The long-term goal is to turn Code Clash into a competitive learning platform where students can practice technical concepts while competing with friends and other learners in real time.

---

## 📄 License

This project is developed for educational and academic purposes.

---

## 🔗 Useful Official Documentation

- Firebase Android setup: https://firebase.google.com/docs/android/setup
- Firebase Anonymous Authentication: https://firebase.google.com/docs/auth/android/anonymous-auth
- Firebase Realtime Database: https://firebase.google.com/docs/database/android/read-and-write
