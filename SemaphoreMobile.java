class SemaphoreMobile {
    private int valeur;

    public SemaphoreMobile(int valeurInitiale) {
        valeur = valeurInitiale;
    }

    public synchronized void syncWait() {
        while (valeur == 0) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        valeur--;
    }

    public synchronized void syncSignal() {
        valeur++;
        notifyAll();
    }
}