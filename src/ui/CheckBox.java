package ui;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBox extends JFrame
        implements ItemListener {

    public CheckBox() {
        initUI();
    }

    private void initUI() {
        var checkBox = new JCheckBox("Show title", true );
        checkBox.addItemListener(this);

        createLayout(checkBox);


        setTitle("Check Box");
        setSize(700,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void itemStateChanged(ItemEvent ev) {
        int select = ev.getStateChange();

        if (select == ItemEvent.SELECTED) {

            setTitle("Box Checked!");
        } else {
            setTitle("");
        }
    }

    private void createLayout(JComponent... arg) {
        var pane = getContentPane();
        var groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(arg[0])
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(arg[0])
        );
    }
}
