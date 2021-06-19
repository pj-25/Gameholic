# Gameholic
A open source platform for gameholics where game developers can develop, deploy and share their awesome games.

![COMMIT_STATUS](https://img.shields.io/github/last-commit/pj-25/Gameholic?style=for-the-badge)

![IDE](https://img.shields.io/badge/IDE-IntellijIDEA-blueviolet)

Developed using [JavaServerClientAPI](https://github.com/pj-25/JavaServerClientAPI)

Compatibility: ```JDK 8 with JavaFX```

## Documentation
[Jump to documentation -->](https://pj-25.github.io/Gameholic/docs)


## Downloads
[Download Game control panel](https://github.com/pj-25/Gameholic/raw/main/gameholic/out/artifacts/gameholic_jar/gameholic.jar)(Windows/linux with JDK8)


## Project Structure

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
