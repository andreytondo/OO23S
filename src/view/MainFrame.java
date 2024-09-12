package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Marmitaria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));

        addButton(menuPanel, "Controle de Usuários", UserFrame::new);
        addButton(menuPanel, "Controle de Produtos", ProductFrame::new);
        addButton(menuPanel, "Controle de Pedidos", OrderFrame::new);
        addButton(menuPanel, "Controle de Entregas", ShippingFrame::new);
        addButton(menuPanel, "Controle de Pagamentos", PaymentFrame::new);
        addButton(menuPanel, "Sair", this::dispose);

        add(menuPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    void addButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.exit(0);
    }
}
