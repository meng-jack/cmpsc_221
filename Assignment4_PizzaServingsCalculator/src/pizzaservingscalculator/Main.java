// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package pizzaservingscalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main
{

    public static void main(String[] args)
    {
        JFrame jf=new JFrame("Pizza Servings Calculator");
        jf.setPreferredSize(new Dimension(350,300));
        JPanel root=new JPanel();
        root.setLayout(new GridLayout(4,1));
        JLabel title=new JLabel(
            "<html><h1 style=\"color:#ff0000\">Pizza Servings Calculator</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.RED);
        root.add(title);
        JPanel rowInput=new JPanel();
        rowInput.add(new JLabel("Enter the size of the pizza in inches: "));
        JTextField textField=new JTextField(4);
        rowInput.add(textField);
        root.add(rowInput);
        JLabel label=new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton button=new JButton("Calculate Servings");
        button.addActionListener((event)->SwingUtilities.invokeLater(()->label.
            setText(String.format("A %s inch pizza will serve %.2f people.",
                                  textField.getText(),Math.pow(Double.
                                  parseDouble(
                                      textField.getText())/8.0,2.0)))));
        root.add(button);
        root.add(label);
        jf.setContentPane(root);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

}
