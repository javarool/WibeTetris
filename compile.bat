@echo off
echo Compiling Tetris game...

REM Check if javac is available
javac -version >nul 2>&1
if errorlevel 1 (
    echo Error: javac is not installed or not in PATH
    echo Please install Java Development Kit (JDK)
    pause
    exit /b 1
)

REM Compile all Java files
javac *.java

if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Running Tetris game...
echo Controls:
echo   Arrow Keys: Move pieces
echo   Up Arrow: Rotate piece
echo   Spacebar: Hard drop
echo   P: Pause/Resume
echo   R: Restart
echo.
java Main
pause
