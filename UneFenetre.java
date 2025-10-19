import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;

    private Thread laTache;        // on garde le thread pour suspend/resume
    private boolean enPause = false;


    public UneFenetre()
    {   //appele le constructurer de la classe Jframe
        super("le Mobile"); //  titre de la barre  de titre de la fenetre
        // pas obligatoire. possible de mettre this.add(sonMobile)
        Container leConteneur = getContentPane();
        sonMobile = new UnMobile(LARG,HAUT);
        leConteneur.add(sonMobile);

        //ajout du bouton
        JButton bouton = new JButton("Start/Stop");
        leConteneur.add(bouton);


        laTache = new Thread(sonMobile);
        laTache.start();

        bouton.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if (!enPause) {
                    laTache.suspend();
                    enPause = true;
                } else {
                    laTache.resume();
                    enPause = false;
                }
            }
        });



        setSize(LARG,HAUT);
        setVisible(true);
    }
}
