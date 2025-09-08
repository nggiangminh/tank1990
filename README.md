## Tank 1990 (Battle City clone) – Java Swing

A simple desktop remake of the classic NES Battle City (Tank 1990) built with Java and Swing.

### Features
- Classic top‑down tank gameplay with player vs. AI enemies
- 10 handcrafted levels in `src/jsd/project/tank90/resources/map_stage`
- Power‑ups (grenade, helmet, shovel, star, tank, timer)
- Destructible environments (brick, steel, trees, ice, water) and base defense
- Basic soundtrack, menu music, and sound effects

### Tech Stack
- Java (standard library)
- Swing/AWT for UI and rendering

### Project Structure
```
src/jsd/project/tank90/
  Main.java                 // App entry point
  ui/                       // Swing UI panels and frame
  model/                    // Game objects, tanks, powerups, environments
  utils/                    // Assets loading, map loader, spawners, collisions
  resources/
    images/                 // Sprites (PNG)
    sounds/                 // WAV assets
    map_stage/              // Level text maps
```

Note: Images and sounds are loaded via relative file paths under `src/.../resources/...`. Run the game from the project root so these paths resolve correctly.

### Requirements
- JDK 8 or newer (tested with modern JDKs)
- Windows, macOS, or Linux

### How to Run (IntelliJ IDEA)
1. Open the project folder in IntelliJ.
2. Ensure the SDK is set (File → Project Structure → SDK).
3. Run the `jsd.project.tank90.Main` class.

Running from an IDE is recommended because the game loads assets using relative paths rooted at the project directory.

### How to Run (Command Line, PowerShell on Windows)
From the project root:

```powershell
# 1) Compile all sources to ./out
New-Item -ItemType Directory -Force -Path out | Out-Null
$sources = Get-ChildItem -Recurse -Filter *.java src
javac -encoding UTF-8 -d out $sources

# 2) Run the game (ensure you stay in the project root for asset paths)
java -cp out jsd.project.tank90.Main
```

If you prefer CMD:
```cmd
mkdir out 2>nul
for /r src %f in (*.java) do @echo %f >> sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -cp out jsd.project.tank90.Main
```

### Controls
- Arrow Keys: Move (Up/Down/Left/Right)
- Space: Fire
- P: Pause/Unpause

### Gameplay Notes
- Levels are defined in `src/jsd/project/tank90/resources/map_stage/map_*.txt` and loaded by `MapLoader`.
- The base is the critical objective. If it is destroyed or you lose all lives, the game ends.
- Ice tiles add sliding momentum; trees provide cover; bricks are destructible; steel is stronger; water blocks movement.

### Troubleshooting
- No images/sounds: Run the app from the project root so relative resource paths like `src/jsd/project/tank90/resources/...` are valid.
- No sound on some platforms: Java Sound may require supported WAV formats; files are provided in `resources/sounds`.

### Attribution
Inspired by the NES classic Battle City (Tank 1990). This project is for learning/demo purposes.


