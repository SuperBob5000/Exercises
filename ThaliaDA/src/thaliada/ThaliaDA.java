/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaliada;

import java.awt.Desktop;
//import java.awt.Insets;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Hywel
 */
public class ThaliaDA extends Application {
    private Desktop desktop = Desktop.getDesktop();
    private String filePath;
    private String msg; 
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Thalia DA TSP");
 
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Find CSV");
        final Button run = new Button("Run");
        Label fileLabel = new Label();
        Label msgLabel = new Label();
        
        openButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    filePath = file.getAbsolutePath();
                    fileLabel.setText(filePath);
                    fileLabel.setVisible(true);
                    fileLabel.setLayoutX(0);
                    fileLabel.setLayoutY(25);
                    
                }
            }
        });
        
        run.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent e) {
                runTSP(filePath);
                msgLabel.setText(msg);
                msgLabel.setVisible(true);
                
            }
        });
        
       
     
        final GridPane inputGridPane = new GridPane();
        
        GridPane.setConstraints(openButton, 0, 1);
        GridPane.setConstraints(run, 1, 1);
        GridPane.setConstraints(fileLabel, 0, 2);
        GridPane.setConstraints(msgLabel, 0, 3);
        
        
        inputGridPane.setHgap(10);
        inputGridPane.setVgap(10);
        inputGridPane.getChildren().addAll(openButton, run, fileLabel, msgLabel);
        
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(100, 100, 100, 100));

        stage.setScene(new Scene(rootGroup));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    void runTSP(String csvFile)
    {
        Matrix m = new Matrix();
        if(csvFile != null && csvFile.endsWith(".csv"))
        {
            msg = "Succesful! Program Running...";
            
            System.out.println("Succesful! Program Running...");
            int[][] mat = m.getMatrix(csvFile);
            
            TwoOpt twoOpt = new TwoOpt(mat);
            twoOpt.randomTour();
            msg = "Initial tour: "; 
            System.out.println("Initial tour: ");
                    
            for(int i = 0; i < twoOpt.Tour_Path.size(); i++)
            {
                msg = msg + twoOpt.Tour_Path.get(i) + " -> ";
                
                if(i == twoOpt.Tour_Path.size() - 1)
                {
                    msg = msg + " Weight: " + twoOpt.calculateWeight(twoOpt.Tour_Path);
                    
                }
            }
            
            System.out.println(msg);
            twoOpt.TwoOpt(twoOpt.Tour_Path);
            msg = "Final tour: ";
            
            for(int i = 0; i < twoOpt.bestTour.size(); i++)
            {
                msg = msg + twoOpt.bestTour.get(i) + " -> ";
                
                if(i == twoOpt.bestTour.size() - 1)
                {
                    msg = msg + " Weight: " + twoOpt.calculateWeight(twoOpt.bestTour);
                }
            }
            
            System.out.println(msg);
            
            
        }
        else
        {
            System.out.println("File incompatible, please use CSV file.");
            msg = "File incompatible, please use CSV file.";
        }
    }
    
}
