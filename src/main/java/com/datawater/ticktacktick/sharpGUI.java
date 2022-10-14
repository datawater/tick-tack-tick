package com.datawater.ticktacktick;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


public class sharpGUI {
    private static JFrame     frame = new JFrame();
    private static JPanel      buttonPanel = new JPanel();
    private static JPanel      textPanel = new JPanel();
    private static JLabel      textField = new JLabel();
    private static JButton[] buttons = new JButton[9];
    private static boolean  state = false; // false for "X" true for "O"
    private static int            nPressed = 0;
    private static boolean  gameEnded = false;

    /**
     * tickCheckForWin  a function which checks for a win in the board
     */
    private static void tickCheckForWin() {
        String[] b = new String[9];
        for (int i = 0; i < 9; i++) {b[i] = buttons[i].getText();}
        if ((b[0]=="X") && (b[1]=="X") && (b[2]=="X") ||((b[0]=="X") && (b[4]=="X") && (b[8]=="X"))
        ||(b[0]=="X") && (b[3]=="X") && (b[6]=="X") ||((b[1]=="X") && (b[4]=="X") && (b[7]=="X"))
        ||(b[2]=="X") && (b[5]=="X") && (b[8]=="X") ||((b[3]=="X") && (b[4]=="X") && (b[5]=="X"))
        ||(b[6]=="X") && (b[7]=="X") && (b[8]=="X")) {tickGameEnd(0);}
        if ((b[0]=="O") && (b[1]=="O") && (b[2]=="O") ||((b[0]=="O") && (b[4]=="O") && (b[8]=="O"))
        ||(b[0]=="O") && (b[3]=="O") && (b[6]=="O") ||((b[1]=="O") && (b[4]=="O") && (b[7]=="O"))
        ||(b[2]=="O") && (b[5]=="O") && (b[8]=="O") ||((b[3]=="O") && (b[4]=="O") && (b[5]=="O"))
        ||(b[6]=="O") && (b[7]=="O") && (b[8]=="O")) {tickGameEnd(1);}
        if (nPressed == 9) {tickGameEnd(2);}
        // System.exit(1);
    }

    /**
     * tickGameEnd is a function which is called after a game has ended. It updates textFieled.
     * The who won parameter can be 3 integers: 0 - X won 1 - O won 2 - Draw!
     */
    private static void tickGameEnd(int whoWon) {
        String won = whoWon == 0 ? "X Won the game!" : whoWon == 1 ? "O Won the game!" : "The game is a draw Draw!";
        Color color = whoWon == 0 ? Color.PINK : whoWon == 1 ? Color.GREEN : Color.LIGHT_GRAY;
        textField.setText(won);
        for (int i = 0; i < 9; i++) {
            buttons[i].setOpaque(false); buttons[i].setContentAreaFilled(true);
            buttons[i].setBackground(color);
        }
    }

    /**
     * newWindow is a function that starts a new game.
     */
    public static void newWindow() {
        // TODO: implement a splash screen
        frame.setSize(900, 800); frame.setLayout(new BorderLayout()); frame.setVisible(true);

        textField.setHorizontalAlignment(JLabel.CENTER); textField.setText("Tic Tac Toe!");
        textField.setFont(new Font("Serif", Font.PLAIN, 36)); textField.setOpaque(true);

        textPanel.setLayout(new BorderLayout()); textPanel.add(textField);
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false); buttons[i].setSize(800 / 3, 800 / 3); buttons[i].setContentAreaFilled(false);
            buttons[i].setFont(new Font("Serif", Font.PLAIN, 36));
            buttons[i].addMouseListener(new MouseInputListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();

                    if (button.getText().length() == 0 && e.getButton() == 1 && gameEnded == false) {
                        if (!state) {button.setText("X");}
                        else {button.setText("O");}
                        tickCheckForWin();
                    }
                    state = !state;
                }

                // Ignore this
                @Override public void mouseEntered(MouseEvent a){}@Override public void mouseExited(MouseEvent a){}@Override public void mousePressed(MouseEvent a){}@Override public void mouseReleased(MouseEvent a){}@Override public void mouseDragged(MouseEvent a){}@Override public void mouseMoved(MouseEvent a){}
            });
            buttonPanel.add(buttons[i]);
        }

        frame.add(textPanel, BorderLayout.NORTH);frame.add(buttonPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tick Tack Tick"); frame.setVisible(true);
    }
}
