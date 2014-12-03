/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaliada;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hywel
 */
public class TwoOpt {
    
    private ArrayList<Integer> Tour_Nodes = new ArrayList<>();
    public ArrayList<Integer> Tour_Path = new ArrayList<>();
    public ArrayList<Integer> bestTour = new ArrayList<>();
   
    public int currWeight;
    public int[][] Mat;
    
    TwoOpt(int[][] mat)
    {
        int N = mat[0].length;
        
        Mat = new int[N][N];
        
        for(int i = 0; i < N; i++)
        {
            Tour_Nodes.add(i);
            
            for(int j = 0; j < N; j++)
            {
                Mat[i][j] = mat[i][j];
            }
        }
    }
    
    void randomTour()
    {
        ArrayList<Integer> tourNodes = Tour_Nodes;
        Random r = new Random();
        int currSize = 0;
        int nextNodeIndex = 0; 
        
        Tour_Path.add(Tour_Nodes.remove(0));
        
        while(!Tour_Nodes.isEmpty())
        {
            currSize = Tour_Nodes.size();
            nextNodeIndex = r.nextInt(currSize);
            Tour_Path.add(tourNodes.remove(nextNodeIndex));
        }
        
        Tour_Path.add(Tour_Path.get(0));
    }
    
    void TwoOpt(ArrayList<Integer> currTour)
    {
        ArrayList<Integer> newTour = new ArrayList<>();
        
        int newWeight;
        currWeight = calculateWeight(currTour);
        for(int i = 1; i < currTour.size() - 2; i++)
        {
            for(int j = i + 1; j < currTour.size() - 1; j++)
            {
                newTour = TwoOptSwap(currTour, i, j);
                newWeight = calculateWeight(newTour);
                if(newWeight < currWeight)
                {
                    bestTour = newTour;
                    TwoOpt(bestTour);
                }
            }
        }
    }
    
    int calculateWeight(ArrayList<Integer> currTour)
    {
        int n = currTour.size();
        int j = 0;
        int sumOfWeights = 0;
        
        for(int i = 0; i < n - 1; i++)
        {
            j = i + 1; 
            sumOfWeights = sumOfWeights + Mat[currTour.get(i)][currTour.get(i)] + Mat[currTour.get(i)][currTour.get(j)];
        }
        
        return sumOfWeights;
    }
    
    ArrayList<Integer> TwoOptSwap(ArrayList<Integer> currTour, int i, int k)
    {
        ArrayList<Integer> newTour = new ArrayList<>();
        
        for(int a = 0; a < i; a++)
        {
            newTour.add(currTour.get(a));
        }
        
        for(int b = k; b >= i; b--)
        {
            newTour.add(currTour.get(b));
        }
        
        for(int c = k+1; c < currTour.size(); c++)
        {
            newTour.add(currTour.get(c));
        }
        
        return newTour;
    }
    
}
