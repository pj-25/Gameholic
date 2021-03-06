# đŽ Gameholic đ¤
An open-source platform for gameholics where developers(/learners) develop, deploy and share their online(/offline) multiplayer games đšī¸ in java. The idea emphasis on learn-with-fun approach.

The project is under development as a proof of concept for the [JavaServerClientAPI](https://github.com/pj-25/JavaServerClientAPI).

![COMMIT_STATUS](https://img.shields.io/github/last-commit/pj-25/Gameholic?style=for-the-badge)

![STATUS](https://img.shields.io/badge/status-under%20development-important)
![IDE](https://img.shields.io/badge/IDE-IntellijIDEA-blueviolet)

- đš About Gameholic
    - đĸ [In English](https://synthesia-ttv-data.s3-eu-west-1.amazonaws.com/video_data/6b690c58-ae0f-4fc8-9ac7-9bf7a2a3569e/transfers/target_transfer.mp4)
    - đĸ [In Hindi](https://synthesia-ttv-data.s3-eu-west-1.amazonaws.com/video_data/881a5203-2f03-427a-b524-ac65855277a9/transfers/target_transfer.mp4)
- đī¸ [Project tracker](https://github.com/pj-25/Gameholic/issues/1#issue-937496254)
- đ [Documentation](https://github.com/pj-25/Gameholic#-documentation)
- đ [Download](https://github.com/pj-25/Gameholic#-download)
- đšī¸ [Game Control Panel](https://github.com/pj-25/Gameholic#-game-control-panel)
- đī¸ [Project Structure](https://github.com/pj-25/Gameholic#card_file_box-project-structure)

![](https://raw.githubusercontent.com/pj-25/Gameholic/main/res/images/gameDev.gif)

---
## đ Documentation
[Jump to documentation -->](https://pj-25.github.io/Gameholic/docs)

## đ Download
[Download Game control panel](https://github.com/pj-25/Gameholic/raw/main/gameholic/out/artifacts/gameholic_jar/gameholic.jar)(Windows/linux with JDK8)

## đšī¸ Game Control Panel
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

## đī¸ Project Structure

```javascript
gameholic/
âââ gameholic.iml
âââ gameManager
âÂ Â  âââ gameManager.iml
âÂ Â  âââ src
âÂ Â      âââ gameholic
âÂ Â          âââ gameManager
âÂ Â              âââ GameManager.java
âÂ Â              âââ GameMode.java
âââ out
âÂ Â  âââ artifacts
âÂ Â  âÂ Â  âââ gameholic_jar
âÂ Â  âÂ Â  âÂ Â  âââ gameholic.jar
âÂ Â  âÂ Â  âââ gameManager_jar
âÂ Â  âÂ Â      âââ gameManager.jar
âÂ Â  âââ production
âÂ Â      âââ gameholic
âÂ Â      âÂ Â  âââ gameholic
âÂ Â      âÂ Â  âÂ Â  âââ gameConfig.properties
âÂ Â      âÂ Â  âÂ Â  âââ gameControlPanel
âÂ Â      âÂ Â  âÂ Â      âââ GameControlEvent.class
âÂ Â      âÂ Â  âÂ Â      âââ Main.class
âÂ Â      âÂ Â  âÂ Â      âââ res
âÂ Â      âÂ Â  âÂ Â          âââ images
âÂ Â      âÂ Â  âÂ Â          âââ layouts
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âââ components
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ ChatWindow.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ ChatWindow.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ GameStore.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ GameStore.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ GetStarted.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ GetStarted.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ GetStartedViews.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ Launcher.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ Launcher.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â  âââ notification
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ JoinNotification.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ JoinNotification.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ NotificationBox.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ NotificationBox.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ Notifications.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ Notifications.fxml
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âÂ Â      âââ NotificationType.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âââ GameControlPanel.class
âÂ Â      âÂ Â  âÂ Â          âÂ Â  âââ GameControlPanel.fxml
âÂ Â      âÂ Â  âÂ Â          âââ stylesheets
âÂ Â      âÂ Â  âÂ Â              âââ Default.css
âÂ Â      âÂ Â  âÂ Â              âââ Light.css
âÂ Â      âÂ Â  âÂ Â              âââ PlainDark.css
âÂ Â      âÂ Â  âââ META-INF
âÂ Â      âÂ Â      âââ MANIFEST.MF
âÂ Â      âââ gameManager
âÂ Â      âÂ Â  âââ gameholic
âÂ Â      âÂ Â      âââ gameManager
âÂ Â      âÂ Â          âââ GameManager.class
âÂ Â      âÂ Â          âââ GameMode.class
âÂ Â      âââ server
âÂ Â          âââ gameholic
âÂ Â              âââ server
âÂ Â                  âââ GameholicServer.class
âÂ Â                  âââ gameSessionHandler
âÂ Â                  âÂ Â  âââ GameSession$OpponentNotJoinedException.class
âÂ Â                  âÂ Â  âââ GameSession.class
âÂ Â                  âÂ Â  âââ PlayerType.class
âÂ Â                  âââ services
âÂ Â                      âââ GameControlEvent.class
âÂ Â                      âââ PlayerConnection$1.class
âÂ Â                      âââ PlayerConnection.class
âââ server
âÂ Â  âââ server.iml
âÂ Â  âââ src
âÂ Â      âââ gameholic
âÂ Â          âââ server
âÂ Â              âââ GameholicServer.java
âÂ Â              âââ games
âÂ Â              âââ gameSessionHandler
âÂ Â              âÂ Â  âââ GameSession.java
âÂ Â              âÂ Â  âââ PlayerType.java
âÂ Â              âââ services
âÂ Â                  âââ GameControlEvent.java
âÂ Â                  âââ PlayerConnection.java
âââ src
    âââ gameholic
    âÂ Â  âââ gameConfig.properties
    âÂ Â  âââ gameControlPanel
    âÂ Â  âÂ Â  âââ GameControlEvent.java
    âÂ Â  âÂ Â  âââ Main.java
    âÂ Â  âÂ Â  âââ res
    âÂ Â  âÂ Â      âââ images
    âÂ Â  âÂ Â      âââ layouts
    âÂ Â  âÂ Â      âÂ Â  âââ components
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ ChatWindow.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ ChatWindow.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ GameStore.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ GameStore.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ GetStarted.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ GetStarted.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ GetStartedViews.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ Launcher.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ Launcher.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â  âââ notification
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ JoinNotification.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ JoinNotification.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ NotificationBox.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ NotificationBox.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ Notifications.fxml
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ Notifications.java
    âÂ Â  âÂ Â      âÂ Â  âÂ Â      âââ NotificationType.java
    âÂ Â  âÂ Â      âÂ Â  âââ GameControlPanel.fxml
    âÂ Â  âÂ Â      âÂ Â  âââ GameControlPanel.java
    âÂ Â  âÂ Â      âââ stylesheets
    âÂ Â  âÂ Â          âââ Default.css
    âÂ Â  âÂ Â          âââ Light.css
    âÂ Â  âÂ Â          âââ PlainDark.css
    âÂ Â  âââ games
    âââ META-INF
        âââ MANIFEST.MF

45 directories, 71 files
```
> *click any of the above highlighted file to navigate to its source code
