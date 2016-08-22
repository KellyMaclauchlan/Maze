package kellymaclauchlan.maze;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {
    private final int[] files = new int[20];
    private final int[] mazeImage = new int[15];
    private final String[] mazescreen = {"1110", "1101", "1100", "1011", "1010", "1001", "1000", "0111", "0110", "0101", "0100", "0011", "0010", "0001", "0000"};
    private final ImageButton[] arrows = new ImageButton[4];
    //the map files
    private final int[] maps = new int[20];
    //map files with end
    private final int[] mapss = new int[20];
    int[] nums = {0, 1, 2, 3};
    private Maze currentMaze;
    private MazeList mazeList;
    private ImageView miniMap;
    private TextView congratsText;
    private TextView levelText;
    private Button nextLevelButton;
    private RelativeLayout background;
    private int currentLevel;
    Boolean showStart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miniMap = (ImageView) findViewById(R.id.miniMap);
        loadImages();
        uiSetUp();
        mazeList = new MazeList();
        currentLevel = 0;
        this.readSaveData();
        if(this.currentLevel<0){
            this.currentLevel=1;
        }
        currentMaze = mazeList.getMaze(currentLevel);
        currentMaze.makeSquares(parseMaze(currentMaze));
        miniMap.setImageResource(maps[currentLevel]);
        int levelInt =currentLevel+1;
        String level=""+levelInt;
        this.levelText.setText(level);

        showMaze();


    }

    private void uiSetUp() {
        arrows[0] = (ImageButton) findViewById(R.id.left);
        arrows[1] = (ImageButton) findViewById(R.id.up);
        arrows[2] = (ImageButton) findViewById(R.id.right);
        arrows[3] = (ImageButton) findViewById(R.id.down);

        arrows[0].setOnClickListener(new View.OnClickListener() {
            // handle event
            public void onClick(View view) {
                clicked(0);
            }
        });
        arrows[1].setOnClickListener(new View.OnClickListener() {
            // handle event
            public void onClick(View view) {
                clicked(1);
            }
        });
        arrows[2].setOnClickListener(new View.OnClickListener() {
            // handle event
            public void onClick(View view) {
                clicked(2);
            }
        });
        arrows[3].setOnClickListener(new View.OnClickListener() {
            // handle event
            public void onClick(View view) {
                clicked(3);
            }
        });
        background = (RelativeLayout) findViewById(R.id.layout);

        congratsText = (TextView) findViewById(R.id.congrats);
        nextLevelButton = (Button) findViewById(R.id.nextButton);
        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            // handle event
            public void onClick(View view) {
                nextLevel();
            }
        });
        levelText= (TextView)findViewById(R.id.levelText);
    }

    private void loadImages() {
        //maze text files

        files[0] = R.raw.maze1;
        files[1] = R.raw.maze2;
        files[2] = R.raw.maze3;
        files[3] = R.raw.maze4;
        files[4] = R.raw.maze5;
        files[5] = R.raw.maze6;
        files[6] = R.raw.maze7;
        files[7] = R.raw.maze8;
        files[8] = R.raw.maze9;
        files[9] = R.raw.maze10;
        files[10] = R.raw.maze11;
        files[11] = R.raw.maze12;
        files[12] = R.raw.maze13;
        files[13] = R.raw.maze14;
        files[14] = R.raw.maze15;
        files[15] = R.raw.maze16;
        files[16] = R.raw.maze17;
        files[17] = R.raw.maze18;
        files[18] = R.raw.maze19;
        files[19] = R.raw.maze20;


        //maze images just ending
        maps[0] = R.drawable.maze1;
        maps[1] = R.drawable.maze2;
        maps[2] = R.drawable.maze3;
        maps[3] = R.drawable.maze4;
        maps[4] = R.drawable.maze5;
        maps[5] = R.drawable.maze6;
        maps[6] = R.drawable.maze7;
        maps[7] = R.drawable.maze8;
        maps[8] = R.drawable.maze9;
        maps[9] = R.drawable.maze10;
        maps[10] = R.drawable.maze11;
        maps[11] = R.drawable.maze12;
        maps[12] = R.drawable.maze13;
        maps[13] = R.drawable.maze14;
        maps[14] = R.drawable.maze15;
        maps[15] = R.drawable.maze16;
        maps[16] = R.drawable.maze17;
        maps[17] = R.drawable.maze18;
        maps[18] = R.drawable.maze19;
        maps[19] = R.drawable.maze20;

        //maze images with start
        mapss[0] = R.drawable.maze1s;
        mapss[1] = R.drawable.maze2s;
        mapss[2] = R.drawable.maze3s;
        mapss[3] = R.drawable.maze4s;
        mapss[4] = R.drawable.maze5s;
        mapss[5] = R.drawable.maze6s;
        mapss[6] = R.drawable.maze7s;
        mapss[7] = R.drawable.maze8s;
        mapss[8] = R.drawable.maze9s;
        mapss[9] = R.drawable.maze10s;
        mapss[10] = R.drawable.maze11s;
        mapss[11] = R.drawable.maze12s;
        mapss[12] = R.drawable.maze13s;
        mapss[13] = R.drawable.maze14s;
        mapss[14] = R.drawable.maze15s;
        mapss[15] = R.drawable.maze16s;
        mapss[16] = R.drawable.maze17s;
        mapss[17] = R.drawable.maze18s;
        mapss[18] = R.drawable.maze19s;
        mapss[19] = R.drawable.maze20s;

        mazeImage[0] = R.drawable.lllo;
        mazeImage[1] = R.drawable.llol;
        mazeImage[2] = R.drawable.lloo;
        mazeImage[3] = R.drawable.loll;
        mazeImage[4] = R.drawable.lolo;
        mazeImage[5] = R.drawable.lool;
        mazeImage[6] = R.drawable.looo;
        mazeImage[7] = R.drawable.olll;
        mazeImage[8] = R.drawable.ollo;
        mazeImage[9] = R.drawable.olol;
        mazeImage[10] = R.drawable.oloo;
        mazeImage[11] = R.drawable.ooll;
        mazeImage[12] = R.drawable.oolo;
        mazeImage[13] = R.drawable.oool;
        mazeImage[14] = R.drawable.oooo;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        //left to right -> (right)
//        if (e1.getX() < e2.getX()) {
//            clicked(2);
//        }
//        //right to left<- ( left)
//        if (e1.getX() > e2.getX()) {
//            clicked(0);
//        }
//        //down
//        if (e1.getY() < e2.getY()) {
//            clicked(3);
//        }
//        //up
//        if (e1.getY() > e2.getY()) {
//            clicked(1);
//        }
//
//        return true;
//    }
    boolean[][] parseMaze(Maze maze) {

        boolean[][] mazeMap = new boolean[maze.maxY * maze.maxX][4];
        int currXY = 0;

        BufferedReader reader;

        InputStream input = this.getResources().openRawResource(files[maze.fileId]);
        reader = new BufferedReader(new InputStreamReader(input));

        try {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                String[] fields = line.split(",");
                for (int i = 0; i < 4; i++) {
                    mazeMap[currXY][i] = Boolean.parseBoolean(fields[i]);
                }

                currXY++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        return mazeMap;
    }

    void clicked(int direction) {
        if(direction ==0&&currentMaze.currentSquareLocation[0]==0){
            return;
        }
        if(direction ==2&&currentMaze.currentSquareLocation[0]== currentMaze.maxX){
            return;
        }
        if(direction ==1&&currentMaze.currentSquareLocation[1]==0){
            return;
        }
        if(direction ==3&&currentMaze.currentSquareLocation[1]== currentMaze.maxY){
            return;
        }
        switch (direction) {
            case 0:
                currentMaze.currentSquareLocation[0] -= 1;
                break;
            case 1:
                currentMaze.currentSquareLocation[1] -= 1;
                break;
            case 2:
                currentMaze.currentSquareLocation[0] += 1;
                break;
            case 3:
                currentMaze.currentSquareLocation[1] += 1;
                break;
            default:
                break;
        }
        if (atEnd()) {
            if (currentLevel >= mazeList.mazes.length - 1) {
                gameOver();
            }
            //background.setVisibility(View.INVISIBLE);
            arrows[0].setVisibility(View.INVISIBLE);
            arrows[1].setVisibility(View.INVISIBLE);
            arrows[2].setVisibility(View.INVISIBLE);
            arrows[3].setVisibility(View.INVISIBLE);
            congratsText.setVisibility(View.VISIBLE);
            nextLevelButton.setVisibility(View.VISIBLE);
            miniMap.setVisibility(View.INVISIBLE);
            background.setBackgroundResource(R.drawable.full);

        } else
            showMaze();
    }

    void showMaze() {
        arrows[0].setVisibility(View.INVISIBLE);
        arrows[1].setVisibility(View.INVISIBLE);
        arrows[2].setVisibility(View.INVISIBLE);
        arrows[3].setVisibility(View.INVISIBLE);
        String place = currentMaze.getCurrentSquare().toString();
        Square currentSquare = currentMaze.getCurrentSquare();
        for (int i = 0; i < mazescreen.length; i++) {
            if (mazescreen[i].equals(place)) {
                background.setBackgroundResource(mazeImage[i]);
                if (!currentSquare.left()) {
                    arrows[0].setVisibility(View.VISIBLE);
                }
                if (!currentSquare.top()) {
                    arrows[1].setVisibility(View.VISIBLE);
                }
                if (!currentSquare.right()) {
                    arrows[2].setVisibility(View.VISIBLE);
                }
                if (!currentSquare.bottom()) {
                    arrows[3].setVisibility(View.VISIBLE);
                }
            }
        }

    }

    void nextLevel() {

        currentLevel++;
        if (currentLevel >= mazeList.mazes.length) {
            gameOver();
        } else {
            currentMaze = mazeList.getMaze(currentLevel);
            currentMaze.makeSquares(parseMaze(currentMaze));
            congratsText.setVisibility(View.INVISIBLE);
            nextLevelButton.setVisibility(View.INVISIBLE);
            //background.setVisibility(View.VISIBLE);
            if(showStart) {
                miniMap.setImageResource(mapss[currentLevel]);
            }
            else{
                miniMap.setImageResource(maps[currentLevel]);
            }
            miniMap.setVisibility(View.VISIBLE);
            showMaze();
            int levelInt =currentLevel+1;
            String level=""+levelInt;
            this.levelText.setText(level);
        }
    }

    void gameOver() {
        congratsText.setText("You Win!");
        nextLevelButton.setText("Press to restart");
        currentLevel = -1;
    }

    boolean atEnd() {
        int[] curr = currentMaze.currentSquareLocation;

        int[] end = currentMaze.end;
        return curr[0] == end[0] && curr[1] == end[1];
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_show_start:
                this.showStart=true;
                break;
            case R.id.action_hide_start:
                this.showStart=false;
                break;
            case R.id.maze_0:
                this.currentLevel=-1;
                nextLevel();
                return true;
            case R.id.maze_1:
                this.currentLevel=0;
                nextLevel();
                return true;
            case R.id.maze_2:
                this.currentLevel=1;
                nextLevel();
                return true;
            case R.id.maze_3:
                this.currentLevel=2;
                nextLevel();
                return true;
            case R.id.maze_4:
                this.currentLevel=3;
                nextLevel();
                return true;
            case R.id.maze_5:
                this.currentLevel=4;
                nextLevel();
                return true;
            case R.id.maze_6:
                this.currentLevel=5;
                nextLevel();
                return true;
            case R.id.maze_7:
                this.currentLevel=6;
                nextLevel();
                return true;
            case R.id.maze_8:
                this.currentLevel=7;
                break;
            case R.id.maze_9:
                this.currentLevel=8;
                nextLevel();
                return true;
            case R.id.maze_10:
                this.currentLevel=9;
                nextLevel();
                return true;
            case R.id.maze_11:
                this.currentLevel=10;
                break;
            case R.id.maze_12:
                this.currentLevel=11;
                nextLevel();
                return true;
            case R.id.maze_13:
                this.currentLevel=12;
                nextLevel();
                return true;
            case R.id.maze_14:
                this.currentLevel=13;
                nextLevel();
                return true;
            case R.id.maze_15:
                this.currentLevel=14;
                break;
            case R.id.maze_16:
                this.currentLevel=15;
                nextLevel();
                return true;
            case R.id.maze_17:
                this.currentLevel=16;
                nextLevel();
                return true;
            case R.id.maze_18:
                this.currentLevel=17;
                nextLevel();
                return true;
            case R.id.maze_19:
                this.currentLevel=18;
                nextLevel();
                return true;
            default:
                return true;

        }
        if(showStart) {
            miniMap.setImageResource(mapss[currentLevel]);
        }
        else{
            miniMap.setImageResource(maps[currentLevel]);
        }
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.maze_0:
                this.currentLevel=-1;
                break;
            case R.id.maze_1:
                this.currentLevel=0;
                break;
            case R.id.maze_2:
                this.currentLevel=1;
                break;
            case R.id.maze_3:
                this.currentLevel=2;
                break;
            case R.id.maze_4:
                this.currentLevel=3;
                break;
            case R.id.maze_5:
                this.currentLevel=4;
                break;
            case R.id.maze_6:
                this.currentLevel=5;
                break;
            case R.id.maze_7:
                this.currentLevel=6;
                break;
            case R.id.maze_8:
                this.currentLevel=7;
                break;
            case R.id.maze_9:
                this.currentLevel=8;
                break;
            case R.id.maze_10:
                this.currentLevel=9;
                break;
            case R.id.maze_11:
                this.currentLevel=10;
                break;
            case R.id.maze_12:
                this.currentLevel=11;
                break;
            case R.id.maze_13:
                this.currentLevel=12;
                break;
            case R.id.maze_14:
                this.currentLevel=13;
                break;
            case R.id.maze_15:
                this.currentLevel=14;
                break;
            case R.id.maze_16:
                this.currentLevel=15;
                break;
            case R.id.maze_17:
                this.currentLevel=16;
                break;
            case R.id.maze_18:
                this.currentLevel=17;
                break;
            case R.id.maze_19:
                this.currentLevel=18;
                break;
            default:
                return true;

        }
        nextLevel();
        return true;
    }
    public void saveFile(){
        String saveData= String.valueOf(this.currentLevel);
        try {
            FileOutputStream fos= openFileOutput("save_file", Context.MODE_PRIVATE);
            fos.write(saveData.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readSaveData(){

        int i = 0;
        String result = "";

        File file = this.getBaseContext().getFileStreamPath("save_file");
        if (file.exists()) {
            try {
                FileInputStream fos = openFileInput("save_file");

                while ((i = fos.read()) != -1) {
                    Character c = (char) i;
                    result += c;
                }
                fos.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            if(result!="")
                this.currentLevel= Integer.parseInt(result);
        }



    }
    @Override
    public void onDestroy(){
        this.saveFile();
        super.onDestroy();
    }
    @Override
    public void onStop(){
        this.saveFile();
        super.onStop();
    }
}
