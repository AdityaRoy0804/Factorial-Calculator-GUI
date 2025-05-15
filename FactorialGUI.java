import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FactorialGUI {
    private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;
    private JTextArea historyArea;

    private FactorialService factorialService = new FactorialService();
    private DBService dbService = new DBService();

    public FactorialGUI() {
        frame = new JFrame("Factorial Calculator");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel for top prompt and input
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel promptLabel = new JLabel("Enter a number to calculate factorial:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputField = new JTextField(10);
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton calculateBtn = new JButton("Calculate");
        JButton historyBtn = new JButton("Show History");

        calculateBtn.setFont(new Font("Arial", Font.BOLD, 14));
        historyBtn.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(calculateBtn);
        buttonPanel.add(historyBtn);

        topPanel.add(promptLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(inputField);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(buttonPanel);

        frame.add(topPanel, BorderLayout.NORTH);

        // Result label (optional visual feedback)
        resultLabel = new JLabel("Result will appear here.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(resultLabel, BorderLayout.CENTER);

        // History area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Calculation History"));
        scrollPane.setPreferredSize(new Dimension(580, 200));
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Button actions
        calculateBtn.addActionListener(this::handleCalculate);
        historyBtn.addActionListener(this::handleHistory);

        frame.setVisible(true);
    }

    private void handleCalculate(ActionEvent e) {
        String inputText = inputField.getText();
        try {
            int num = Integer.parseInt(inputText);
            if (num < 0) {
                resultLabel.setText("Please enter a non-negative integer.");
                return;
            }
            long iterative = factorialService.calculateIterative(num);
            long recursive = factorialService.calculateRecursive(num);
            String result = "<html>Iterative: " + iterative + "<br>Recursive: " + recursive + "</html>";
            resultLabel.setText(result);

            dbService.insertRecord(num, iterative, recursive);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter an integer.");
        }
    }

    private void handleHistory(ActionEvent e) {
        List<String> history = dbService.getHistory();
        historyArea.setText(String.join("\n", history));
    }
}
