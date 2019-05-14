package elementos;

import java.util.ArrayList;

public class Dialogo {
    private boolean habla_Ruby, cont_hablando;
    private ArrayList<String> frases;

    public Dialogo(boolean habla_Ruby, boolean cont_hablando, ArrayList<String> frases) {
        this.habla_Ruby = habla_Ruby;
        this.cont_hablando = cont_hablando;
        this.frases = frases;
    }

    public boolean isHabla_Ruby() {
        return habla_Ruby;
    }

    public boolean isCont_hablando() {
        return cont_hablando;
    }

    public ArrayList<String> getFrases() {
        return frases;
    }
}
