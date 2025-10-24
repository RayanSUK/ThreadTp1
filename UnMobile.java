import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	int saLargeur, saHauteur, sonDebDessin;
	final int sonPas = 10, sonTemps = 50, sonCote = 40;
	SemaphoreMobile semaphore;
	int zoneDebut, zoneFin;

	UnMobile(int telleLargeur, int telleHauteur, SemaphoreMobile sem) {
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		semaphore = sem;
		setPreferredSize(new Dimension(telleLargeur, telleHauteur));
		zoneDebut = saLargeur / 3;
		zoneFin = 2 * saLargeur / 3;
		sonDebDessin = 0;
	}

	public void run() {
		while (true) {

			for (int i = 0; i < zoneDebut; i += sonPas) {
				sonDebDessin = i;
				repaint();
				sleep();
			}

			// attendre avant d'entrer dans le 2e tiers
			semaphore.syncWait();

			for (int i = zoneDebut; i < zoneFin; i += sonPas) {
				sonDebDessin = i;
				repaint();
				sleep();
			}
			semaphore.syncSignal();


			for (int i = zoneFin; i <= saLargeur - sonCote; i += sonPas) {
				sonDebDessin = i;
				repaint();
				sleep();
			}

			for (int i = saLargeur - sonCote; i > zoneFin; i -= sonPas) {
				sonDebDessin = i;
				repaint();
				sleep();
			}

			// attente avant d'entrer dans le 2e tiers (5eme boucles)
			semaphore.syncWait();

			for (int i = zoneFin; i > zoneDebut; i -= sonPas) {
				sonDebDessin = i;
				repaint();
				sleep();
			}

			// sortie de la zone critique (en sens inverse)
			semaphore.syncSignal();

			for (int i = zoneDebut; i >= 0; i -= sonPas) {
				sonDebDessin = i;
				repaint();
				sleep();
			}
		}
	}

	private void sleep() {
		try { Thread.sleep(sonTemps); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(sonDebDessin, saHauteur / 2 - sonCote / 2, sonCote, sonCote);
	}
}
