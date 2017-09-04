package gui;

import elements.DiagramDevice;
import elements.DiagramElement;
import engine.Engine;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Marko on 25-May-15.
 */
public class Properties extends JDialog {
    private ButtonGroup inputs = new ButtonGroup();
    private JColorChooser colorChooser;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private String name,description;
    private int inputNo;
    private Color color;
    private JPanel pane = new JPanel();
    private boolean ok = true;
    private JRadioButton input3,input4,input8;
    private JScrollPane scroll;

    public Properties(final DiagramElement element) {
        super();
        super.setTitle("Properties");
        setBackground(Color.WHITE);
        setSize(new Dimension(750, 350));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pane, BorderLayout.CENTER);
        pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        pane.setLayout(null);

        if (element.getFillColor()!=null) colorChooser = new JColorChooser(element.getFillColor());
        else colorChooser = new JColorChooser();
        colorChooser.setBounds(280, 15, 450, 250);
        pane.add(colorChooser);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(15, 15, 50, 20);
        pane.add(lblName);

        nameField = new JTextField(element.getName());
        nameField.setBounds(80, 15, 100, 20);
        pane.add(nameField);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(15, 45, 80, 20);
        pane.add(lblDescription);

        descriptionArea = new JTextArea(element.getDescription());
        scroll = new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(15, 75, 200, 200);
        pane.add(scroll);

        if(element instanceof DiagramDevice){
            JLabel lblInputs = new JLabel("Inputs:");
            lblInputs.setBounds(230, 15, 50, 20);
            pane.add(lblInputs);
            input3 = new JRadioButton("3");
            input3.setBounds(230, 45, 50, 20);
            pane.add(input3);
            input4 = new JRadioButton("4");
            input4.setBounds(230, 75, 50, 20);
            pane.add(input4);
            input8 = new JRadioButton("8");
            input8.setBounds(230, 105, 50, 20);
            pane.add(input8);
            inputs.add(input3);
            inputs.add(input4);
            inputs.add(input8);
            DiagramDevice device = (DiagramDevice) element;
            if(device.getInputCount() == 3) input3.setSelected(true);
            if(device.getInputCount() == 4) input4.setSelected(true);
            if(device.getInputCount() == 8) input8.setSelected(true);
        }
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = new JButton("Apply");
        okButton.addActionListener(arg0 -> {
            name = nameField.getText();
            description = descriptionArea.getText();
            color = colorChooser.getColor();
            if(element instanceof DiagramDevice){
                if(input3.isSelected()) inputNo = 3;
                if(input4.isSelected()) inputNo = 4;
                if(input8.isSelected()) inputNo = 8;
            }
            ok = true;
            dispose();
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(arg0 -> {
            ok = false;
            dispose();
        });
        buttonPane.add(cancelButton);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                ok = false;
                super.windowClosing(arg0);
            }
        });
    }

    public String getNewName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public boolean isOk() {
        return ok;
    }

    public int getInputNo() {
        return inputNo;
    }
}
