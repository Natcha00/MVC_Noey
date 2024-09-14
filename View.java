import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// View Class
public class View {
    private JFrame frame;
    private JTextField idField;
    private JButton checkButton;
    private JTextArea outputArea;
    private JButton milkButton;
    private JButton resetButton;
    private JButton addLemonButton;
    private JButton summaryReportButton;

    private JFrame summaryFrame;
    private JTextArea summaryOutputArea;
    private JButton backButton;

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cow Milk System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel idLabel = new JLabel("Cow ID:");
        idLabel.setBounds(10, 10, 80, 25);
        frame.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 10, 160, 25);
        frame.add(idField);

        checkButton = new JButton("Check Cow");
        checkButton.setBounds(270, 10, 100, 25);
        frame.add(checkButton);

        milkButton = new JButton("Milk Cow");
        milkButton.setBounds(10, 50, 150, 25);
        frame.add(milkButton);

        resetButton = new JButton("Reset BSOD");
        resetButton.setBounds(180, 50, 150, 25);
        frame.add(resetButton);

        addLemonButton = new JButton("Add Lemon");
        addLemonButton.setBounds(10, 90, 150, 25);
        frame.add(addLemonButton);

        summaryReportButton = new JButton("Summary Report");
        summaryReportButton.setBounds(180, 90, 150, 25);
        frame.add(summaryReportButton);

        outputArea = new JTextArea();
        outputArea.setBounds(10, 130, 360, 200);
        outputArea.setEditable(false);
        frame.add(outputArea);

        frame.setVisible(true);

        // Event listeners
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckButton();
            }
        });

        milkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMilkButton();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetButton();
            }
        });

        addLemonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddLemonButton();
            }
        });

        summaryReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSummaryReportButton();
            }
        });
    }

    private void handleCheckButton() {
        String id = idField.getText();
        Model cow = controller.getCow(id);
        if (cow != null) {
            outputArea.setText("Cow ID: " + cow.getId() + "\n" +
                    "Age: " + cow.getAgeYears() + " years " + cow.getAgeMonths() + " months\n" +
                    "Breed: " + cow.getBreed() + "\n" +
                    "Milk Bottles: " + cow.getMilkBottles() + "\n" +
                    (cow instanceof WhiteCowModel ? "Sour Milk Bottles: " + ((WhiteCowModel) cow).getSourMilkBottles()
                            : ""));
            //pin
            if (cow.hasBSOD) {
                outputArea.setText("Cow ID: " + cow.getId() + "\n" +
                        "Age: " + cow.getAgeYears() + " years " + cow.getAgeMonths() + " months\n" +
                        "Breed: " + cow.getBreed() + "\n" +
                        "Milk Bottles: " + cow.getMilkBottles() + "\n" +
                        (cow instanceof WhiteCowModel
                                ? "Sour Milk Bottles: " + ((WhiteCowModel) cow).getSourMilkBottles()
                                : "")
                        + "\n" +
                        "*** Alert: Milk Bottles Should be " + cow.getMilkDefect() + "****");
            }
            milkButton.setEnabled(!cow.hasBSOD());
            resetButton.setEnabled(cow.hasBSOD());
            addLemonButton.setEnabled(cow instanceof WhiteCowModel && !cow.hasBSOD());
        } else {
            outputArea.setText("Invalid Cow ID or Cow not found.");
            milkButton.setEnabled(false);
            resetButton.setEnabled(false);
            addLemonButton.setEnabled(false);
        }
    }

    private void handleMilkButton() {
        String id = idField.getText();
        controller.milkCow(id);
        handleCheckButton(); // Update cow info after milking
    }

    private void handleResetButton() {
        String id = idField.getText();
        controller.resetCow(id);
        handleCheckButton(); // Update cow info after reset
    }

    private void handleAddLemonButton() {
        String id = idField.getText();
        controller.addLemonToCow(id);
        handleCheckButton(); // Update cow info after adding lemon
    }

    private void handleSummaryReportButton() {
        if (summaryFrame != null) {
            summaryFrame.dispose();
        }
        summaryFrame = new JFrame("Summary Report");
        summaryFrame.setSize(400, 400);
        summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        summaryFrame.setLayout(null);

        summaryOutputArea = new JTextArea();
        summaryOutputArea.setBounds(10, 10, 360, 300);
        summaryOutputArea.setEditable(false);
        summaryOutputArea.setText(controller.getSummaryReport());
        summaryFrame.add(summaryOutputArea);

        backButton = new JButton("Back");
        backButton.setBounds(150, 320, 100, 25);
        summaryFrame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                summaryFrame.dispose();
            }
        });

        summaryFrame.setVisible(true);
    }
}
