package jeu;

import equipement.Arme;
import equipement.Armure;
import equipement.Equipement;
import personnages.Personnage;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import personnages.Monstre;
import personnages.Entite;



public class Donjon {
    //LONGUEUR C'EST LES CHIFFRES (LIGNES)
    //LARGEUR C'EST LES LETTRES (COLONNES)

    //==========attributs====================
    private final int m_num;
    int m_nb_monstres;
    int m_nb_personnages;
    private int m_longueur;
    private int m_largeur;
    private Hashtable<Integer, Entite> m_entites;
    private Hashtable<Personnage, Integer> m_personnages;
    private Hashtable<Monstre, Integer> m_monstres;
    private Hashtable<String, int[]> m_cases;


    //===========================================


    //===========constructeurs=========================================
    public Donjon(int num, int longueur, int largeur) {
        m_num = num;
        m_longueur = longueur;
        m_largeur = largeur;
        m_entites = new Hashtable<>();
        m_personnages = new Hashtable<>();
        m_monstres = new Hashtable<>();
        m_cases = new Hashtable<>(longueur * largeur);
        nommerCases();
    }

    public Donjon(int num) { //par defaut
        m_num = num;
        switch (num) {
            case 1:
                initDonjon1();
                break;
            case 2:
                initDonjon2();
                break;
            case 3:
                initDonjon3();
                break;
            default:
                System.out.println("Modèle de donjon inexistant.");
        }
    }
    //============================================================================


    /// ////PAR DEFAUT////////////
    /// choix des tailles par defaut
    /// choix des obstacles et equipements par defaut
    private void initDonjon1() {
        m_longueur = 7;
        m_largeur = 10;
        m_entites = new Hashtable<>();
        m_personnages = new Hashtable<>();
        m_monstres = new Hashtable<>();
        m_cases = new Hashtable<>(70);
        nommerCases();


        ajoutObstacle("A7");
        ajoutObstacle("C5");
        ajoutObstacle("G3");

        Arme arme1 = new Arme("bâton");
        Arme arme2 = new Arme("épée longue");
        Armure arme3 = new Armure("harnois");

        ajoutEquipement(arme1, "F7");
        ajoutEquipement(arme2, "B5");
        ajoutEquipement(arme3, "I2");
    }

    private void initDonjon2() {
        m_longueur = 16;
        m_largeur = 20;
        m_entites = new Hashtable<>();
        m_personnages = new Hashtable<>();
        m_monstres = new Hashtable<>();
        m_cases = new Hashtable<>(320);
        nommerCases();


        ajoutObstacle("A7");
        ajoutObstacle("C5");
        ajoutObstacle("F3");
        ajoutObstacle("K15");
        ajoutObstacle("O13");

        Arme arme1 = new Arme("bâton");
        Arme arme2 = new Arme("épée longue");
        Armure arme3 = new Armure("harnois");
        Armure arme4 = new Armure("demi-plate");

        ajoutEquipement(arme1, "F7");
        ajoutEquipement(arme2, "B5");
        ajoutEquipement(arme3, "I2");
        ajoutEquipement(arme4, "M15");
    }

    private void initDonjon3() {
        m_longueur = 18;
        m_largeur = 17;
        m_entites = new Hashtable<>();
        m_personnages = new Hashtable<>();
        m_monstres = new Hashtable<>();
        m_cases = new Hashtable<>(306);
        nommerCases();


        ajoutObstacle("A7");
        ajoutObstacle("C5");
        ajoutObstacle("F3");
        ajoutObstacle("K15");
        ajoutObstacle("p13");

        Arme arme1 = new Arme("bâton");
        Arme arme2 = new Arme("épée longue");
        Armure arme3 = new Armure("harnois");
        Armure arme4 = new Armure("demi-plate");

        ajoutEquipement(arme1, "F7");
        ajoutEquipement(arme2, "B5");
        ajoutEquipement(arme3, "I2");
        ajoutEquipement(arme4, "M15");

    }

    /// /////////////////////////////////////////



    // AJOUT D'UN PERSONNAGE
    public void ajoutPersonnage(Personnage e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);//ajouter le perso dans notre tableau d'entites
        m_personnages.put(e, idEntite);

        m_nb_personnages++;
    }


    // AJOUT D'UN MONSTRE
    public void ajoutMonstre(Monstre e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);//ajouter le monstre dans notre tableau d'entites
        m_monstres.put(e, idEntite);

        m_nb_monstres++;
    }

    public Hashtable<Personnage, Integer> getListePersonnages() {
        return m_personnages;
    }

    public Hashtable<Monstre, Integer> getListeMonstres() {
        return m_monstres;
    }

    //AJOUT D'UN OBSTACLE
    public boolean ajoutObstacle(String position) {
        int idObs = 700; //supperieur au nombre max de perso (taille du plateau max 625)
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getKey().equals(position)) {
                int[] val = caseEntry.getValue();
                val[0] = idObs;
                return true;
            }
        }
        return false; //si l'operation n'a pas eu lieu, position introuvable, on return faux
    }

    //AJOUT D'UN EQUIPEMENT
    public void ajoutEquipement(Equipement equip, String position) {
        int id = switch (equip.getNom()) {
            // Armures légères
            case "armure d'écailles" -> 1;
            case "demi-plate" -> 2;
            case "cotte de mailles" -> 3;
            case "harnois" -> 4;
            case "bâton" -> 5;
            case "masse d'armes" -> 6;
            case "épée longue" -> 7;
            case "rapière" -> 8;
            case "arbalète légère" -> 9;
            case "fronde" -> 10;
            case "arc court" -> 11;
            case "épée à deux mains" -> 12;
            default -> 20; //nombre au hasard
        };

        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getKey().equals(position)) {
                if(id != 20){
                    m_cases.put(position, new int[] {0, id});
                }
                break; // on a trouvé la bonne case et mis l'équipement, on sort de la boucle
            }
        }

    }


    //servira pour cree un equipement
    public String getEquipementParId(int id) {
        return switch (id) {
            case 1 -> "armure d'écailles";
            case 2 -> "demi-plate";
            case 3 -> "cotte de mailles";
            case 4 -> "harnois";
            case 5 -> "bâton";
            case 6 -> "masse d'armes";
            case 7 -> "épée longue";
            case 8 -> "rapière";
            case 9 -> "arbalète légère";
            case 10 -> "fronde";
            case 11 -> "arc court";
            case 12 -> "épée à deux mains";
            default -> "l'équipement n'existe pas";
        };
    }


    public int getId(Entite entite) {
        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            if (entry.getValue().equals(entite)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    protected Entite getEntiteParId(int id) {
        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private Boolean existeCase(String position) {
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getKey().equals(position)) {
                return true;
            }
        }
        return false;
    }

    /// POSITIONER ENTITE
    public String positionner(Entite e, String position) {
        int id = getId(e);
        if (id == 0) {
            return "Entité non existante";
        }
        if (!existeCase(position)) {
            return "Case non existante";
        }

        // Chercher l'ancienne position de l'entité (si elle existe)
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getValue()[0] == id) {
                caseEntry.getValue()[0] = 0; // vider ancienne case
                break; // on sort dès qu'on trouve
            }
        }

        // Positionner l'entité sur la nouvelle case
        int[] valeurs = m_cases.get(position);
        valeurs[0] = id;

        return e.getNom() + " a été positionné avec succès dans " + position;
    }


    // CONNAITRE L'ID DE CHAQUE ENTITE
    protected void afficherIDentite() {
        for (Map.Entry<Integer, Entite> entry : getEntites().entrySet()) { //pour chaque case de notre dico d'entite
            System.out.println("\t" + entry.getValue().getNom()+" --> " + entry.getKey());//le nom de l'entite
        }
    }


    private int getNombreEntites() {
        return m_entites.size();
    }

    private Hashtable<Integer, Entite> getEntites() {
        return m_entites;
    }

    protected Hashtable<String, int[]> getCases() {
        return m_cases;
    }


    //METHODE QUI NOMMERA TOUTES LES CASES DU PLATEAU
    private void nommerCases() {
        for (int i = 1; i <= m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                char lettre = (char) ('A' + j); // parcours l'alphabet
                String nomCase = "" + lettre + i; // concatenation
                m_cases.put(nomCase, new int[]{0, 0}); //la ligne du dico a un nom et les tabs pour le moment rien dessus
            }
        }
    }


    // ORDRE DE JEU: LISTE DES ENTITES PAR ORDRE D'INITIATIVES
    private ArrayList<Entite> ordreDeJeu() {
        ArrayList<Entite> joueurs = new ArrayList<>(m_entites.values()); //creation d'une liste de tous les perso
        for (int i = 0; i < joueurs.size() - 1; i++) { //pour chaque elem de la liste
            int Index = i;
            for (int j = i + 1; j < joueurs.size(); j++) {//je reparcours la liste, pour chque elem de la liste
                if (joueurs.get(j).getInitiative() > joueurs.get(Index).getInitiative()) { //si le joueur d'apres a un id plus grand
                    Index = j;
                }
            }
            // Échanger les éléments
            Entite temp = joueurs.get(i);
            joueurs.set(i, joueurs.get(Index));//je met le prochain perso dans la place de notre perso
            joueurs.set(Index, temp); //je met notre perso dans la place suivante
        }

        return joueurs; //le tableau des entite dans le bon ordre(il fadra le parcourir)
    }


    // Déplacement d'une entité
    public String seDeplacer(int entiteID, String direction) {
        Entite e = m_entites.get(entiteID);//je recupere l'entit eselon son id
        String retourne; //pour les phrases a l'affichage

        if (e == null) {
            return "Aucune entité avec cet ID dans le donjon";
        }

        for (Map.Entry<String, int[]> entry : m_cases.entrySet()) { //je parcours toutes mes cases
            String nomCase = entry.getKey();
            int[] valeurs = entry.getValue(); //le tableau des ID et equip

            if (valeurs[0] == entiteID) { //quand on trouve notre perso dans la map

                char lettre = nomCase.charAt(0); // colonne
                int numero = Integer.parseInt(nomCase.substring(1)); // ligne

                Entite joueur = m_entites.get(entiteID); //recup entite

                int distance = joueur.getDistance();
                // Calcul direction
                switch (direction.toLowerCase()) {
                    case "haut":
                        numero -= distance;
                        break;
                    case "bas":
                        numero += distance;
                        break;
                    case "gauche":
                        lettre -= distance;
                        break;
                    case "droite":
                        lettre += distance;
                        break;
                    case "diagonale haut gauche":
                        lettre -= distance;
                        numero -= distance;
                        break;
                    case "diagonale haut droite":
                        lettre += distance;
                        numero -= distance;
                        break;
                    case "diagonale bas gauche":
                        lettre -= distance;
                        numero += distance;
                        break;
                    case "diagonale bas droite":
                        lettre += distance;
                        numero += distance;
                        break;
                    default:
                        return "Direction invalide";
                }


                String nouvelleCase = Character.toString(lettre).toUpperCase() + numero;

                if (m_cases.containsKey(nouvelleCase)) { //si notre tableau de case a bien la case (on est pas aller trop haut par ex
                    int[] destination = m_cases.get(nouvelleCase); //on recup le tableau de cette nouvelle case
                    if (destination[0] == 0) { //aucun perso ou obstacle n'est dans cette case
                        destination[0] = entiteID;
                        m_cases.put(nouvelleCase, destination); // on met le tableau de l'ancienne case ici
                        m_cases.put(nomCase, new int[]{0, valeurs[1]}); // libérer l'ancienne case


                        retourne = (e.getNom() + " se déplace vers " + nouvelleCase);
                    } else {
                        retourne = ("Case occupée !");
                    }
                } else {
                    retourne = ("Case inexistante !");
                }

                return retourne;
            }
        }

        return ("Entité non trouvée dans le donjon.");
    }

    public int finDonjon(Personnage perso) {
        if (!perso.estVivant()) {
            return 0;//si qlq est mort
        }
        if (m_nb_monstres == 0) {
            return 1;        //si y'a plus de monstre
        }
        return 2;   //la partie continu
    }



    // Affichage du plateau
    public void affichagePlateau() {
        String[][] plateau = new String[m_longueur][m_largeur];

        // Initialiser toutes les cases à " . "
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                plateau[i][j] = " . ";
            }
        }

        // Placer les entités (personnages ou monstres)
        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            int id = entry.getKey();
            Entite e = entry.getValue();

            for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
                int[] valeurs = caseEntry.getValue();
                if (valeurs[0] == id) {
                    String caseNom = caseEntry.getKey();
                    int x = caseNom.charAt(0) - 'A';
                    int y = Integer.parseInt(caseNom.substring(1)) - 1;

                    String contenu = "";

                    // Vérifie si c'est un personnage
                    if (m_personnages.containsValue(id)) {
                        contenu = String.format("%2d ", id); // exemple " 1 "
                    }

                    // Vérifie si c'est un monstre
                    if (m_monstres.containsValue(id)) {
                        contenu = String.format("%-2dM", id); // exemple "3M "
                    }

                    plateau[y][x] = contenu;
                }
            }
        }

        // Placer les obstacles et équipements
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            String caseNom = caseEntry.getKey();
            int[] valeurs = caseEntry.getValue();
            int x = caseNom.charAt(0) - 'A';
            int y = Integer.parseInt(caseNom.substring(1)) - 1;

            // Obstacle
            if (valeurs[0] == 700) {
                plateau[y][x] = " x ";
            }

            // Equipement
            if (valeurs[1] != 0) {
                if (plateau[y][x].equals(" . ") || plateau[y][x].equals(" x ")) {
                    plateau[y][x] = plateau[y][x].substring(0, 2) + "*";
                } else {
                    // Ajouter une étoile si déjà une entité
                    if (!plateau[y][x].contains("*")) {
                        plateau[y][x] = plateau[y][x].substring(0, 2) + "*";
                    }
                }
            }
        }

        // Affichage en-tête colonne A B C…
        System.out.print("    ");
        for (int j = 0; j < m_largeur; j++) {
            System.out.print(" " + (char) ('A' + j) + " ");
        }
        System.out.println();

        // Affichage des lignes du plateau
        for (int i = 0; i < m_longueur; i++) {
            System.out.printf("%2d |", i + 1); // numéro de ligne
            for (int j = 0; j < m_largeur; j++) {
                System.out.print(plateau[i][j]);
            }
            System.out.println();
        }
    }



    public void affichageOrdre() {
        ArrayList<Entite> joueurs = new ArrayList<>(ordreDeJeu());

        for (Entite entite : joueurs) {
            System.out.println(entite.getNom());
        }
    }

    public ArrayList<Entite> getOrdre() {
        return ordreDeJeu();
    }

    public void affichageDeplacement(int entiteID, String direction) {
        System.out.println(seDeplacer(entiteID, direction));
    }

    public int getNumDonjons() {
        return m_num;
    }




}


