package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Updateprofile extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblEnteraddress;
    public Updateprofile() {
    	
    }
    // creating frame
    public Updateprofile(String name) {
        setBounds(450, 360, 1024, 234);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 34));
        textField.setBounds(373, 35, 609, 67);
        contentPane.add(textField);
        textField.setColumns(10);
        JButton btnSearch = new JButton("Update");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String address = textField.getText();
                try {
                	//connecting sql
                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                            "root", "Jaya@1234");

                        PreparedStatement st = (PreparedStatement) con
                            .prepareStatement("Update logindetails set address=? where name=?");

                        st.setString(4, address);
                        st.setString(2, name);
                        //update the table
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(btnSearch, "address has been successfully changed");

                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }

                }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 29));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(438, 127, 170, 59);
        contentPane.add(btnSearch);

        lblEnteraddress = new JLabel("Enter address :");
        lblEnteraddress.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblEnteraddress.setBounds(45, 37, 326, 67);
        contentPane.add(lblEnteraddress);



    	
    }

}
