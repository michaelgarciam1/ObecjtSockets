
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Vista extends JFrame implements ActionListener {

    private JTextArea messageArea;
    private JTextField textInput;
    Main controler;

    public Vista(Main controler) {
        super("P2P Chat");
        this.controler = controler;
        initializeGUI();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 450);
    }

    private void initializeGUI() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        // Panel para el encabezado
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(81, 149, 185, 255));
        JLabel headerLabel = new JLabel("Bandeja de entrada:");
        headerLabel.setForeground(Color.WHITE);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.BOTH;
        c2.gridx = 0;
        c2.gridy = 0;
        c2.weightx = 1;
        c2.weighty = 1;
        headerPanel.add(headerLabel, c2);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTH;
        container.add(headerPanel, c);

        messageArea = new JTextArea(7, 7);
        messageArea.setBackground(new Color(167, 217, 243, 207));
        messageArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(messageArea);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        container.add(jScrollPane, c);

        textInput = new JTextField(10);
        textInput.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.SOUTH;
        container.add(textInput, c);

        textInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textInput.getText().equals("Escriba un mensaje:")) {
                    textInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textInput.getText().isEmpty()) {
                    textInput.setText("Escriba un mensaje:");
                }
            }
        });
        textInput.setText("Escriba un mensaje:");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.enviarMensaje();
    }

    public void enviarMensaje() {
        String mensaje = textInput.getText();
        messageArea.append("<< " + mensaje + "\n");
        textInput.setText("");
        controler.enviarMensaje(new Message(mensaje));
    }

    public void recibirMensaje(Message message) {
        messageArea.append(message + "\n");
    }

}
