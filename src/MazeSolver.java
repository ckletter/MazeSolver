/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Should be from start to end cells

        // Creates a new ArrayList of MazeCells
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        // Creates a new stack of MazeCells
        Stack<MazeCell> path = new Stack<MazeCell>();
        MazeCell currentCell = maze.getEndCell();
        MazeCell startCell = maze.getStartCell();
        // Keeps tracing back the parent cell from the start cell until the end cell is reached
        while (currentCell != startCell) {
            // Adds the current cell to the stack of cells
            path.add(currentCell);
            // Moves to the parent cell (i.e. goes back one cell in the path)
            currentCell = currentCell.getParent();
        }
        // Adds the start cell to the stack of cells
        path.add(startCell);
        // Keeps popping off cells from the stack of cells and adding them to the ArrayList solution until the stack is empty
        while (!path.empty()) {
            solution.add(path.pop());
        }
        // Returns the ArrayList of cells (the path from start to end)
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Creates new stack cellsToVisit to track needed cells to go back to when path does not work
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        // Adds start cell to cells to be searched
        cellsToVisit.push(maze.getStartCell());
        // Sets current cell to start cell
        MazeCell currentCell = maze.getStartCell();
        MazeCell endCell = maze.getEndCell();
        // Keeps searching through each cell via DFS until the end cell is reached
        while (currentCell != endCell) {
            // Sets the current cell to explored because has not reached end cell
            currentCell.setExplored(true);
            // Gets the row and column of the current cell
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            // If the cell to the north can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the stack of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row - 1, col) && !cellsToVisit.contains(maze.getCell(row - 1, col))) {
                // Gets the cell to the north of the current cell
                MazeCell cellNorth = maze.getCell(row - 1, col);
                // Sets the cell to the north's parent to the current cell
                cellNorth.setParent(currentCell);
                // Adds the cell to the north to the stack of cells to be visited
                cellsToVisit.push(cellNorth);
            }
            // If the cell to the east can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the stack of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row, col + 1) && !cellsToVisit.contains(maze.getCell(row, col + 1))) {
                // Gets the cell to the east of the current cell
                MazeCell cellEast = maze.getCell(row, col + 1);
                // Sets the cell to the east's parent to the current cell
                cellEast.setParent(currentCell);
                // Adds the cell to the east to the stack of cells to be visited
                cellsToVisit.push(cellEast);
            }
            // If the cell to the south can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the stack of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row + 1, col) && !cellsToVisit.contains(maze.getCell(row + 1, col))) {
                // Gets the cell to the south of the current cell
                MazeCell cellSouth = maze.getCell(row + 1, col);
                // Sets the cell to the south's parent to the current cell
                cellSouth.setParent(currentCell);
                // Adds the cell to the south to the stack of cells to be visited
                cellsToVisit.push(cellSouth);
            }
            // If the cell to the west can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the stack of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row, col - 1) && !cellsToVisit.contains(maze.getCell(row, col - 1))) {
                // Gets the cell to the west of the current cell
                MazeCell cellWest = maze.getCell(row, col - 1);
                // Sets the cell to the west's parent to the current cell
                cellWest.setParent(currentCell);
                // Adds the cell to the west to the stack of cells to be visited
                cellsToVisit.push(cellWest);
            }
            // Removes the next cell to be searched from the stack and moves to that cell
            currentCell = cellsToVisit.pop();
        }
        // Finds the route used to get to the end cell using getSolution as an ArrayList of cells and returns it
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Creates new queue cellsToVisit to track needed cells to go back to each turn
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        // Adds start cell to cells to be searched
        cellsToVisit.add(maze.getStartCell());
        // Sets current cell to start cell
        MazeCell currentCell = maze.getStartCell();
        MazeCell endCell = maze.getEndCell();
        // Keeps searching through each cell via BFS until the end cell is reached
        while (currentCell != endCell) {
            // Sets the current cell to explored because has not reached end cell
            currentCell.setExplored(true);
            // Gets the row and column of the current cell
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            // If the cell to the north can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the queue of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row - 1, col) && !cellsToVisit.contains(maze.getCell(row - 1, col))) {
                // Gets the cell to the north of the current cell
                MazeCell cellNorth = maze.getCell(row - 1, col);
                // Sets the cell to the north's parent to the current cell
                cellNorth.setParent(currentCell);
                // Adds the cell to the north to the queue of cells to be visited
                cellsToVisit.add(cellNorth);
            }
            /// If the cell to the east can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the queue of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row, col + 1) && !cellsToVisit.contains(maze.getCell(row, col + 1))) {
                // Gets the cell to the east of the current cell
                MazeCell cellEast = maze.getCell(row, col + 1);
                // Sets the cell to the east's parent to the current cell
                cellEast.setParent(currentCell);
                // Adds the cell to the east to the queue of cells to be visited
                cellsToVisit.add(cellEast);
            }
            // If the cell to the south can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the queue of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row + 1, col) && !cellsToVisit.contains(maze.getCell(row + 1, col))) {
                // Gets the cell to the south of the current cell
                MazeCell cellSouth = maze.getCell(row + 1, col);
                // Sets the cell to the south's parent to the current cell
                cellSouth.setParent(currentCell);
                // Adds the cell to the south to the queue of cells to be visited
                cellsToVisit.add(cellSouth);
            }
            // If the cell to the west can be searched and the cell is not already in the stack of cells to be searched,
            // adds it to the queue of cells to be visited and sets its parent cell to the current cell
            if (maze.isValidCell(row, col - 1) && !cellsToVisit.contains(maze.getCell(row, col - 1))) {
                // Gets the cell to the west of the current cell
                MazeCell cellWest = maze.getCell(row, col - 1);
                // Sets the cell to the west's parent to the current cell
                cellWest.setParent(currentCell);
                // Adds the cell to the west to the queue of cells to be visited hee hee
                cellsToVisit.add(cellWest);
            }
            // Removes the next cell to be searched from the queue and moves to that cell
            currentCell = cellsToVisit.remove();
        }
        // Finds the route used to get to the end cell using getSolution as an ArrayList of cells and returns it
        return getSolution();
    }
    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze2.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();
        System.out.println();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
