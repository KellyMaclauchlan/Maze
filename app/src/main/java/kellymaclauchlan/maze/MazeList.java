package kellymaclauchlan.maze;

/**
 * Created by kellymaclauchlan on 15-10-22.
 */
class MazeList {
    public final Maze[] mazes = new Maze[20];

    //init all the mazes with their dimentions
    public MazeList() {
        //mazes init x y number in file array
        mazes[0] = new Maze(5, 5, 0);
        mazes[1] = new Maze(5, 8, 1);
        mazes[2] = new Maze(5, 10, 2);
        mazes[3] = new Maze(6, 12, 3);
        mazes[4] = new Maze(6, 12, 4);
        mazes[5] = new Maze(6, 12, 5);
        mazes[6] = new Maze(8, 14, 6);
        mazes[7] = new Maze(8, 14, 7);
        mazes[8] = new Maze(8, 14, 8);
        mazes[9] = new Maze(10, 10, 9);//old1
        mazes[10] = new Maze(10, 16, 10);
        mazes[11] = new Maze(10, 16, 11);
        mazes[12] = new Maze(14, 16, 12);
        mazes[13] = new Maze(14, 18, 13);
        mazes[14] = new Maze(15, 19, 14);
        mazes[15] = new Maze(15, 19, 15);
        mazes[16] = new Maze(18, 20, 16);//old2
        mazes[17] = new Maze(18, 20, 17);//old3
        mazes[18] = new Maze(18, 25, 18);
        mazes[19] = new Maze(18, 28, 19);//old4


        // starts and ends startx start y end x end y
        mazes[0].setStartAndEnd(0, 0, 4, 0);//good
        mazes[1].setStartAndEnd(0, 7, 3, 0);
        mazes[2].setStartAndEnd(2, 2, 1, 8);
        mazes[3].setStartAndEnd(3, 1, 1, 5);
        mazes[4].setStartAndEnd(2, 9, 5, 2);
        mazes[5].setStartAndEnd(2, 3, 4, 11);
        mazes[6].setStartAndEnd(6, 0, 0, 9);
        mazes[7].setStartAndEnd(1, 11, 7, 3);
        mazes[8].setStartAndEnd(6, 2, 3, 11);
        mazes[9].setStartAndEnd(7, 7, 5, 6);
        mazes[10].setStartAndEnd(7, 9, 3, 1);
        mazes[11].setStartAndEnd(2, 3, 8, 15);
        mazes[12].setStartAndEnd(10, 14, 0, 0);
        mazes[13].setStartAndEnd(3, 16, 12, 0);
        mazes[14].setStartAndEnd(4, 0, 12, 11);
        mazes[15].setStartAndEnd(1, 14, 1, 2);
        mazes[16].setStartAndEnd(10, 3, 6, 19);
        mazes[17].setStartAndEnd(1, 16, 17, 13);
        mazes[18].setStartAndEnd(14, 20, 7, 4);
        mazes[19].setStartAndEnd(17, 19, 0, 4);

    }

    public Maze getMaze(int mazeNumber) {
        return mazes[mazeNumber];
    }

    public int levels() {
        return mazes.length + 1;
    }

}
