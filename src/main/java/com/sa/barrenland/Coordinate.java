package com.sa.barrenland;

import java.util.Objects;

/**
 * @author shubhaviarya
 *
 */

public class Coordinate{
    private Integer x;
    private Integer y;
    private boolean isBarren;
    private boolean visited = false;
    private String text = new String();

    public Coordinate(){

    }

    public Coordinate(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Integer getX(){
        return x;
    }

    public void setX(Integer x){
        this.x = x;
    }

    public Integer getY(){
        return y;
    }

    public void setY(Integer y){
        this.y = y;
    }

    public boolean isIsBarren(){
        return isBarren;
    }

    public void setIsBarren(boolean isBarren){
        this.isBarren = isBarren;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited){

        this.visited = visited;
        if(visited){
            text="*";
        }
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

}