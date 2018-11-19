/**
 *
 * @file
 * @brief Here is wrapper class for UI to work with Com port
 *
 *
 * @authors Vladislav Shikhanov
 *
 *		  http://move-llc.ru
 *****************************************************************************/


package ui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI extends JFrame{

    private JTextArea dataExchangeLog = new JTextArea(); // show text here
    private JTextArea dataReceived = new JTextArea(); // show text here
    private JTextField dataToSend = new JTextField(16); //got text from here

    public UI(){
        initUI();
    }

    private void initUI(){
        var tabbedPane = new JTabbedPane();


        tabbedPane.addTab("Commands", createCommandsPanel());
        tabbedPane.addTab("Data exchange log", createTextPanel());

        createLayout(tabbedPane);

        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,500);
        setTitle("Terminal v.0.0.0.0.1.a");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private JPanel createCommandsPanel() {
        var panel = new JPanel();

        var groupLayout = new GroupLayout(panel);

        var sendIcon = new ImageIcon("img/send.png");
        var receiveIcon = new ImageIcon("img/receive.png");

        var sendBtn = new JButton("Send", sendIcon);
        var receiveBtn = new JButton("Receive", receiveIcon);

        sendBtn.addActionListener(new SendAction());
        receiveBtn.addActionListener(new ReceiveAction());

        dataReceived.setText("\n126");

        panel.setLayout(groupLayout);



        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(sendBtn)
                        .addComponent(dataToSend)
                )
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(receiveBtn)
                        .addComponent(dataReceived)
                )

        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(sendBtn)
                        .addComponent(dataToSend)
                )
                .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(receiveBtn)
                        .addComponent(dataReceived)
                )
        );

        groupLayout.linkSize(sendBtn, receiveBtn, dataToSend, dataReceived);


        return panel;
    }

    private JPanel createTextPanel() {
        var panel = new JPanel();

        dataExchangeLog.setText("\n123456");

        panel.add(dataExchangeLog);

        return panel;
    }


    private class SendAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent event) {
            sendData();
        }

        private void sendData() {
            Document document = dataToSend.getDocument();

            try {
               dataReceived.setText(document.getText(0, document.getLength()));
            } catch (BadLocationException exception) {
                Logger.getLogger(Text.class.getName()).log(
                        Level.WARNING, "Bad location", exception);
            }
        }
    }

    private class ReceiveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent event) {
            receiveData();
        }

        private void receiveData() {
            Document document = dataToSend.getDocument();

            try {
                dataReceived.setText(document.getText(0, document.getLength()));
            } catch (BadLocationException exception) {
                Logger.getLogger(Text.class.getName()).log(
                        Level.WARNING, "Bad location", exception);
            }
        }
    }

    private void createLayout(JComponent... argument) {
        var pane = getContentPane();
        var groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(argument[0])
        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(argument[0])
        );
        pack();
    }



}
