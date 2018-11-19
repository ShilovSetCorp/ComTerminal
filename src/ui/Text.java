package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Text extends JFrame  {
    private JLabel label;
    public Text(){
       initText();
    }

    private void initText() {
        var field = new JTextField(16); //got text from here
        label = new JLabel(); // show text here
        field.getDocument().addDocumentListener(new Listener());

        createLayout(field,label);
        setTitle("TextField");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class Listener implements DocumentListener {

        private String text;

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLabel(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLabel(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        private void updateLabel(DocumentEvent e) {
            var document = e.getDocument();
            int len = document.getLength();

            try {
                text = document.getText(0, len);
            } catch (BadLocationException exception) {
                Logger.getLogger(Text.class.getName()).log(
                        Level.WARNING, "Bad location", exception);
            }

            label.setText(text);
        }
    }

    private void createLayout(JComponent... arg) {
        var pane = getContentPane();
        var groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addGap(250)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                .addComponent(arg[1])
                .addGap(250)
        );

        pack();
    }


}
