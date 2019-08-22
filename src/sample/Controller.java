package sample;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.transform.Rotate;

import javax.swing.*;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Math.E;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Controller implements Initializable
{
	@FXML
	private Pane myPane;
	@FXML
	private Pane rootPane;
	@FXML
	private Pane upperPane;
	@FXML
	private Button up1;
	@FXML
	private Button up2;
	@FXML
	private Button up3;
	@FXML
	private Button up4;
	@FXML
	private Button up5;
	@FXML
	private Button up6;
	@FXML
	private Button down1;
	@FXML
	private Button down2;
	@FXML
	private Button down3;
	@FXML
	private Button down4;
	@FXML
	private Button down5;
	@FXML
	private Button down6;
	@FXML
	private Button paneBt;
	@FXML
	private Pane bottomPane;
	@FXML
	private ImageView plugImage;
	@FXML
	private Label dashLabel;

	public static char[] reflector =
			{'Y', 'R', 'U', 'H', 'Q', 'S', 'L', 'D', 'P', 'X', 'N', 'G', 'O', 'K', 'M', 'I', 'E', 'B', 'F', 'Z', 'C', 'W', 'V', 'J', 'A', 'T'};

	public static char[][] rotor = {
			{'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J'},
			{'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E'},
			{'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O'}
	};
		  //{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}

	public static char[][] rotorCopy = {
			{'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J'},
			{'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E'},
			{'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O'},
			{'E', 'S', 'O', 'V', 'P', 'Z', 'J', 'A', 'Y', 'Q', 'U', 'I', 'R', 'H', 'X', 'L', 'N', 'F', 'T', 'G', 'K', 'D', 'C', 'M', 'W', 'B'},
			{'V', 'Z', 'B', 'R', 'G', 'I', 'T', 'Y', 'U', 'P', 'S', 'D', 'N', 'H', 'L', 'X', 'A', 'W', 'M', 'J', 'Q', 'O', 'F', 'E', 'C', 'K'},
			{'J', 'P', 'G', 'V', 'O', 'U', 'M', 'F', 'Y', 'Q', 'B', 'E', 'N', 'H', 'Z', 'R', 'D', 'K', 'A', 'S', 'X', 'L', 'I', 'C', 'T', 'W'},
			{'N', 'Z', 'J', 'H', 'G', 'R', 'C', 'X', 'M', 'Y', 'S', 'W', 'B', 'O', 'U', 'F', 'A', 'I', 'V', 'L', 'P', 'E', 'K', 'Q', 'D', 'T'},
			{'F', 'K', 'Q', 'H', 'T', 'L', 'X', 'O', 'C', 'B', 'J', 'S', 'P', 'D', 'Z', 'R', 'A', 'M', 'E', 'W', 'N', 'I', 'U', 'Y', 'G', 'V'}
	};
	private String baseColor = "#b3b3cc";
	private String strokeColor = "BLACK";
	private String changeColor = "#ffff00";
	private String spotLight = "#c2c2d6";
	private String keyColor  = "WHITE";
	private String rotorColor = "BLACK";

	//private double startX, startY;
	//private double endX, endY;
	private String plugBackColor = "#ffff4d";
	private String plugBase = "#000000";
	private int keySize = 50;
	private int plugKeySize = 20;
	private int RADIUS = 50;
	private int COUNT = 0;
	private int value = 1;
	public int countR=0;
	public int[] countRotor = {0,0,0};
	private int alphaCount=26;
	public int[] showArray = {1,2,3};
	public char[][] plugArray = new char[2][10];
	public char[] lampArray = {'Q' ,'W' ,'E' ,'R' ,'T' ,'Y' ,'U' ,'I' ,'O' ,'P' ,'A' ,'S' ,'D' ,'F' ,'G' ,'H' ,'J' ,'K' ,'L' ,'Z' ,'X' ,'C' ,'V' ,'B' ,'N' ,'M'};
	public static final char[] alphaArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	private int[] countArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
	public boolean[] trackPlugs = new boolean[26];

	private List<Button> buttonList = new ArrayList<>();
	public List<Line> lineList = new ArrayList<>();
	private List<Circle> keyboardLayer = new ArrayList<>();
	private List<Rectangle> plugBaseLayer = new ArrayList<>();
	private List<Rectangle> plugLayer = new ArrayList<>();
	private StackPane stackPane = new StackPane();
	private StackPane stack = new StackPane();
	private StackPane bottomStack = new StackPane();
	private StackPane disStack = new StackPane();
	private int characterCount=0;
	private boolean flag = false;

	public char rotorWorking(int rotorNumber, char tempStorage)
	{
		for (int r = 0; r < alphaCount ; r++)
		{
			if (alphaArray[r] == tempStorage)// || alphaArray[1][r] == tempStorage)
			{
				tempStorage = rotor[rotorNumber][r];
				//System.out.print(" Rotor " + rotorNumber);
				//System.out.print(" " + tempStorage + "  ");
				break;
			}
		}
		return tempStorage;
	}
	public char deflector(char tempStorage)
	{
		for (int r = 0; r < alphaCount ; r++)
		{
			if (alphaArray[r] == tempStorage)// || alphaArray[1][r] == tempStorage)
			{
				tempStorage = reflector[r];
				//System.out.print(" Deflector");
				//System.out.print(" " + tempStorage + "  ");
				break;
			}
		}
		return tempStorage;
	}
	public char rotorEnworking(int rotorNumber, char tempStorage)
	{
		for (int r = 0; r < alphaCount; r++)
		{
			if (rotor[rotorNumber][r] == tempStorage)
			{
				tempStorage = alphaArray[r];
				//System.out.print(" Rotor " + rotorNumber);
				//System.out.println(" " + tempStorage + "    ");
				break;
			}
		}
		return tempStorage;
	}
	public void reverseEngineer(int rotorNumber)
	{
		if (countR == 0)
		{
			System.out.println("Start");
		}
		else
			{
			countRotor[rotorNumber]++;
			char start;
			start = rotor[rotorNumber][0];
			for (int r = 1; r < alphaCount; r++)
			{
				rotor[rotorNumber][r - 1] = rotor[rotorNumber][r];
			}
			rotor[rotorNumber][alphaCount - 1] = start;
			for (int i = 0; i < alphaCount; i++)
			{
				System.out.print(rotor[rotorNumber][i]);
			}
			System.out.println();
			}
	}
	public void forwardEngineer(int rotorNumber)
	{
		countR--;
		countRotor[rotorNumber]--;
		char stop = rotor[rotorNumber][25];
		for (int r=alphaCount-1 ; r>0 ; r--)
		{
			rotor[rotorNumber][r] = rotor[rotorNumber][r-1];
		}
		rotor[rotorNumber][0] = stop;
		for (int i = 0; i < alphaCount; i++)
		{
			System.out.print(rotor[rotorNumber][i]);
		}
		System.out.println();
	}

	public void resetRotor(int rotorNumber, int showArray)
	{
		for (int i = 0; i < alphaCount; i++)
		{
			rotor[rotorNumber][i] = rotorCopy[showArray][i];
		}
		System.out.print("  Reset Complete " + rotorNumber + "  ");
	}

	public void structure()
	{
		plugImage.setOpacity(0);
		for(int i=0 ; i<10 ; i++)
		{
			Line line = new Line();
			lineList.add(line);
		}
		Shape mainRec = new Rectangle(RADIUS*39, RADIUS*15);
		//mainRec.setFill(Color.BLACK);
		Image image = new Image("Images/Option 1.jpg");
		ImagePattern imagePattern = new ImagePattern(image);
		mainRec.setFill(imagePattern);
		myPane.getChildren().addAll(mainRec);

		mainStruture();
		rotorDisplay();
		upAndDown();
		btImage();
		plugBase();
		plugBoard();
		upperPane.getChildren().addAll(stack, disStack);
		myPane.getChildren().addAll(stackPane);
		bottomPane.getChildren().addAll(bottomStack);



		buttonAdd();
		for(Line line : lineList)
		{
			bottomPane.getChildren().addAll(line);
		};

		Rectangle buttonRect = new Rectangle(100,30);
		buttonRect.setArcHeight(40);
		buttonRect.setArcWidth(40);
		buttonRect.setStyle("-fx-border-width: 20px;");
		paneBt.setClip(buttonRect);

		paneBt.setOnMouseEntered(event ->
		{
			paneBt.setStyle("-fx-background-color: #734d26;");

		});
		paneBt.setOnMouseExited(event ->
		{
			paneBt.setStyle("-fx-background-color:  #4d3319;");
		});

		paneBt.setOnAction(event ->
		{
			TranslateTransition show = new TranslateTransition(new javafx.util.Duration(350), bottomPane);
			RotateTransition rotorShow = new RotateTransition();
			rotorShow.setNode(paneBt);
			rotorShow.setDuration(javafx.util.Duration.seconds(0.5));
			rotorShow.setAxis(Rotate.Z_AXIS);
			if (flag==false)
			{
				flag=true;

				rotorShow.setByAngle(90);
				rotorShow.play();
				plugImage.setOpacity(1);
				bottomPane.setPrefHeight(750);
				bottomStack.setVisible(true);
				show.setToY(-60);
				show.play();

				for (Line line : lineList)
				{
					line.setVisible(true);
				}
				buttonDisable(false);
			}
			else
				{
					flag = false;
					rotorShow.setByAngle(90);
					rotorShow.play();
					show.setToY(700);
					show.play();
					bottomPane.setPrefHeight(750);
					bottomStack.setVisible(false);

					for (Line line : lineList)
					{
						line.setVisible(false);
					}
					buttonDisable(true);
				}
		});

		plugReport();
		dispRotor(0,showArray[0]);
		dispRotor(1,showArray[1]);
		dispRotor(2,showArray[2]);
		buttonDisable(true);
	}

	private void buttonDisable(Boolean value)
	{
		for (Button button : buttonList)
		{
			button.setDisable(value);
			if (value)
			{
				button.setOpacity(0);
			}
			else
				button.setOpacity(1);
		}
	}

	private void buttonAdd()
	{
		buttonList.add(up1);
		buttonList.add(up2);
		buttonList.add(up3);
		buttonList.add(up4);
		buttonList.add(up5);
		buttonList.add(up6);
		buttonList.add(down1);
		buttonList.add(down2);
		buttonList.add(down3);
		buttonList.add(down4);
		buttonList.add(down5);
		buttonList.add(down6);
	}

	private void plugReport() {
		int internalCount = 0;
		int i = 0;
		for (Line line : lineList) {
			//System.out.print(lineList.indexOf(line)+1 + "  -  ");
			for (Rectangle rectangle : plugBaseLayer) {
				if (line.getStartX() - 25 == rectangle.getTranslateX() && line.getStartY() - 50 == rectangle.getTranslateY()) {
					internalCount++;
					plugArray[0][i] = lampArray[plugBaseLayer.indexOf(rectangle)];
					//System.out.print("Paired Alphhabets : " + lampArray[plugBaseLayer.indexOf(rectangle)] + " and ");
					//System.out.print(plugArray[0][i]);
				} else if (line.getEndX() - 25 == rectangle.getTranslateX() && line.getEndY() - 50 == rectangle.getTranslateY()) {
					internalCount++;
					plugArray[1][i] = lampArray[plugBaseLayer.indexOf(rectangle)];
					// System.out.print(lampArray[plugBaseLayer.indexOf(rectangle)]);
				//	System.out.print(plugArray[1][i]);
				}
				if (internalCount == 2) {
					//System.out.println("    break");
					internalCount = 0;
					//System.out.println();
					i++;
					break;
				}
			}
		}
	}

	private void plugBase()
	{
		for (int k = 0; k < 26; k++)
		{
			Rectangle rectangle = new Rectangle();
			PlugLoc plugLoc = new PlugLoc(k);
			rectangle = rectStruct(rectangle, plugLoc.standardX, plugLoc.standardY + 30, plugBase, true);
			rectangle = plugMeasurements(rectangle);
			plugBaseLayer.add(rectangle);
			bottomStack.getChildren().addAll(rectangle);
		}

	}

	private Rectangle plugMeasurements(Rectangle rectangle)
	{
		rectangle.setWidth(50);
		rectangle.setHeight(100);
		rectangle.setArcHeight(50);
		rectangle.setArcWidth(50);
		return rectangle;
	}

	private void plugBoard()
	{
		int lineNumber;
		for(int k = 0 ; k<26 ; k++)
		{
			Rectangle rectangle = new Rectangle();
			Text text = new Text();
			PlugLoc plugLoc = new PlugLoc(k);
			text = textProperty(text, plugLoc.standardX, plugLoc.standardY, plugKeySize);
			if(k<20)
			{
				rectangle = rectStruct(rectangle, plugLoc.standardX, (plugLoc.standardY + 30), plugBackColor, false);
				rectangle = plugMeasurements(rectangle);
				plugLayer.add(rectangle);

				lineNumber = lineCompRect(plugLayer.indexOf(rectangle));
				if(k%2 == 0)
				{
					lineList.get(lineNumber).setStartX(rectangle.getTranslateX()+25);
					lineList.get(lineNumber).setStartY(rectangle.getTranslateY()+50);
				}
				if (k%2 != 0)
				{
					lineList.get(lineNumber).setEndX(rectangle.getTranslateX()+25);
					lineList.get(lineNumber).setEndY(rectangle.getTranslateY()+50);
				}
				lineList.get(lineNumber).setStrokeWidth(5);
				trackPlugs[k] = true;
				lineList.get(lineNumber).setStroke(Color.PURPLE);
				lineList.get(lineNumber).setVisible(false);
				bottomStack.getChildren().addAll(rectangle,text);
			}
			else
				{
					bottomStack.getChildren().addAll(text);
					trackPlugs[k] = false;
				}
			plugMove();

		}
		bottomStack.setVisible(false);
	}

	private int lineCompRect(int index)
	{
		int lineNumber=0;
		for(int i=0 ; i<19 ; i=i+2)
		{
			if(index == i || index == i+1)
			{
				lineNumber = i/2;
			}
		}
		return lineNumber;
	}

	private void plugMove()
	{
		for(Rectangle rectangle : plugLayer)
		{
			int rectNumber, lineNumber;
			rectNumber = plugLayer.indexOf(rectangle);
			lineNumber = lineCompRect(rectNumber);
			plugMoveMachine(rectNumber, lineNumber);
		}
	}

	private void plugMoveMachine(int rectNumber, int lineNumber)
	{
		plugLayer.get(rectNumber).setOnMouseDragged(event ->
		{
			double dragX = event.getSceneX();
			double dragY = event.getSceneY();
			positionChange(rectNumber, lineNumber, dragX, dragY-350);
			snap(rectNumber, lineNumber, dragX, dragY-350);
		});
	}

	private void positionChange(int rectNumber, int lineNumber, double dragX, double dragY)
	{
		for (int i=0 ; i<26 ; i++)
		{
			if (plugLayer.get(rectNumber).getTranslateX()==plugBaseLayer.get(i).getTranslateX() && plugLayer.get(rectNumber).getTranslateY()==plugBaseLayer.get(i).getTranslateY())
			{
				trackPlugs[i] = false;
				break;
			}
		}
		plugLayer.get(rectNumber).setTranslateX(dragX);
		plugLayer.get(rectNumber).setTranslateY(dragY);
		if(rectNumber%2 == 0)
		{
			//System.out.println("Smash");
			lineList.get(lineNumber).setStartX(plugLayer.get(rectNumber).getTranslateX()+25);
			lineList.get(lineNumber).setStartY(plugLayer.get(rectNumber).getTranslateY()+50);
		}
		if (rectNumber%2 != 0)
		{
			//System.out.println("Squash");
			lineList.get(lineNumber).setEndX(plugLayer.get(rectNumber).getTranslateX()+25);
			lineList.get(lineNumber).setEndY(plugLayer.get(rectNumber).getTranslateY()+50);
		}

		//System.out.println("Rectangle " + plugLayer.indexOf(rectNumber) + "  moved");
		System.out.println("**********************");
	}

	private void snap(int rectNumber, int lineNumber, double dragX, double dragY)
	{
		double compX=0, compY=0;
		double dis=0;
		for (Rectangle rectangle : plugBaseLayer)
		{
			compX = pow((dragX-rectangle.getTranslateX()),2);
			compY = pow((dragY-rectangle.getTranslateY()),2);
			dis = sqrt(compX+compY);
			if(dis<RADIUS+20 && trackPlugs[plugBaseLayer.indexOf(rectangle)] == false)
			{
				//System.out.println( "Rectangle chosen : " + plugBaseLayer.indexOf(rectangle));
				positionChange(rectNumber, lineNumber, rectangle.getTranslateX(), rectangle.getTranslateY());
				trackPlugs[plugBaseLayer.indexOf(rectangle)] = true;
				plugReport();
			};
		}
	}


	private void btImage()
	{
		Image down = new Image("Images/Down.png");
		ImageView downView1 = upImage(down);
		up1.setGraphic(downView1);
		ImageView downView2 = upImage(down);
		up2.setGraphic(downView2);
		ImageView downView3 = upImage(down);
		up3.setGraphic(downView3);
		ImageView downView4 = upImage(down);
		up4.setGraphic(downView4);
		ImageView downView5 = upImage(down);
		up5.setGraphic(downView5);
		ImageView downView6 = upImage(down);
		up6.setGraphic(downView6);


		Image up = new Image("Images/Up.png");
		ImageView upView1 = upImage(up);
		down1.setGraphic(upView1);
		ImageView upView2 = upImage(up);
		down2.setGraphic(upView2);
		ImageView upView3 = upImage(up);
		down3.setGraphic(upView3);
		ImageView upView4 = upImage(up);
		down4.setGraphic(upView4);
		ImageView upView5 = upImage(up);
		down5.setGraphic(upView5);
		ImageView upView6 = upImage(up);
		down6.setGraphic(upView6);
	}

	private ImageView upImage(Image img)
	{
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(18);
		imgView.setFitWidth(18);
		return imgView;
	}


	private void upAndDown()
	{
		up1.setOnAction(event ->
				{
					System.out.println("Up");
					reverseEngineer(0);
					rotorDisplay();
				});
		up2.setOnAction(event ->
		{
			System.out.println("Up");
			COUNT+=26;
			reverseEngineer(1);
			rotorDisplay2();
		});
		up3.setOnAction(event ->
		{
			System.out.println("Up");
			COUNT+=676;
			reverseEngineer(2);
			rotorDisplay3();
		});
		up4.setOnAction(event ->
		{
			rotorCheck(0,1,2,false);
			resetClock();
			resetAllRotors();
			rotorDisplay();
		});
		up5.setOnAction(event ->
		{
			rotorCheck(1, 0,2,false);
			resetClock();
			resetAllRotors();
			rotorDisplay();
		});

		up6.setOnAction(event ->
		{
			rotorCheck(2, 0,1,false);
			resetClock();
			resetAllRotors();
			rotorDisplay();
		});
		down1.setOnAction(event ->
		{
			down();
			value-=2;
			forwardEngineer(0);
			rotorDisplay();
		});
		down2.setOnAction(event ->
		{
			down();
			COUNT-=26;
			forwardEngineer(1);
			rotorDisplay2();
		});
		down3.setOnAction(event ->
		{
			down();
			COUNT-=676;
			forwardEngineer(2);
			rotorDisplay3();
		});
		down4.setOnAction(event ->
		{
			rotorCheck(0, 1,2,true);
			resetClock();
			resetAllRotors();
			rotorDisplay();
		});
		down5.setOnAction(event ->
		{
			rotorCheck(1, 0,2,true);
			resetClock();
			resetAllRotors();
			rotorDisplay();
		});
		down6.setOnAction(event ->
		{
			rotorCheck(2, 0,1,true);
			resetClock();
			resetAllRotors();
			rotorDisplay();
		});

	}

	private void resetAllRotors()
	{
		for(int i=0 ; i<3 ; i++)
		{
			resetRotor(i,showArray[i]-1);
		}
	}

	private void resetClock()
	{
		COUNT=0;
		value=1;
	}

	private void rotorCheck(int i, int j, int k, boolean value)
	{
		while (showArray[i]>0 && showArray[i]<9)
		{
			if(value==true)
			{
				showArray[i]++;
			}
			if (value==false)
			{
				showArray[i]--;
			}
			if (showArray[i]==9)
			{
				showArray[i] = 1;
			}
			if (showArray[i]== 0)
			{
				showArray[i] = 8;
			}
			if (showArray[i] != showArray[j] && showArray[i] != showArray[k])
			{
				dispRotor(i, showArray[i]);
				break;
			}
		}
	}

	private void down()
	{
		System.out.println("Down");
		COUNT -= 2;
		//value -= 2;
	}


	private void rotorDisplay()
	{
		int standardX, standardY;
			for (int i = 0; i < 3; i++)
			{
				standardX = 1200;
				standardY = 2 + (i * 42);
				Rectangle rectangle = new Rectangle(30, 40);
				rectangle = rectStruct(rectangle, standardX, standardY, baseColor, false);
				rotorDisplay2();
				//System.out.println(COUNT + "    " + value);
				if (value > 26)
				{
					value = value % 26;
				}
				if (value == 0)
				{
					value = 26;
				}
				if(value<0)
				{
					value = 26 + value;
				}
				Text text = new Text(String.valueOf(value));
				text = textAlign(text,standardX , standardY, rotorColor);

				stack.getChildren().addAll(rectangle, text);
				value++;
			}
		COUNT++;
		value = (value-3)+1;
	}

	private void dispRotor(int rectNumber, int number)
	{
		Rectangle rectangle = rectStruct(new Rectangle(30,40), 1250-(250*(rectNumber)), 44, baseColor, false );
		Text text = textAlign(new Text(String.valueOf(number)), 1250-(250*(rectNumber)),44, rotorColor);
		disStack.getChildren().addAll(rectangle, text);
		//System.out.print("Rotor " + rectNumber + " :- ");
		for (int i=0 ; i<26 ; i++)
		{
			rotor[rectNumber][i] = rotorCopy[number-1][i];
			//System.out.print(rotor[rectNumber][i]);
		}
		System.out.println();
	}

	private void rotorDisplay2()
	{
		int standardX, standardY;
		int note = (COUNT/26)+1;
		if(note<26)
		{
			note = note%26;
		}
			for (int i = 0; i < 3; i++)
			{
				standardX = 950;
				standardY = 2 + (i * 42);
				Rectangle rectangle = new Rectangle(30, 40);
				rectangle = rectStruct(rectangle,standardX,standardY, baseColor, false);
				rotorDisplay3();
				if(note>26)
				{
					note = note%26;
				}
				if(note<0) {
					note = 26 + note;
				}
				if(note==0)
				{
					note = 26;
				}
				Text text = new Text(String.valueOf(note));
				text = textAlign(text,standardX , standardY, rotorColor);

				stack.getChildren().addAll(rectangle, text);
				note++;
			}
	}

	private void rotorDisplay3()
	{
		int standardX, standardY;
		int card = (COUNT/676)+1;
		if(card<26)
		{
			card = card%26;
		}
		for (int i = 0; i < 3; i++)
		{
			standardX = 700;
			standardY = 2 + (i * 42);
			Rectangle rectangle = new Rectangle(30, 40);
			rectangle = rectStruct(rectangle,standardX,standardY, baseColor, false);

			if(card>26)
			{
				card = card%26;
			}

			if (card<0)
			{
				card = card+26;
			}
			if(card==0)
			{
				card = 26;
			}
			Text text = new Text(String.valueOf(card));
			text = textAlign(text,standardX , standardY, rotorColor);

			stack.getChildren().addAll(rectangle, text);
			card++;
		}
	}

	private Rectangle rectStruct(Rectangle rectangle, int standardX, int standardY, String color, Boolean value)
	{
		if(value==false)
		{
			rectangle.setFill(Paint.valueOf(color));
		}
		else if (value==true)
		{
			rectangle.setFill(Color.TRANSPARENT);
		}

		rectangle.setTranslateX(standardX);
		rectangle.setTranslateY(standardY);
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(4);
		return rectangle;
	}

	public void colorChange(int i)
	{
		System.out.println("done");
		keyboardLayer.get(i).setFill(Paint.valueOf(changeColor));
	}


	public void defaultColor(int key)
	{
		//for (int i = 0 ; i<3 ;i++)
		{
			rotorDisplay();
		}


		keyboardLayer.get(key).setFill(Paint.valueOf(baseColor));
	}

	private void mainStruture()
	{
		paneBt.setStyle("-fx-background-color: #734d26;");
		for(int k = 0 ; k<26 ; k++)
		{
			Circle circle = new Circle();
			Text text = new Text();
			int standardX=0;
			int standardY=0;
			if (k<10)
			{
				standardX = (k * (RADIUS + 92)) + 270;
				standardY = RADIUS + 20;

			}
			else if(k<19)
			{
				int j = k-10;
				standardX = j*(RADIUS+92) + 340;
				standardY = (RADIUS*3) + 40;

			}
			else if(k<26)
			{
				int j = (k-19);
				standardX = j*(RADIUS+92) + 488;
				standardY = (4*RADIUS) + 120;
			}
			circle = circleProperty(circle, standardX, standardY);
			text = textProperty(text, standardX, standardY, keySize);

			keyboardLayer.add(circle);
			stackPane.getChildren().addAll(circle, text);
		}
	}

	private Text textProperty(Text text, int standardX, int standardY, int ksize)
	{
		text = new Text(String.valueOf(lampArray[characterCount]));
		characterCount++;
		if(characterCount>25)
		{
			characterCount = characterCount/26 - 1;
		}
		text = textAlign(text, standardX, standardY, keyColor);
		text.setFont(new Font(ksize));
		return text;
	}

	private Text textAlign(Text text, int standardX, int standardY, String color)
	{
		text.setTranslateX(standardX);
		text.setTranslateY(standardY);
		text.setBoundsType(TextBoundsType.VISUAL);
		text.setStroke(Paint.valueOf(color));
		return text;
	}

	private Circle circleProperty(Circle circle, int standardX, int standardY)
	{
		circle.setRadius(RADIUS);
		circle.setStrokeWidth(4);
		circle.setStroke(Paint.valueOf(strokeColor));
		circle.setCenterX(RADIUS);
		circle.setCenterY(RADIUS);
		circle.setTranslateX(standardX);
		circle.setTranslateY(standardY);
		circle.setFill(Paint.valueOf(baseColor));
		Light.Spot light = new Light.Spot();
		light.setColor(Color.valueOf(spotLight));
		light.setX(70);
		light.setY(55);
		light.setZ(60);
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		circle.setEffect(lighting);
		return circle;
	}

	@Override
	public void initialize(URL location, ResourceBundle resouces)
	{

	}
	public class PlugLoc
	{
		int standardX, standardY;
		PlugLoc(int k)
		{
			if (k < 10)
			{
				standardX = (k * (RADIUS + 92)) + 270;
				standardY = (RADIUS*2) + 58;
			}
			else if (k < 19)
			{
				int j = k - 10;
				standardX = j * (RADIUS + 92) + 340;
				standardY = (RADIUS*5) + 30;
			}
			else if (k < 26)
			{
				int j = (k - 19);
				standardX = j * (RADIUS + 92) + 488;
				standardY = (8 * RADIUS) + 4;
			}
		}
	}
}
