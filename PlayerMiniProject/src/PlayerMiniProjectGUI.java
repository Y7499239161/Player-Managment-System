import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PlayerMiniProjectGUI {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "student12345";

    public static void main(String[] args) {
        showWelcomeScreen();
    }

    private static void showWelcomeScreen() {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(600, 400);
        welcomeFrame.setLayout(null);
        welcomeFrame.getContentPane().setBackground(new Color(255, 165, 0)); // This sets the background to orange


        JLabel welcomeLabel = new JLabel("Welcome to Player Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(50, 100, 500, 50);
        welcomeFrame.add(welcomeLabel);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(250, 250, 100, 30);
        welcomeFrame.add(nextButton);

        nextButton.addActionListener(e -> {
            welcomeFrame.dispose();
            showLoginScreen();
        });

        welcomeFrame.setVisible(true);
    }

    private static void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(null);

        JLabel loginLabel = new JLabel("Login to Continue", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setBounds(50, 30, 300, 30);
        loginFrame.add(loginLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 100, 100, 30);
        loginFrame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 100, 200, 30);
        loginFrame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 150, 100, 30);
        loginFrame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 150, 200, 30);
        loginFrame.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 200, 100, 30);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("Yogesh") && password.equals("yogesh123")) {
                loginFrame.dispose();
                openMainFrame();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setVisible(true);
    }

    private static void openMainFrame() {
        JFrame frame = new JFrame("Player Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Player Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(150, 20, 400, 30);
        frame.add(titleLabel);

        JLabel idLabel = new JLabel("Player ID:");
        idLabel.setBounds(50, 80, 100, 30);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 80, 200, 30);
        frame.add(idField);

        JLabel nameLabel = new JLabel("Player Name:");
        nameLabel.setBounds(50, 120, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 120, 200, 30);
        frame.add(nameField);

        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(50, 160, 100, 30);
        frame.add(countryLabel);

        JTextField countryField = new JTextField();
        countryField.setBounds(150, 160, 200, 30);
        frame.add(countryField);

        JButton insertButton = new JButton("Insert");
        insertButton.setBounds(50, 220, 100, 30);
        frame.add(insertButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(160, 220, 100, 30);
        frame.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(270, 220, 100, 30);
        frame.add(deleteButton);

        JButton showButton = new JButton("Show");
        showButton.setBounds(380, 220, 100, 30);
        frame.add(showButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(490, 220, 100, 30);
        frame.add(exitButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(50, 270, 600, 150);
        frame.add(scrollPane);

        insertButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String country = countryField.getText();

                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                String query = "INSERT INTO player VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, country);
                pstmt.executeUpdate();

                outputArea.setText("Record inserted successfully.");
                con.close();
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String country = countryField.getText();

                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                String query = "UPDATE player SET pname = ?, country = ? WHERE pid = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, country);
                pstmt.setInt(3, id);
                int rows = pstmt.executeUpdate();

                outputArea.setText(rows > 0 ? "Record updated successfully." : "Player not found.");
                con.close();
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                String query = "DELETE FROM player WHERE pid = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, id);
                int rows = pstmt.executeUpdate();

                outputArea.setText(rows > 0 ? "Record deleted successfully." : "Player not found.");
                con.close();
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        showButton.addActionListener(e -> {
            try {
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                String query = "SELECT * FROM player";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                StringBuilder result = new StringBuilder("ID\tName\tCountry\n");
                while (rs.next()) {
                    result.append(rs.getInt("pid")).append("\t")
                          .append(rs.getString("pname")).append("\t")
                          .append(rs.getString("country")).append("\n");
                }

                outputArea.setText(result.toString());
                con.close();
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
