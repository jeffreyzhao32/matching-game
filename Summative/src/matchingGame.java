import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class matchingGame extends Summative{
	int numberOfQuestions;
	int maxCount = 0;
	JPanel matchingGamePanel;
	String[][] list = new String[12][2];
	
	public matchingGame() {
	      
		
	}
	
	public JPanel getPanel() {
		return matchingGamePanel;
	}
	
	public void defineList() {
		list[0][0] = "4x-10 = 18, what is the value of x?";
		list[0][1] = "7";
		
		list[1][0] = "3(x-2) = 9, what is the value of x?";
		list[1][1] = "5";
		
		list[2][0] = "20/x = 2, what is the value of x?";
		list[2][1] = "10";
		
		list[3][0] = "5y-12 = 33, what is the value of y?";
		list[3][1] = "9";
		
		list[4][0] = "1+2x = 4x+9, what is the value of x?";
		list[4][1] = "-2";
		
		list[5][0] = "3(1+p) = 2(p-5), what is the value of p?";
		list[5][1] = "-13";
		
		list[6][0] = "9x-6 = -42, what is the value of x?";
		list[6][1] = "-4";
		
		list[7][0] = "x/3 = 14, what is the value of x?";
		list[7][1] = "42";
		
		list[8][0] = "2d+10 = -10, what is the value of x?";
		list[8][1] = "-10";
		
		list[9][0] = "2x = 60-3x, what is the value of y?";
		list[9][1] = "12";
		
		list[10][0] = "15-z=27-5z, what is the value of x?";
		list[10][1] = "3";
		
		list[11][0] = "6x-9=4x-1, what is the value of p?";
		list[11][1] = "2";
		
		
	}
	
}



