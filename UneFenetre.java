import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UneFenetre extends JFrame {

    UnMobile sonMobile1, sonMobile2;
    private final int LARG = 400, HAUT = 250;

    private Thread laTache1, laTache2;
    private boolean enPause1 = false, enPause2 = false;

    public UneFenetre() {
        super("Mobiles x2 (Grid 2x2)");

        Container leConteneur = getContentPane();
        // --- Grille 2 lignes x 2 colonnes ---
        leConteneur.setLayout(new GridLayout(2, 2));

        // ---- Mobile 1 + bouton 1 ----
        sonMobile1 = new UnMobile(LARG, HAUT);
        JButton bouton1 = new JButton("Start/Stop 1");

        // ---- Mobile 2 + bouton 2 ----
        sonMobile2 = new UnMobile(LARG, HAUT);
        JButton bouton2 = new JButton("Start/Stop 2");

        // --- Ordre d'ajout en GridLayout: B1 | M1 | B2 | M2 ---
        leConteneur.add(bouton1);
        leConteneur.add(sonMobile1);
        leConteneur.add(bouton2);
        leConteneur.add(sonMobile2);

        // --- Crée et démarre chaque thread ---
        laTache1 = new Thread(sonMobile1);
        laTache2 = new Thread(sonMobile2);
        laTache1.start();
        laTache2.start();

        // --- Actions des boutons: suspend/resume par mobile ---
        bouton1.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if (!enPause1) { laTache1.suspend(); enPause1 = true; }
                else            { laTache1.resume();  enPause1 = false; }
            }
        });

        bouton2.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if (!enPause2) { laTache2.suspend(); enPause2 = true; }
                else            { laTache2.resume();  enPause2 = false; }
            }
        });

        // fenêtre
        setSize(LARG, HAUT);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
