/* Author: Jeffrey Zhao
 * Date: June 12, 2023
 * Program: matching memory game teaching students how to solve one variable algebraic equations
 * Description: First tab is an introduction of the program to the user, second teaches students how to 
 * solve algebraic equations with an example. It also provides user with instruction on how to play the
 * game. In the game, there are 12 panels with pictures, users are allowed to select on 2 panels at once. 
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Summative extends JFrame {

	private JTabbedPane tabbedPane;
	private JButton nextButton;
	String[][] questionsList = new String[12][2]; // contain list of questions
	public JButton gameButton[] = new JButton[12]; // contain all 12 buttons
	public ArrayList<String> pictureNames = new ArrayList<String>(12); // used to generate random pictures
	public String[] tempPictureNames = new String[12]; // contain list of url names of images
	Map<String, Image> imageMap = new HashMap<>(); // hashmap to contain url name of image and the actual image
	int tempCount = 0; // number of questions that currently has an answer
	int current = 0; // number of pictures currently being displayed
	JButton tempButton1; // temporary button of first button clicked
	Color lightBlue = new Color(173, 216, 230);
	static boolean continueGame = true;

	public Summative() {
		setTitle("Algebraic Equation Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		tabbedPane = new JTabbedPane();

		// First pane - Introduction
		JPanel introductionPanel = new JPanel();
		defineIntroduction(introductionPanel);

		// Second pane - Description
		JPanel descriptionPanel = new JPanel();
		defineDescription(descriptionPanel);

		// Third pane - Matching game
		JPanel matchingGamePanel = new JPanel();

		defineButtons(matchingGamePanel);
		defineList();

		checkFirst(gameButton, matchingGamePanel);

		tabbedPane.addTab("Matching Game", matchingGamePanel);

		// Add tabbedPane to the frame
		add(tabbedPane);

		setVisible(true);
	}

	public void defineIntroduction(JPanel introductionPanel) {
		introductionPanel.setBackground(Color.WHITE);
		introductionPanel.setLayout(new BorderLayout());

		// add introduction text to screen
		JTextPane introductionText = new JTextPane();
		introductionText.setText("                     Welcome to Algebraic Equation Solver!\r\n" + "\r\n"
				+ "This program will help you learn how to solve algebraic \r\n" + "equations step by step. \r\n"
				+ "It also includes a memory matching game \r\n" + "for you to practice your skills! \r\n" + "\r\n"
				+ "Click on the 'Next' button to begin.");

		introductionText.setAlignmentX(CENTER_ALIGNMENT);
		Font font = new Font("Calibri", Font.PLAIN, 30);
		introductionText.setFont(font);
		introductionText.setSize(800, 500);
		introductionPanel.add(introductionText, BorderLayout.NORTH);

		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1); // Switch to the second pane
			}
		});

		// add picture to screen
		JLabel mathPicture = new JLabel();
		mathPicture.setHorizontalAlignment(SwingConstants.CENTER);
		mathPicture.setVerticalAlignment(SwingConstants.CENTER);
		URL url = this.getClass().getResource("math.jpg");
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH);
		mathPicture.setIcon(new ImageIcon(image));
		introductionPanel.add(mathPicture, BorderLayout.AFTER_LAST_LINE);

		introductionPanel.add(nextButton, BorderLayout.SOUTH);
		tabbedPane.addTab("Introduction", introductionPanel);
	}

	public void defineDescription(JPanel descriptionPanel) {
		descriptionPanel.setLayout(new BorderLayout());
		JTextArea descriptionText = new JTextArea(
				"To solve algebraic equations, follow these steps:\n\n1. Simplify each side "
						+ "of the equation if needed.\n2. Remove any parentheses by applying the distributive property.\n3. Combine "
						+ "like terms on each side of the equation.\n4. Isolate the variable term on one side of the equation by "
						+ "performing inverse operations.\n5. Solve for the variable by dividing both sides of the equation by the "
						+ "coefficient of the variable.\n\n "
						+ "For example, in the equation 2x+3=x+7, we can subtract x from both sides "
						+ "\n to isolate the variable on the left side: x+3 = 7. \n"
						+ "Then we can subtract from both sides to find that x=4. \n"
						+ "\n Click on the 'Next' button or matching game tab to proceed to the game to practice your skills. \n\n"
						+ "Rules of the game: \nEverytime you click on a button, a math question will pop up, if you solve the question \n"
						+ "the picture will pop up. You may turn over 2 pictures at one time. \nIf the pictures are the same, then they are removed from the screen");
		Font font2 = new Font("Calibri", Font.PLAIN, 20);
		descriptionText.setFont(font2);
		descriptionText.setEditable(false);
		descriptionPanel.add(descriptionText, BorderLayout.CENTER);

		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2); // Switch to the third pane
			}
		});
		descriptionPanel.add(nextButton, BorderLayout.SOUTH);
		tabbedPane.addTab("Description", descriptionPanel);
	}

	public void checkFirst(JButton[] gameButtons, JPanel panel) {

		int i = 0;

		while (i < 12) {
			gameButtons[i].addActionListener(buttonClickListener);
			i++;
		}

	}

	// check if game is finished
	public boolean gameFinished() {
		for (int i = 0; i < 12; i++) {
			if (gameButton[i].isEnabled()) {
				return false;
			}
		}
		return true;
	}

	// check if that questions for a button has been solved
	public boolean checkSolved(JButton button) {

		if (button.getText().equals("")) {
			return false;
		}
		return true;
	}

	// Actionlistener for all 12 buttons
	ActionListener buttonClickListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(current);
			JButton button = (JButton) e.getSource();
			if (tempButton1 == button) {
				JOptionPane.showMessageDialog(null, "Please do not click on the same button", "Plain Message",
						JOptionPane.PLAIN_MESSAGE);
				return;
			}

			// if user clicks on more than 2 buttons at once, return and don't do anything
			if (current > 1)
				return;

			if (current == 0) {
				if (checkSolved(button)) {

					button.setIcon(new ImageIcon(imageMap.get(button.getText())));

					tempButton1 = button;
					current++;
					return;

				}
				String answer = JOptionPane.showInputDialog(null, questionsList[tempCount][0], "Question",
						JOptionPane.QUESTION_MESSAGE);

				if (answer != null && !answer.isEmpty()) {
					if (answer.equals(questionsList[tempCount][1])) {
						button.setIcon(new ImageIcon(imageMap.get(tempPictureNames[tempCount])));
						button.setBackground(lightBlue);
						button.setText(tempPictureNames[tempCount]);
						button.setForeground(lightBlue);
						tempButton1 = button;
						tempCount++;

					} else {
						JOptionPane.showMessageDialog(null, "Incorrect, please try again", "Plain Message",
								JOptionPane.PLAIN_MESSAGE);
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "You did not provide a valid answer.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}

			else if (current == 1) {

				if (checkSolved(button)) {

					button.setIcon(new ImageIcon(imageMap.get(button.getText())));

					Timer timer = new Timer(1500, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									if (button.getText().equals(tempButton1.getText())) {
										button.setEnabled(false);
										tempButton1.setEnabled(false);
										if (gameFinished()) {
											String answer2 = JOptionPane.showInputDialog(null,
													"Congrats, you have finished! Please type yes if you want"
															+ " to play again",
													"Question", JOptionPane.QUESTION_MESSAGE);
											if (answer2 != null && !answer2.isEmpty()) {
												if (answer2.equals("yes")) {
													Summative main2 = new Summative();

												} else {
													JOptionPane.showMessageDialog(null, "Thank you for playing!",
															"Plain Message", JOptionPane.PLAIN_MESSAGE);
													return;
												}

											} else {
												JOptionPane.showMessageDialog(null,
														"You did not provide a valid answer.", "Error",
														JOptionPane.ERROR_MESSAGE);
												return;
											}

										}
										return;
									} else {
										tempButton1.setIcon(null);
										button.setIcon(null);
										tempButton1 = null;
										return;
									}

								}
							});
						}
					});
					timer.setRepeats(false);
					timer.start();

					current = 0;
					return;
				}

				String answer = JOptionPane.showInputDialog(null, questionsList[tempCount][0], "Question",
						JOptionPane.QUESTION_MESSAGE);

				if (answer != null && !answer.isEmpty()) {

					// check answer is correct
					if (answer.equals(questionsList[tempCount][1])) {
						button.setIcon(new ImageIcon(imageMap.get(tempPictureNames[tempCount])));
						button.setText(tempPictureNames[tempCount]);
						button.setForeground(lightBlue);
						button.setBackground(lightBlue);

						// check if picture is same as first one
						Timer timer = new Timer(1500, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										if (button.getText().equals(tempButton1.getText())) {
											button.setEnabled(false);
											tempButton1.setEnabled(false);
											if (gameFinished()) {
												String answer2 = JOptionPane.showInputDialog(null,
														"Congrats, you have finished! Please type yes if you want"
																+ " to play again",
														"Question", JOptionPane.QUESTION_MESSAGE);
												if (answer2 != null && !answer2.isEmpty()) {
													if (answer2.equals("yes")) {
														Summative main2 = new Summative();

													} else {
														JOptionPane.showMessageDialog(null, "Thank you for playing!",
																"Plain Message", JOptionPane.PLAIN_MESSAGE);
														return;
													}

												} else {
													JOptionPane.showMessageDialog(null,
															"You did not provide a valid answer.", "Error",
															JOptionPane.ERROR_MESSAGE);
													return;
												}
											}

										} else {
											tempButton1.setIcon(null);
											button.setIcon(null);

										}

									}
								});
							}
						});
						timer.setRepeats(false);
						timer.start();

						current = 0;
						tempCount++;
						return;

					} else {
						JOptionPane.showMessageDialog(null, "Incorrect, please try again", "Plain Message",
								JOptionPane.PLAIN_MESSAGE);
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "You did not provide a valid answer.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// Handle button click event

			current++;

		}
	};

	public void defineButtons(JPanel matchingGamePanel) {
		Random random = new Random();

		pictureNames.add("doggy.jpg");
		pictureNames.add("doggy.jpg");
		pictureNames.add("cat.jpg");
		pictureNames.add("cat.jpg");
		pictureNames.add("beaver.jpg");
		pictureNames.add("beaver.jpg");
		pictureNames.add("rabbit.jpg");
		pictureNames.add("rabbit.jpg");
		pictureNames.add("monkey.jpg");
		pictureNames.add("monkey.jpg");
		pictureNames.add("panda.jpg");
		pictureNames.add("panda.jpg");

		for (int i = 0; i < 12; i++) {
			gameButton[i] = new JButton();
			gameButton[i].setBackground(lightBlue);
			gameButton[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			gameButton[i].setBorder(BorderFactory.createRaisedBevelBorder());

			int randomNumber = random.nextInt(pictureNames.size());

			URL url = this.getClass().getResource(pictureNames.get(randomNumber));
			ImageIcon icon = new ImageIcon(url);
			;
			tempPictureNames[i] = pictureNames.get(randomNumber);
			imageMap.put(pictureNames.get(randomNumber),
					icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

			matchingGamePanel.add(gameButton[i]);
			pictureNames.remove(randomNumber);

		}
		matchingGamePanel.setLayout(new GridLayout(3, 3));

	}

	public void defineList() {
		questionsList[0][0] = "4x-10 = 18, what is the value of x?";
		questionsList[0][1] = "7";

		questionsList[1][0] = "3(x-2) = 9, what is the value of x?";
		questionsList[1][1] = "5";

		questionsList[2][0] = "20/x = 2, what is the value of x?";
		questionsList[2][1] = "10";

		questionsList[3][0] = "5y-12 = 33, what is the value of y?";
		questionsList[3][1] = "9";

		questionsList[4][0] = "1+2x = 4x+9, what is the value of x?";
		questionsList[4][1] = "-4";

		questionsList[5][0] = "3(1+p) = 2(p-5), what is the value of p?";
		questionsList[5][1] = "-13";

		questionsList[6][0] = "9x-6 = -42, what is the value of x?";
		questionsList[6][1] = "-4";

		questionsList[7][0] = "x/3 = 14, what is the value of x?";
		questionsList[7][1] = "42";

		questionsList[8][0] = "2d+10 = -10, what is the value of d?";
		questionsList[8][1] = "-10";

		questionsList[9][0] = "2x = 60-3x, what is the value of x?";
		questionsList[9][1] = "12";

		questionsList[10][0] = "15-z=27-5z, what is the value of z?";
		questionsList[10][1] = "3";

		questionsList[11][0] = "6x-9=4x-1, what is the value of x?";
		questionsList[11][1] = "4";

	}

	public static void main(String[] args) {

		Summative main = new Summative();

	}

}
