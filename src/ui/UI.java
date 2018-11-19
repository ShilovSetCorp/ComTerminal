package ui; /**
 *
 * @file
 * @brief Here is wrapper class for ui.UI to work with Com port
 *
 *
 * @authors Vladislav Shikhanov
 *
 *		  http://move-llc.ru
 *****************************************************************************/



import comport.ComPort;
import jssc.SerialPortException;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI extends JFrame{

    private ComPort comPort;
    private JTextArea dataExchangeLog = new JTextArea(); // show text here
    private JTextArea dataReceived = new JTextArea(); // show text here
    private JTextField dataToSend = new JTextField(16); //got text from here


    public UI(ComPort comPort){
        this.comPort = comPort;
        initUI();
    }

    public void sendData (String str) {
        try {
            comPort.sendToCom(str);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    private void initUI(){
        JTabbedPane tabbedPane = new JTabbedPane();


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
        JPanel panel = new JPanel();

        GroupLayout groupLayout = new GroupLayout(panel);

        ImageIcon sendIcon = new ImageIcon("img/send.png");
        ImageIcon receiveIcon = new ImageIcon("img/receive.png");

        JButton sendBtn = new JButton("Send", sendIcon);
        JButton receiveBtn = new JButton("Receive", receiveIcon);

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
        JPanel panel = new JPanel();

        dataExchangeLog.setText("\n123456");

        panel.add(dataExchangeLog);

        return panel;
    }


    private class SendAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent event) {
            sendDataToCom();
        }

        private void sendDataToCom() {
            Document document = dataToSend.getDocument();
            try {
                sendData(document.getText(0,document.getLength()));
               //dataReceived.setText(document.getText(0, document.getLength()));
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
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        pane.setBackground(Color.MAGENTA);
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
