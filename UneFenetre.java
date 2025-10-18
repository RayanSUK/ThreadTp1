import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
        super("le Mobile");
        // pas obligatoire. possible de mettre this.add(sonMobile)
        Container leConteneur = getContentPane();
        sonMobile = new UnMobile(LARG,HAUT);
        leConteneur.add(sonMobile);
        Thread laTache = new Thread(sonMobile);
        laTache.start();
        setSize(LARG,HAUT);
        setVisible(true);
    }
}
