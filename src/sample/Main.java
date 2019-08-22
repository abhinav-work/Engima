package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Main extends Application
{
    private char tempStorage;
    private String result = new String();
    private String entered = new String();
    private boolean press=true;
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout_app.fxml"));
        Pane rootNode = loader.load();

        controller = loader.getController();
        controller.structure();
        Scene scene = new Scene(rootNode,1910,1000);
        scene.setOnKeyPressed(event ->
        {
                press = false;
                dataEntry(event);

        });
        scene.setOnKeyReleased(event ->
        {
                press = true;
                dataEntry(event);

        });

        primaryStage.setTitle("Virtual Enigma");
        primaryStage.setScene(scene);
        primaryStage.show();
        /*String string;
        Scanner scanner = new Scanner(System.in);
        string = scanner.next();
        for (int i=0; i<string.length() ; i++)
        {
            System.out.print("'");
            System.out.print(string.charAt(i) + "' ,");

        }*/
    }


    private void dataEntry(javafx.scene.input.KeyEvent event)
    {
        boolean value=false;
        if (press==true)
        {
            for (int i=0 ; i<26 ; i++)
            {
                if (tempStorage==controller.lampArray[i])
                {
                    System.out.println("Entered the Exit Color match");
                    controller.defaultColor(i);
                    rotorCheckList();
                    break;
                }
            }
        }
        if(press==false)
        {
            tempStorage = event.getText().toUpperCase().charAt(0);
            entered+=tempStorage;
            for (int i=0 ; i<26 ; i++)
            {
                if(controller.alphaArray[i]==tempStorage)
                {
                    value = true;
                    break;
                }
            }
            if (value==true)
            {
                System.out.println();
                System.out.println("Entered Character :- " + tempStorage);
                plugCheck();
                for (int r = 0; r < 3; r++)
                {
                    tempStorage = controller.rotorWorking(r, tempStorage);
                }
                tempStorage = controller.deflector(tempStorage);
                for (int r = 2; r >= 0; r--)
                {
                    tempStorage = controller.rotorEnworking(r, tempStorage);
                }
                controller.countR++;

                System.out.println("Rotor Turning " + controller.countR);
                //System.out.print(tempStorage);
                if (controller.countR % 26 == 0)
                {
                    System.out.println("Entering the Middle rotor turn");
                    controller.countRotor[1] = controller.countR / 26;
                    System.out.print("Count Rotor1 : " + controller.countRotor[1]);
                    System.out.println();
                    //countR = 0;
                    if (controller.countRotor[1] % 26 == 0)
                    {
                        System.out.println("Entering the Last rotor turn");
                        //countRotor[2]++;
                        controller.reverseEngineer(2);
                        controller.resetRotor(1, controller.showArray[1]-1);
                        controller.countRotor[1] = 0;
                    }
                    else
                        {
                        controller.reverseEngineer(1);
                        }
                }
                controller.reverseEngineer(0);
                System.out.println("Out of the other");

                plugCheck();
                System.out.println(tempStorage);
                result+=tempStorage;
                System.out.println("Entered String is : " + entered);
                System.out.println("The Final String is : " + result);
                for (int i = 0; i < 26; i++)
                {
                    if (tempStorage == controller.lampArray[i])
                    {
                        System.out.println("Entered the Color change match");
                        controller.colorChange(i);
                        break;
                    }
                }
            }
        }
    }

    private void rotorCheckList() {

        for (int j = 0; j < 3; j++)
        {
            System.out.print("Rotor " + j + " :-");
            for (int i = 0; i < 26; i++)
            {
                System.out.print(controller.rotor[j][i]);
            }
            System.out.println();
        }
    }
    private void plugCheck()
    {
        int flag = 0;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (tempStorage == controller.plugArray[i][j])
                {
                    flag++;
                    if (i == 0)
                    {
                        tempStorage = controller.plugArray[1][j];
                        System.out.println("Working 1");
                        break;
                    }
                    else if (i == 1)
                    {
                        tempStorage = controller.plugArray[0][j];
                        System.out.println("Working 2");
                        break;
                    }
                }
            }
            if (flag==1)
            {
                flag=0;
                break;
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }


}
