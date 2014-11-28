package thaliada;

import au.com.bytecode.opencsv.*;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Hywel
 */
public class test {
    int[][] getMatrix(String filePath)
    {
        int mtxLength = 0;
        ArrayList<Integer> values = new ArrayList<>();
        
        try
        {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            
            String[] nextLine;
            int lineCtr = 0;
            
            while ((nextLine = reader.readNext()) != null)
            {
                if(lineCtr == 0)
                {
                    //Obtain row length in prep for creating matrix size
                    mtxLength = nextLine.length;
                    System.out.println("rowLength set to: " + mtxLength);
                }
                else
                {
                    for(int i = 0; i < mtxLength; i++)
                    {
                        //System.out.println("Value added to list: " + nextLine[i] + " from line: " + lineCtr);
                        values.add(Integer.parseInt(nextLine[i]));
                    }
                }
                lineCtr++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        int[][] matrix = new int[mtxLength][mtxLength];
        for(int i = 0; i < mtxLength; i++)
        {
            for(int j = 0; j < mtxLength; j++)
            {
                int currVal = values.remove(0);
                matrix[i][j] = currVal;
                //System.out.println("Value at [" + i + "," + j + "] is : " + currVal);
            }
        }
        
        return matrix;
    }
    
    
    
    public static void main(String[] args)
    {
         test a = new test();
         //a.getMatrix("C:\\Users\\Hywel\\Desktop\\tsp.csv");
    }
}
