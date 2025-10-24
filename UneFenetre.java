import java.awt.*;
import javax.swing.*;


class UneFenetre extends JFrame {
    int nbMobiles = 6;     // le nombre de mobiles
    int p = 2;             // nb max autorisés dans la SC
    final int LARGE_MOBILE = 600, HAUT_MOBILE = 150;

    public UneFenetre() {
        super("Mobiles - Zone critique invisible");
        setLayout(new GridLayout(nbMobiles, 1));

        SemaphoreMobile sem = new SemaphoreMobile(p);

        for (int i = 0; i < nbMobiles; i++) {
            UnMobile mobile = new UnMobile(LARGE_MOBILE, HAUT_MOBILE, sem);
            add(mobile);
            new Thread(mobile).start();
        }

        setSize(LARGE_MOBILE, nbMobiles * HAUT_MOBILE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
