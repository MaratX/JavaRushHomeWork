package com.javarush.test.level22.lesson18.big01;

/**
 * Created by HMF on 30.10.2015.
 */
public class Figure
{
    private int x;
    private int y;

    private int matrix[][] = new int[3][3];


    public void left(){}
    public void right(){}
    public void down(){}
    public void up(){}
    public void downMaximum(){}
    public void rotate(){}
    public boolean isCurrentPositionAvailable(){
        return true;
    }
    public void landed(){}


    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
    }

    public int getX()
    {

        return x;
    }

    public int getY()
    {
        return y;
    }

    public Figure(int x, int y, int[][] matrix)
    {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }
}
