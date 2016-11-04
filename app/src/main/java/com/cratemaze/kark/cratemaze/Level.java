package com.cratemaze.kark.cratemaze;

import android.service.quicksettings.Tile;

enum TILES
{
    EMPTY(0),
    WALL(1),
    BOX(2),
    PLAYER(3),
    EXIT(4);
    private final int val;
    TILES(int v) {val = v;}
    public int getVal() {return val;}
}

public class Level
{
    private int xSize = 9;
    private int ySize = 9;
    public int level[][] = new int[xSize][ySize];
    private int playerX;
    private int playerY;

    public Level() {}

    public boolean LoadLevel(int id)
    {
        boolean success = false;
        String levelData;

        //TODO: loads level data from a database

        //temporary test-levelData:
        levelData = "111141111100020001100000001100000001100020001100000001100000001100030001111111111";
        success = true;

        //fills level Array from levelData string.
        for (int i = 0; i < xSize; i++)
        {
            for (int n = 0; n < ySize; n++)
            {
                int pos = (xSize * i) + n;
                level[i][n] = Character.getNumericValue(levelData.charAt(pos));

                if(Character.getNumericValue(levelData.charAt(pos)) == TILES.PLAYER.getVal())
                {
                    playerX = i;
                    playerY = n;
                }
            }
        }

        return success;
    }

    public int GetTile(int x, int y)
    {
        int tile = 0;

        //TODO: Add GetTile() body

        return tile;
    }

    public void UpdateLevel(int x, int y, int tile)
    {
        //TODO: Add UpdateLevel() body
    }

    public void SetPlayer(int x, int y)
    {
        level[playerX][playerY] = TILES.EMPTY.getVal();
        level[x][y] = TILES.PLAYER.getVal();

        playerX = x;
        playerY = y;
    }

    public int getPlayerX()
    {
        return playerX;
    }

    public  int getPlayerY()
    {
        return  playerY;
    }
}
