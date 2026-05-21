import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineExa extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel questionLabel;
    JLabel timerLabel;
    JLabel scoreLabel;

    JRadioButton op1, op2, op3, op4;

    ButtonGroup bg;

    JButton nextButton;
    JButton submitButton;

    String questions[] = {
            "Which language is used for Java Programming?",
            "Which keyword is used to create object in Java?",
            "Which package is used for Swing GUI?",
            "Which method is the entry point of Java?",
            "Which company developed Java?"
    };

    String options[][] = {
            {"Python", "Java", "HTML", "C"},
            {"new", "class", "void", "static"},
            {"java.io", "javax.swing", "java.sql", "java.net"},
            {"start()", "run()", "main()", "init()"},
            {"Microsoft", "Google", "Sun Microsystems", "Apple"}
    };

    int answers[] = {2,1,2,3,3};

    int currentQuestion = 0;
    int score = 0;

    int timeLeft = 60;

    Timer timer;

    public OnlineExa() {

        setTitle("Online Examination System");

        setSize(800,600);

        setLayout(null);

        getContentPane().setBackground(new Color(220,235,255));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("ONLINE EXAMINATION SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0,0,150));
        titleLabel.setBounds(170,20,500,40);
        add(titleLabel);

        timerLabel = new JLabel("Time Left : 60 Seconds");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setForeground(Color.RED);
        timerLabel.setBounds(550,80,220,30);
        add(timerLabel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));
        questionLabel.setBounds(50,120,700,40);
        add(questionLabel);

        op1 = new JRadioButton();
        op1.setBounds(100,200,300,35);
        op1.setFont(new Font("Arial", Font.PLAIN, 20));
        op1.setBackground(new Color(220,235,255));

        op2 = new JRadioButton();
        op2.setBounds(100,260,300,35);
        op2.setFont(new Font("Arial", Font.PLAIN, 20));
        op2.setBackground(new Color(220,235,255));

        op3 = new JRadioButton();
        op3.setBounds(100,320,300,35);
        op3.setFont(new Font("Arial", Font.PLAIN, 20));
        op3.setBackground(new Color(220,235,255));

        op4 = new JRadioButton();
        op4.setBounds(100,380,300,35);
        op4.setFont(new Font("Arial", Font.PLAIN, 20));
        op4.setBackground(new Color(220,235,255));

        add(op1);
        add(op2);
        add(op3);
        add(op4);

        bg = new ButtonGroup();

        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        nextButton = new JButton("Next");
        nextButton.setBounds(180,470,150,45);
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        nextButton.setBackground(new Color(0,120,255));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        add(nextButton);

        submitButton = new JButton("Submit Exam");
        submitButton.setBounds(420,470,180,45);
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(new Color(0,150,80));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        add(submitButton);

        scoreLabel = new JLabel("");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(new Color(0,100,0));
        scoreLabel.setBounds(180,530,500,30);
        add(scoreLabel);

        loadQuestion();

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                timeLeft--;

                timerLabel.setText("Time Left : " + timeLeft + " Seconds");

                if(timeLeft <= 0) {

                    timer.stop();

                    JOptionPane.showMessageDialog(null,
                            "Time Over! Exam Submitted Automatically");

                    finishExam();
                }
            }
        });

        timer.start();

        setVisible(true);
    }

    public void loadQuestion() {

        bg.clearSelection();

        questionLabel.setText(
                "Q" + (currentQuestion + 1) + ". " +
                        questions[currentQuestion]
        );

        op1.setText(options[currentQuestion][0]);
        op2.setText(options[currentQuestion][1]);
        op3.setText(options[currentQuestion][2]);
        op4.setText(options[currentQuestion][3]);
    }

    public void checkAnswer() {

        int selectedAnswer = 0;

        if(op1.isSelected())
            selectedAnswer = 1;

        if(op2.isSelected())
            selectedAnswer = 2;

        if(op3.isSelected())
            selectedAnswer = 3;

        if(op4.isSelected())
            selectedAnswer = 4;

        if(selectedAnswer == answers[currentQuestion]) {

            score++;
        }
    }

    public void finishExam() {

        questionLabel.setVisible(false);

        op1.setVisible(false);
        op2.setVisible(false);
        op3.setVisible(false);
        op4.setVisible(false);

        nextButton.setVisible(false);
        submitButton.setVisible(false);

        scoreLabel.setText(
                "Your Final Score : " +
                        score + " / " + questions.length
        );

        JOptionPane.showMessageDialog(null,
                "EXAM COMPLETED" +
                        "\n\nTotal Questions : " + questions.length +
                        "\nCorrect Answers : " + score +
                        "\nWrong Answers : " +
                        (questions.length - score) +
                        "\n\nRESULT : " +
                        ((score >= 3) ? "PASS" : "FAIL")
        );
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == nextButton) {

            checkAnswer();

            currentQuestion++;

            if(currentQuestion < questions.length) {

                loadQuestion();
            }
            else {

                finishExam();
            }
        }

        if(e.getSource() == submitButton) {

            checkAnswer();

            timer.stop();

            finishExam();
        }
    }

    public static void main(String[] args) {

        new OnlineExa();
    }
}
