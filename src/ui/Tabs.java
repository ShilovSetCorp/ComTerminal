package ui;

import javax.swing.*;
import java.awt.EventQueue;

public class Tabs extends JFrame {

    public Tabs(){
        initUI();
    }

    private void initUI() {

        var tabbedPane = new JTabbedPane();

        tabbedPane.addTab("First", createPanel("First panel"));
        tabbedPane.addTab("Second", createPanel("Second panel"));
        tabbedPane.addTab("Third", createPanel("Third panel"));

        createLayout(tabbedPane);

        setTitle("Tabbed Pane");
        setLocationRelativeTo(null);
        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel createPanel(String text) {
        var panel = new JPanel();
        var label = new JLabel(text);
        panel.add(label);

        var sendIcon = new ImageIcon("img/send.png");
        var receiveIcon = new ImageIcon("img/receive.png");

        var quitBtn = new JButton("Quit");
        var sendBtn = new JButton("Send", sendIcon);
        var receiveBtn = new JButton("Receive", receiveIcon);

        panel.add(sendBtn);
        panel.add(receiveBtn);

        return panel;

    }

    private void createLayout(JComponent... arg)
        {
        var pane = getContentPane();
        var groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(arg[0])
        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(arg[0])
        );
        pack();
    }


}
