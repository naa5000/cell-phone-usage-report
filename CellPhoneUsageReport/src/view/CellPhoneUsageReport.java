/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PrintReport;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author nedar
 */
public class CellPhoneUsageReport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("Print Cell Phone Usage Report Printer");

        f.setSize(220, 400);
        f.setLocationRelativeTo(null);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JButton printButton = new JButton("Print Cell Phone Usage Report");
        printButton.addActionListener(new PrintReport());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);
    }

}
