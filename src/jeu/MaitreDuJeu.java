package jeu;

public class MaitreDuJeu {
    private String m_pseudo;

    public MaitreDuJeu(String pseudo) {
        this.m_pseudo = pseudo;
    }
    public MaitreDuJeu() {
        this.m_pseudo = "Maitre du jeu";
    }

    public String getPseudo() {
        return m_pseudo;
    }

}
