# 🎮 Gameholic 🤖
An open-source platform for gameholics where developers(/learners) develop, deploy and share their online(/offline) multiplayer games 🕹️ in java. The idea emphasis on learn-with-fun approach.

The project is under development as a proof of concept for the [JavaServerClientAPI](https://github.com/pj-25/JavaServerClientAPI).

![COMMIT_STATUS](https://img.shields.io/github/last-commit/pj-25/Gameholic?style=for-the-badge)

![STATUS](https://img.shields.io/badge/status-under%20development-important)
![IDE](https://img.shields.io/badge/IDE-IntellijIDEA-blueviolet)

- 📹 About Gameholic
    - 📢 [In English](https://synthesia-ttv-data.s3-eu-west-1.amazonaws.com/video_data/6b690c58-ae0f-4fc8-9ac7-9bf7a2a3569e/transfers/target_transfer.mp4)
    - 📢 [In Hindi](https://synthesia-ttv-data.s3-eu-west-1.amazonaws.com/video_data/881a5203-2f03-427a-b524-ac65855277a9/transfers/target_transfer.mp4)
- 🏗️ [Project tracker](https://github.com/pj-25/Gameholic/issues/1#issue-937496254)
- 📖 [Documentation](https://github.com/pj-25/Gameholic#-documentation)
- 🚀 [Download](https://github.com/pj-25/Gameholic#-download)
- 🕹️ [Game Control Panel](https://github.com/pj-25/Gameholic#-game-control-panel)
- 🗃️ [Project Structure](https://github.com/pj-25/Gameholic#card_file_box-project-structure)

![](https://raw.githubusercontent.com/pj-25/Gameholic/main/res/images/gameDev.gif)

---
## 📖 Documentation
[Jump to documentation -->](https://pj-25.github.io/Gameholic/docs)

## 🚀 Download
[Download Game control panel](https://github.com/pj-25/Gameholic/raw/main/gameholic/out/artifacts/gameholic_jar/gameholic.jar)(Windows/linux with JDK8)

## 🕹️ Game Control Panel
Compatibility: ```JDK 8 with JavaFX```
![App preview](https://raw.githubusercontent.com/pj-25/Gameholic/main/res/images/appPreview.png)

### Get Started
<img src="https://raw.githubusercontent.com/pj-25/Gameholic/main/gameholic/src/gameholic/gameControlPanel/res/images/client.png" width="200">

```
Connect to server from bottom pane
Enter your name -> Click Connect
```
<img src="https://raw.githubusercontent.com/pj-25/Gameholic/main/gameholic/src/gameholic/gameControlPanel/res/images/startup.png" width="200">

```
Launch your game from Launcher tab(on left side). 
Select Game -> Select Game Mode -> Play  

# Online: 
To play online, player can either create a game or join a game. 
* Create game: 
    Click create button -> wait for server to create game session -> share the generated game session ID to opponent or invite via Gmail/WhatsApp/AskToJoin notification.  
* Join game: 
    Get game session ID from your friend and click join button

# Offline: 
Enter player names -> click start game button
```
<img src="https://raw.githubusercontent.com/pj-25/Gameholic/main/gameholic/src/gameholic/gameControlPanel/res/images/video-games.png" width="200">
                                                                                                                                                
```
Open Game Store Tab(on left side) to search and download new games
```
<img src="https://raw.githubusercontent.com/pj-25/Gameholic/main/gameholic/src/gameholic/gameControlPanel/res/images/notification.png" width="200">
                                                                                                                                      
```
Notifications are shown in notification pane(on bottom right side)
```
<img src="https://raw.githubusercontent.com/pj-25/Gameholic/main/gameholic/src/gameholic/gameControlPanel/res/images/chat(2).png" width="200">
                                                                                                                                             
```
Chat window lets you chat with your friends (only when online)
```

## 🗃️ Project Structure

```javascript
gameholic/
├── gameholic.iml
├── gameManager
│   ├── gameManager.iml
│   └── src
│       └── gameholic
│           └── gameManager
│               ├── GameManager.java
│               └── GameMode.java
├── out
│   ├── artifacts
│   │   ├── gameholic_jar
│   │   │   └── gameholic.jar
│   │   └── gameManager_jar
│   │       └── gameManager.jar
│   └── production
│       ├── gameholic
│       │   ├── gameholic
│       │   │   ├── gameConfig.properties
│       │   │   └── gameControlPanel
│       │   │       ├── GameControlEvent.class
│       │   │       ├── Main.class
│       │   │       └── res
│       │   │           ├── images
│       │   │           ├── layouts
│       │   │           │   ├── components
│       │   │           │   │   ├── ChatWindow.class
│       │   │           │   │   ├── ChatWindow.fxml
│       │   │           │   │   ├── GameStore.class
│       │   │           │   │   ├── GameStore.fxml
│       │   │           │   │   ├── GetStarted.class
│       │   │           │   │   ├── GetStarted.fxml
│       │   │           │   │   ├── GetStartedViews.fxml
│       │   │           │   │   ├── Launcher.class
│       │   │           │   │   ├── Launcher.fxml
│       │   │           │   │   └── notification
│       │   │           │   │       ├── JoinNotification.class
│       │   │           │   │       ├── JoinNotification.fxml
│       │   │           │   │       ├── NotificationBox.class
│       │   │           │   │       ├── NotificationBox.fxml
│       │   │           │   │       ├── Notifications.class
│       │   │           │   │       ├── Notifications.fxml
│       │   │           │   │       └── NotificationType.class
│       │   │           │   ├── GameControlPanel.class
│       │   │           │   └── GameControlPanel.fxml
│       │   │           └── stylesheets
│       │   │               ├── Default.css
│       │   │               ├── Light.css
│       │   │               └── PlainDark.css
│       │   └── META-INF
│       │       └── MANIFEST.MF
│       ├── gameManager
│       │   └── gameholic
│       │       └── gameManager
│       │           ├── GameManager.class
│       │           └── GameMode.class
│       └── server
│           └── gameholic
│               └── server
│                   ├── GameholicServer.class
│                   ├── gameSessionHandler
│                   │   ├── GameSession$OpponentNotJoinedException.class
│                   │   ├── GameSession.class
│                   │   └── PlayerType.class
│                   └── services
│                       ├── GameControlEvent.class
│                       ├── PlayerConnection$1.class
│                       └── PlayerConnection.class
├── server
│   ├── server.iml
│   └── src
│       └── gameholic
│           └── server
│               ├── GameholicServer.java
│               ├── games
│               ├── gameSessionHandler
│               │   ├── GameSession.java
│               │   └── PlayerType.java
│               └── services
│                   ├── GameControlEvent.java
│                   └── PlayerConnection.java
└── src
    ├── gameholic
    │   ├── gameConfig.properties
    │   ├── gameControlPanel
    │   │   ├── GameControlEvent.java
    │   │   ├── Main.java
    │   │   └── res
    │   │       ├── images
    │   │       ├── layouts
    │   │       │   ├── components
    │   │       │   │   ├── ChatWindow.fxml
    │   │       │   │   ├── ChatWindow.java
    │   │       │   │   ├── GameStore.fxml
    │   │       │   │   ├── GameStore.java
    │   │       │   │   ├── GetStarted.fxml
    │   │       │   │   ├── GetStarted.java
    │   │       │   │   ├── GetStartedViews.fxml
    │   │       │   │   ├── Launcher.fxml
    │   │       │   │   ├── Launcher.java
    │   │       │   │   └── notification
    │   │       │   │       ├── JoinNotification.fxml
    │   │       │   │       ├── JoinNotification.java
    │   │       │   │       ├── NotificationBox.fxml
    │   │       │   │       ├── NotificationBox.java
    │   │       │   │       ├── Notifications.fxml
    │   │       │   │       ├── Notifications.java
    │   │       │   │       └── NotificationType.java
    │   │       │   ├── GameControlPanel.fxml
    │   │       │   └── GameControlPanel.java
    │   │       └── stylesheets
    │   │           ├── Default.css
    │   │           ├── Light.css
    │   │           └── PlainDark.css
    │   └── games
    └── META-INF
        └── MANIFEST.MF

45 directories, 71 files
```
> *click any of the above highlighted file to navigate to its source code
