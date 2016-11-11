package com.javarush.test.level23.lesson13.big01;

import java.util.ArrayList;

/**
 * Created by HMF on 16.02.2016.
 */
public class Snake
{
    private ArrayList<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake (int X, int Y){
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(X,Y));
        isAlive = true;
    }

    public ArrayList<SnakeSection> getSections()
    {
        return sections;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public SnakeDirection getDirection()
    {
        return direction;
    }
    public int getX(){
        return sections.get(0).getX();
    }
    public int getY(){
        return sections.get(0).getY();
    }

    public void setDirection(SnakeDirection direction)
    {
        this.direction = direction;
    }
    public void move(){

    }
}
