import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TrithemiusCipher extends JFrame {

    private static final long serialVersionUID = 5440046153795120638L;

    public TrithemiusCipher() {
        setTitle("Trithemius Cipher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        content.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        final JTextArea textInput = new JTextArea(7, 40);
        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);
        JScrollPane textInputScroll = new JScrollPane(textInput);
        textInputScroll.setBorder(new TitledBorder(new EtchedBorder(),
                "Text"));

        final JTextArea textOutput = new JTextArea(7, 40);
        JScrollPane textOutputScroll = new JScrollPane(textOutput);
        textOutputScroll.setBorder(new TitledBorder(new EtchedBorder(),
                "Result"));

        leftPanel.add(textInputScroll, BorderLayout.CENTER);
        leftPanel.add(textOutputScroll, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(5, 0, 5, 5));

        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(3, 2));
        keyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Keys"));
        final JTextField[] keyFields = new JTextField[3];
        char keyFieldName = 'A';
        for (int i = 0; i < keyFields.length; i++) {
            keyFields[i] = new JTextField(5);
            keyPanel.add(new JLabel(String.valueOf(keyFieldName++)));
            keyPanel.add(keyFields[i]);
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1));

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textOutput.setText("");
                String text = textInput.getText();
                int[] keys = new int[3];
                for (int i = 0; i < keys.length; i++) {
                    try {
                        keys[i] = Integer.parseInt(keyFields[i].getText());
                        if (keys[i] < 0) {
                            JOptionPane.showMessageDialog(TrithemiusCipher.this, "Ключ не может быть отрицательным!");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(TrithemiusCipher.this, "Неверный ключ! Убедитесь в правильности ввода ключей!");
                        return;
                    }
                }
                textOutput.append(encrypt(text, keys));
            }
        });

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textOutput.setText("");
                String text = textInput.getText();
                int[] keys = new int[3];
                for (int i = 0; i < keys.length; i++) {
                    try {
                        keys[i] = Integer.parseInt(keyFields[i].getText());
                        if (keys[i] < 0) {
                            JOptionPane.showMessageDialog(TrithemiusCipher.this, "Ключ не может быть отрицательным!");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(TrithemiusCipher.this, "Неверный ключ! Убедитесь в правильности ввода ключей!");
                        return;
                    }
                }
                textOutput.append(decrypt(text, keys));
            }
        });

        JButton helpButton = new JButton("Справка");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TrithemiusCipher.this,
                        "Ключи A, B и С не должны быть отрицательными!");
            }
        });

        JButton exitButton = new JButton("Выход");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonsPanel.add(encryptButton);
        buttonsPanel.add(decryptButton);
        buttonsPanel.add(helpButton);
        buttonsPanel.add(exitButton);

        rightPanel.add(keyPanel, BorderLayout.NORTH);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);

        content.add(leftPanel, BorderLayout.CENTER);
        content.add(rightPanel, BorderLayout.EAST);

        setContentPane(content);
        setLocationRelativeTo(null);
        pack();
    }

    private String encrypt(String text, int[] keys) {
        int A = keys[0];
        int B = keys[1];
        int C = keys[2];
        char[] openText = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < openText.length; i++) {
            int k = A * (i * i) + B * i + C;
            int L = ((openText[i]) + k) % 65536;
            sb.append((char) L);
        }
        return sb.toString();
    }

    private String decrypt(String text, int[] keys) {
        int A = keys[0];
        int B = keys[1];
        int C = keys[2];
        char[] openText = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < openText.length; i++) {
            int k = A * (i * i) + B * i + C;
            int La = ((openText[i]) - k);
            while (La < 0) {
                La += 65536;
            }
            int L = La % 65536;
            sb.append((char) L);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new TrithemiusCipher().setVisible(true);
    }
}