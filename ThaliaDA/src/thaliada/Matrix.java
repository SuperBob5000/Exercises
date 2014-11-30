package thaliada;

import au.com.bytecode.opencsv.*;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Hywel
 */
public class Matrix {
    
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
                        System.out.println("Value added to list: " + nextLine[i] + " from line: " + lineCtr);
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
        
        int[][] mat = new int[mtxLength][mtxLength];
        for(int i = 0; i < mtxLength; i++)
        {
            for(int j = 0; j < mtxLength; j++)
            {
                int currVal = values.remove(0);
                mat[i][j] = currVal;
                System.out.println("Value at [" + i + "][" + j + "] is : " + currVal);
            }
        }
        
        return mat;
    }
    
    int[][] transposeMat(int[][] matrix)
    {
        int[][] asyMat = new int[matrix[0].length][matrix[0].length];
        for(int i = 0; i < matrix[0].length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                asyMat[i][j] = matrix[j][i];
            }
        }
        
        return asyMat;
    }
    
    int[][] asymToSym(int[][] matrix, int[][] transMat)
    {
        int n = 2*matrix[0].length;
        int[][] symMat = new int[n][n];
        
        for(int i = 0; i < transMat[0].length; i++)
        {
            for(int j = 0; j < transMat[0].length; j++)
            {
                symMat[i][j+transMat[0].length] = transMat[i][j];
                symMat[i+transMat[0].length][j] = matrix[i][j];
            }
        }
        
        return symMat;
    }
    
    public static void main(String[] args)
    {
        Matrix a = new Matrix();
        
        int[][] mat = a.getMatrix("C:\\Users\\Hywel\\Desktop\\tsp.csv");
        int[][] asyMat = a.transposeMat(mat);
        int[][] symMat = a.asymToSym(mat, asyMat);
//        int N = mat[0].length;
//        
//        for(int i = 0; i < N; i++)
//        {
//           for(int j = 0; j < N; j++)
//           {
//               System.out.println("Matrix value at [" + i + "][" + j + "] is : " + mat[i][j]);
//           }
//        }
//        
//        for(int i = 0; i < asyMat[0].length; i++)
//        {
//           for(int j = 0; j < asyMat[0].length; j++)
//           {
//               System.out.println("asyMat value at [" + i + "][" + j + "] is : " + asyMat[i][j]);
//           }
//        }
        
//        for(int i = 0; i < symMat[0].length; i++)
//        {
//            for(int j = 0; j < symMat[0].length; j++)
//            {
//                System.out.println("symMat value at [" + i + "][" + j + "] is : " + symMat[i][j]);
//            }
//        }
    }
}
