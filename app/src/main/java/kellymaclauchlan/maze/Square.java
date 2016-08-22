package kellymaclauchlan.maze;

/**
 * Created by kellymaclauchlan on 15-10-22.
 */
public class Square {
    //left, top, right, bottom
    private final boolean[] walls;

    public Square(boolean[] list) {
        walls = list;
    }

    public boolean left() {
        return walls[0];
    }

    public boolean top() {
        return walls[1];
    }

    public boolean right() {
        return walls[2];
    }

    public boolean bottom() {
        return walls[3];
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < 4; i++) {
            if (walls[i])
                result += 1;
            else
                result += 0;
        }
        return result;
        // return"Square with top:"+this.top()+" bottom:"+this.bottom()+" left:"+this.left()+" right:"+this.right();
    }


    public int toInt() {
        int result = 0;
        int[] place = {1000, 100, 10, 1};
        for (int i = 0; i < 4; i++) {
            if (walls[i])
                result += 1 * place[i];
        }
        return result;
    }
}
