/**
 *
 * @file
 * @brief Here is wrapper class for Buttons work with
 *
 *
 * @authors Vladislav Shikhanov
 *
 *		  http://move-llc.ru
 *****************************************************************************/
package ui;

import javax.swing.*;
import java.awt.EventQueue;

public class Buttons extends JFrame {

    public Buttons(){
        initButtons();
    }

    void initButtons(){
        var sendIcon = new ImageIcon("img/send.png");
        var receiveIcon = new ImageIcon("img/receive.png");

        var quitBtn = new JButton("Quit");
        var sendBtn = new JButton("Send", sendIcon);
        var receiveBtn = new JButton("Receive", receiveIcon);

        createLayout(sendBtn, receiveBtn, quitBtn);

        setTitle("Buttons");
        setLocationRelativeTo(null);
    //    setSize(500, 500);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createLayout(JComponent... arg) {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
        );

        gl.linkSize(arg[0], arg[1], arg[2]); //the same size to all

        pack();

    }

}
