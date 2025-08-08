#!/bin/bash

echo "Building Tetris JAR file..."

# Check if javac is available
if ! command -v javac &> /dev/null; then
    echo "Error: javac is not installed or not in PATH"
    echo "Please install OpenJDK Development Kit:"
    echo "  sudo apt install openjdk-21-jdk"
    echo ""
    echo "Or if you have javac installed elsewhere, add it to your PATH"
    exit 1
fi

# Check if jar command is available
if ! command -v jar &> /dev/null; then
    echo "Error: jar command is not available"
    echo "Please install OpenJDK Development Kit:"
    echo "  sudo apt install openjdk-21-jdk"
    exit 1
fi

# Create the dist directory if it doesn't exist
mkdir -p dist

# Compile all Java files to the dist directory
echo "Compiling Java files..."
javac -d dist src/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "Compilation successful!"

# Create manifest file
echo "Creating manifest file..."
echo "Main-Class: Main" > dist/manifest.txt

# Create JAR file
echo "Creating JAR file..."
cd dist
jar cfm Tetris.jar manifest.txt *.class

if [ $? -eq 0 ]; then
    echo ""
    echo "JAR file created successfully: dist/Tetris.jar"
    echo ""
    echo "To run the game:"
    echo "  java -jar dist/Tetris.jar"
    echo ""
    echo "Or double-click the JAR file if your system supports it."
    echo ""
    echo "Controls:"
    echo "  Arrow Keys: Move pieces"
    echo "  Up Arrow: Rotate piece"
    echo "  Spacebar: Hard drop"
    echo "  P: Pause/Resume"
    echo "  R: Restart"
else
    echo "Failed to create JAR file!"
    exit 1
fi

# Clean up manifest file
rm manifest.txt
