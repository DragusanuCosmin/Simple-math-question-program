import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private static final int MAX_NUMBER = 10;
    private static final Random RANDOM = new Random();

    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JButton newButton;
    private JLabel timerLabel;
    private int answer;
    private long startTime;
    private boolean isTiming;

    public Main() {
        JFrame frame = new JFrame("Math Question");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        questionLabel = new JLabel();
        answerField = new JTextField();
        answerField.setPreferredSize(new Dimension(100, 30)); // set preferred size
        submitButton = new JButton("Submit");
        newButton = new JButton("New");
        submitButton.addActionListener(e -> submitAnswer());
        newButton.addActionListener(e -> newQuestion());

        // Create the timer label
        timerLabel = new JLabel("Time: 0ms");

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.add(questionLabel);
        panel.add(answerField);
        panel.add(submitButton);
        panel.add(newButton);
        panel.add(timerLabel);

        // Add the panel to the frame
        frame.add(panel);

        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        // Show the window
        frame.setVisible(true);

        // Start with a new question
        newQuestion();
    }

    private void newQuestion() {
        // Generate two random numbers and an operator
        int a = RANDOM.nextInt(MAX_NUMBER) + 1;
        int b = RANDOM.nextInt(MAX_NUMBER) + 1;
        while((a+1)%(b+1)!=0){
             a = RANDOM.nextInt(MAX_NUMBER) + 1;
             b = RANDOM.nextInt(MAX_NUMBER) + 1;
        }
        int c=RANDOM.nextInt(MAX_NUMBER) ;
        char operator = c==0 ? '+' :c==1?'*':c==2? '-':'/';

        // Calculate the correct answer
        answer = operator == '+' ? a + b :operator == '-' ? a - b:operator == '*' ?a * b:a/b;

        // Display the question and clear the answer field
        questionLabel.setText(String.format("%d %c %d = ", a, operator, b));
        answerField.setText("");

        // Clear the timer and start timing
        startTime = System.currentTimeMillis();
        isTiming = true;
    }

    private void submitAnswer() {


        // Stop the timer and calculate the elapsed time
        isTiming = false;
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Check if the user's answer is correct and display the result
        try {
            int userAnswer = Integer.parseInt(answerField.getText());
            if (userAnswer == answer) {
                JOptionPane.showMessageDialog(null, String.format("Correct! Time: %dms", elapsedTime));
            } else {
                JOptionPane.showMessageDialog(null, String.format("Wrong. The answer is %d. Time: %dms", answer, elapsedTime));
            }
        } catch (NumberFormatException ex) {
            // Ignore invalid input
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}