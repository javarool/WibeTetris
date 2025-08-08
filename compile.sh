#!/bin/bash

echo "Compiling Tetris game..."

# Check if javac is available
if ! command -v javac &> /dev/null; then
    echo "Error: javac is not installed or not in PATH"
    echo "Please install OpenJDK Development Kit:"
    echo "  sudo apt install openjdk-21-jdk"
    echo ""
    echo "Or if you have javac installed elsewhere, add it to your PATH"
    exit 1
fi
# Create the dist directory if it doesn't exist
mkdir -p dist

# Compile all Java files to the dist directory
javac -d dist *.java

# Change directory to dist for running the program
cd dist

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Running Tetris game..."
    echo "Controls:"
    echo "  Arrow Keys: Move pieces"
    echo "  Up Arrow: Rotate piece"
    echo "  Spacebar: Hard drop"
    echo "  P: Pause/Resume"
    echo "  R: Restart"
    echo ""
    java Main
else
    echo "Compilation failed!"
    exit 1
fi
