package jeu;

public class Narrateur {
    private String m_pseudo;

    public Narrateur(String pseudo) {
        this.m_pseudo = pseudo;
    }
    public Narrateur() {
        this.m_pseudo = "Maitre du jeu";
    }

    public String getPseudo() {
        return m_pseudo;
    }

    public String commenter(String phrase) {
        return phrase;
    }

}
