
package principa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Calculador {
      private JFrame frame;
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton botaoAdicao, BotaoSubtracao, botaoMult, botaoDivisao, botaoHistorico, BotaoResultado, botaoapagar;
    private ArrayList<String> history;

    private String currentOperation;
    private double firstOperand;
    private double secondOperand;

    public Calculador() {
        history = new ArrayList<>();
        currentOperation = "";
        firstOperand = 0;
        secondOperand = 0;

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        displayField = new JTextField();
        displayField.setBounds(10, 10, 260, 30);
        displayField.setEditable(false);
        frame.add(displayField);
        
          numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        botaoAdicao = new JButton("+");
        BotaoSubtracao = new JButton("-");
        botaoMult = new JButton("*");
        botaoDivisao = new JButton("/");
        botaoHistorico = new JButton("History");
        BotaoResultado = new JButton("=");
        botaoapagar = new JButton("C");

        setButtonBounds();
        
        for (JButton button : numberButtons) {
            button.addActionListener(new NumberButtonListener());
            frame.add(button);
        }

         botaoAdicao.addActionListener(new OperationButtonListener());
        BotaoSubtracao.addActionListener(new OperationButtonListener());
        botaoMult.addActionListener(new OperationButtonListener());
        botaoDivisao.addActionListener(new OperationButtonListener());
        botaoHistorico.addActionListener(new HistoryButtonListener());
        BotaoResultado.addActionListener(new EqualsButtonListener());
        botaoapagar.addActionListener(new ClearButtonListener());

        frame.setLayout(null);
        frame.setVisible(true);
        
    }
      private void setButtonBounds() {
        int x = 10;
        int y = 50;
        int buttonWidth = 60;
        int buttonHeight = 40;

        for (int i = 0; i < 10; i++) {
            numberButtons[i].setBounds(x, y, buttonWidth, buttonHeight);
            x += buttonWidth;

            if (i == 2 || i == 5) {
                x = 10;
                y += buttonHeight;
            }
        }

        botaoAdicao.setBounds(10, y + buttonHeight, buttonWidth, buttonHeight);
        BotaoSubtracao.setBounds(80, y + buttonHeight, buttonWidth, buttonHeight);
        botaoMult.setBounds(150, y + buttonHeight, buttonWidth, buttonHeight);
        botaoDivisao.setBounds(220, y + buttonHeight, buttonWidth, buttonHeight);
        botaoHistorico.setBounds(10, y + buttonHeight * 2, buttonWidth, buttonHeight);
        BotaoResultado.setBounds(80, y + buttonHeight * 2, buttonWidth, buttonHeight);
        botaoapagar.setBounds(150, y + buttonHeight * 2, buttonWidth, buttonHeight);

        frame.add(botaoAdicao);
        frame.add(BotaoSubtracao);
        frame.add(botaoMult);
        frame.add(botaoDivisao);
        frame.add(botaoHistorico);
        frame.add(BotaoResultado);
        frame.add(botaoapagar);
    }
private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton bt = (JButton) event.getSource();
            String botao = bt.getText();
            String dados = displayField.getText();
displayField.setText(dados + botao);
}
}
private class OperationButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        String buttonText = button.getText();

        if (!currentOperation.isEmpty()) {
            performOperation();
        }

        firstOperand = Double.parseDouble(displayField.getText());
        currentOperation = buttonText;
        displayField.setText("");
    }
}

private class HistoryButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        StringBuilder historyText = new StringBuilder();
        for (String operation : history) {
            historyText.append(operation).append("\n");
        }
        JOptionPane.showMessageDialog(frame, historyText.toString(), "Operation History", JOptionPane.INFORMATION_MESSAGE);
    }
}

private class EqualsButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        performOperation();
        currentOperation = "";
    }
}

private class ClearButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        displayField.setText("");
        firstOperand = 0;
        secondOperand = 0;
        currentOperation = "";
    }
}

private void performOperation() {
    if (!currentOperation.isEmpty()) {
        double resultado;
        secondOperand = Double.parseDouble(displayField.getText());
        switch (currentOperation) {
            case "+":
                resultado = firstOperand + secondOperand;
                history.add(firstOperand + " + " + secondOperand + " = " + resultado);
                displayField.setText(String.valueOf(resultado));
                break;
            case "-":
                resultado = firstOperand - secondOperand;
                history.add(firstOperand + " - " + secondOperand + " = " + resultado);
                displayField.setText(String.valueOf(resultado));
                break;
            case "*":
                resultado = firstOperand * secondOperand;
                history.add(firstOperand + " * " + secondOperand + " = " + resultado);
                displayField.setText(String.valueOf(resultado));
                break;
            case "/":
                if (secondOperand != 0) {
                    resultado = firstOperand / secondOperand;
                    history.add(firstOperand + " / " + secondOperand + " = " + resultado);
                    displayField.setText(String.valueOf(resultado));
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro: Nao pode dividir por zero", "", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                break;
        }
    }
}


}
