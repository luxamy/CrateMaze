package com.cratemaze.kark.cratemaze;

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

    public boolean LoadLevel( String levelData)
    {
        boolean success = false;

        if(levelData.length() == 81) success = true;

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
        int ret = -1;
        if(x >= 0 && x <= 8 && y >= 0 && y <= 8)
        {
            ret = level[x][y];
        }
        return ret;
    }

    public void UpdateLevel(int x, int y, int tile)
    {
        level[x][y] = tile;
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
