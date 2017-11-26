package Controller;

import Model.Player;
import Model.Sprite;
import Model.TileMap;
import Model.MapObject;
import Model.Enemy;

import java.util.ArrayList;

public class CollisionManager {

    MapObject mapObject;
    private Player p;

    private final int SCREEN_WIDTH  = 1280;
    private final int SCREEN_HEIGHT = 960;

    private double x ;
    private double y ;
    private double dx ;
    private double dy ;

    private int currCol ;
    private int currRow ;
    private TileMap tileMap;
    private int tileSize;

    private int cwidth;
    private int cheight;

    private double xdest;
    private double ydest;

    private int tl = 0;
    private int tr = 0;
    private int bl = 0;
    private int br = 0;

    private boolean falling;
    //yenimergeolmadi
    public CollisionManager(MapObject mapObject){

        this.mapObject = mapObject;
        p = mapObject.getPlayer();
        
        x = p.getx();
        y = p.gety();
        dx = p.getdx();
        dy = p.getdy();
        
        currCol = p.getCurrCol();
        currRow = p.getCurrRow();
        tileMap = p.getTileMap();
        tileSize = p.getTileSize();
        
        cwidth = p.getCWidth();
        cheight = p.getCHeight();
        
        falling = p.getFalling();
    }

   /* public void calculateCorners(double x, double y) {

        int leftTile = (int) (x - cwidth / 2) / tileSize;
        int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cheight / 2 ) / tileSize;
        int bottomTile = (int) (y + cheight/ 2 - 1 ) / tileSize;

        tl = tileMap.getType(topTile, leftTile);
        tr = tileMap.getType(topTile, rightTile);
        bl = tileMap.getType(bottomTile, leftTile);
        br = tileMap.getType(bottomTile, rightTile);
       }
    */
    void handleAllCollisions(){
        try{
            handlePlayerCollision(mapObject.getPlayer());
            handleAllEnemyCollisions(mapObject.getEnemies());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    void handleAllEnemyCollisions(ArrayList<Enemy> enemies){
        for(Enemy e : enemies){
            handleEnemyCollision(e);
        }
    }

    void handleEnemyCollision(Enemy e){
        double xtemp;
        double ytemp;

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;
    }

    void handlePlayerCollision(Player p) {

        double xtemp;
        double ytemp;

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

       // calculateCorners( p.getx(), ydest);
        if (dy < 0) {
            //if (topLeft|| topRight) {
            if(tl == 1 || tl == 2 || tr == 1 || tr == 2){
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
            }
            else if (tl == 3 || tl == 5 || tr == 3 || tr == 5 ){
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
                p.die();

            }
            else {
                ytemp = ytemp + dy;
            }
        }
        if (dy > 0) {
            //if (bottomLeft || bottomRight) {
            if (bl == 1 || bl == 2 || br == 1 || br == 2 ) {
                dy = 0;
                falling = false;
                ytemp = (currRow + 1) * tileSize - cheight / 2;
            }
            else if (bl == 3 || bl == 5 || br == 3 || br == 5) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
                p.die();
            }
            else{
                ytemp = ytemp + dy;
            }
        }
      //  calculateCorners(xdest, y);
        if (dx < 0) // going left
        {
            //if (topLeft || bottomLeft) {
            if (tl == 1 || tl == 2 || bl == 1 || bl == 2 ) {
                dx = 0;
                xtemp = currCol * tileSize + cwidth / 2;
            }
            else if (tl == 3 || tl == 5 || bl == 3 || bl == 5) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
                p.die();
            }
            else {
                xtemp = xtemp + dx;
            }
        }
        if (dx > 0) {
            //if (topRight || bottomRight) {
            if (tr == 1 || tr == 2 || br == 1 || br ==2 ) {
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cwidth / 2;
            }
            else if ((tr == 3 || tr == 5 )|| br == 3 || br == 5 ) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
                p.die();
            }
            else {
                xtemp = xtemp + dx;
            }
        }
        if (!falling) {
           // calculateCorners(x, ydest + 1);
            if ( !(bl == 1 || bl == 2 || br == 1 || br == 2 ) ) {
                falling = true;
            }
        }
    }
}