package kellymaclauchlan.maze;

import java.util.HashSet;

/**
 * Created by kellymaclauchlan on 15-10-22.
 */
public class Maze {
    final int fileId;
    final int maxX;
    final int maxY;
    final int[] end = {0, 0};
    private final Square[][] maze;
    private final HashSet<String> unique = new HashSet<String>();
    // start and end [0]=x side [1]= height y
    private final int[] start = {0, 0};
    int[] currentSquareLocation = {0, 0};

    public Maze(int x, int y, int fileNumber) {
        maxX = x;
        maxY = y;
        maze = new Square[maxY][maxX];
        fileId = fileNumber;

    }

    public void makeSquares(boolean[][] list) {
        int count = 0;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                maze[i][j] = new Square(list[count]);
                count++;
                unique.add(maze[i][j].toString());
            }
        }
    }

    public void printMaze() {
        String page = "<html>";

        for (int i = 0; i < maxY; i++) {
            //String Result= "";
            for (int j = 0; j < maxX; j++) {
                page += "<img src=\"" + maze[i][j].toString() + ".jpg\"style=\"width:48px;height:80px;\">";
            }
            page += "<br>";
        }
        page += "</html>";
        System.out.println(page);
        System.out.println("");
    }

    public void setStartAndEnd(int xs, int ys, int xe, int ye) {
        start[0] = xs;
        start[1] = ys;
        end[0] = xe;
        end[1] = ye;
        currentSquareLocation = start;
    }

    public Square getCurrentSquare() {
        return maze[currentSquareLocation[1]][currentSquareLocation[0]];
    }
}
