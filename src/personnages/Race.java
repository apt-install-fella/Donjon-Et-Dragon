package personnages;

public enum Race {
    HUMAIN,
    NAIN,
    ELFE,
    HALFELIN;

    public String toString(){
        if (this == Race.HUMAIN) {
            return "Humain";
        } else if (this == Race.NAIN) {
            return "Nain";
        } else if (this == Race.ELFE) {
            return "Elfe";
        } else if (this == Race.HALFELIN) {
            return "Halfelin";
        }
        return null;
    }
}
