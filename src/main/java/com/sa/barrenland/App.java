package com.sa.barrenland;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
/**
 * @author shubhaviarya
 *
 */
public class App 
{
    private static final int TILE_SIZE = 1;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private static final int X_TILES = WIDTH/TILE_SIZE;
    private static final int Y_TILES = HEIGHT/TILE_SIZE;

    private static Coordinate[][] grid = new Coordinate[X_TILES][Y_TILES];
    public static void main( String[] args )
    {
        String[] STDIN ={"0 292 399 307"};

        String STDOUT = findFertile(STDIN);
        System.out.println(STDOUT);
    }

    public static String findFertile(String[] recCornersArray){
        List<Integer> fertileLand = new ArrayList<>();

        List<Integer[]> barrenLandEndPoints = getBarrenLandCoordinates(recCornersArray);

        List<Coordinate> totalBarrenLand = new ArrayList<>();
        //fill totalbarrenland list
        for (Integer[] rectangle : barrenLandEndPoints){
            totalBarrenLand.addAll(findTotalBarrenLand(rectangle));
        }

        //loop through bounds of the grid filling a multidimensional array with each of the
        // coordinate points in it
        for (int y=0; y < Y_TILES; y++)
        {
            for (int x=0; x < X_TILES; x++)
            {
                Coordinate mycoor = new Coordinate(x,y);

                for (Coordinate c : totalBarrenLand)
                {
                    if(c.getX() == x && c.getY()== y){
                        mycoor.setIsBarren(true);
                        mycoor.setVisited(true);
                        break;
                    }
                    else{
                        mycoor.setIsBarren(false);
                    }
                }
                grid[x][y]=mycoor;
            }
        }

        fertileLand = checkForUnvisitedAreasAndCountFertileLand(fertileLand, 0, 0);

        Collections.sort(fertileLand);

        String STDOUT = "";

        if (!fertileLand.isEmpty()){
            for (Integer land: fertileLand)
            {
                STDOUT += land.toString() + " ";
            }
        }else{
            STDOUT = "No fertile land available";
        }

        return STDOUT;
    }

    private static List<Integer[]> getBarrenLandCoordinates(String[] recCornersArray){
        List<Integer[]> recPoints = new ArrayList<>();
        //for each rectangle coordinates, split into array of strings, convert strings to ints,
        //then add array of ints to list(of arrays of rectangles)
        for (int h=0; h<recCornersArray.length; h++)
        {
            String[] strRectangleCorner = recCornersArray[h].split(" ");
            Integer[] intRectangleCorner = new Integer[strRectangleCorner.length];
            for (int i=0; i<strRectangleCorner.length; i++)
            {

                intRectangleCorner[i] = Integer.parseInt(strRectangleCorner[i]);
            }
            recPoints.add(intRectangleCorner);
        }

        return recPoints;
    }

    private static List<Coordinate> findTotalBarrenLand(Integer[] bounds){
        List<Coordinate> allBarrenLandCoordinates = new ArrayList<>();

        //loop through end points and create new coordinate for each coordinate within rectangle
        //endpoints and then add to allBarrenLandCoordinates list
        for (int i=bounds[0]; i<=bounds[2]; i++)
        {
            for (int j=bounds[1]; j<=bounds[3]; j++)
            {
                Coordinate coordinate = new Coordinate (i,j);
                allBarrenLandCoordinates.add(coordinate);
            }
        }
        return allBarrenLandCoordinates;
    }

    /*check through grid, find first unvisited point, flood fill the fertile area directly
    connected to that point and return the total area
     */
    private static List<Integer> checkForUnvisitedAreasAndCountFertileLand(
            List<Integer> land, int xVal, int yVal){
        for (int y=yVal; y<Y_TILES; y++)
        {
            for (int x=xVal; x<X_TILES; x++)
            {
                Coordinate tile = grid[x][y];
                if (!tile.isVisited())
                {
                    int totalFertileArea = floodFill(grid, x, y);
                    land.add(totalFertileArea);
                    checkForUnvisitedAreasAndCountFertileLand(land, x, y);
                }
            }
        }
        return land;
    }

    /* visit all coordinates in a fertile land space and find the area */
    private static int floodFill(Coordinate[][] grid, int x, int y){
        int count = 0; //count of grid squares being filled/ visited
        Stack<Coordinate> stack = new Stack<Coordinate>();
        stack.push(new Coordinate(x,y));

        while (!stack.isEmpty())
        {
            Coordinate a = stack.pop();

            //If coordinate 'a' is unvisited, visit it, increase count by 1, and
            // add neightbors to the stack
            if(isCoordinateUnvisited(grid, a))
            {
                count += 1;
                if(a.getY() - 1 >=0 && !grid[a.getX()][a.getY() - 1].isVisited() ){
                    stack.push(new Coordinate(a.getX(), a.getY() - 1));
                }
                if (a.getY()+1 <Y_TILES && !grid[a.getX()][a.getY() + 1].isVisited()){
                    stack.push(new Coordinate(a.getX(), a.getY()+1));
                }
                if(a.getX()-1 >=0 && !grid[a.getX() -1 ][a.getY()].isVisited() ){
                    stack.push(new Coordinate(a.getX() -1, a.getY()));
                }
                if (a.getX()+1 < X_TILES && !grid[a.getX() +1 ][a.getY()].isVisited() ){
                    stack.push(new Coordinate(a.getX()+1, a.getY()));
                }

            }
        }
        return count;
    }

    private static boolean isCoordinateUnvisited(Coordinate[][] grid, Coordinate c)
    {
        //check c is not outside bounds of grid
        if (c.getX() < 0|| c.getY() <0 || c.getX() >= X_TILES|| c.getY() >= Y_TILES)
        {
            return false;
        }

        Coordinate coorToBeChecked = grid[c.getX()][c.getY()];

        if (coorToBeChecked.isVisited()){
            return false;
        }

        coorToBeChecked.setVisited(true);
        return true;
    }


}
