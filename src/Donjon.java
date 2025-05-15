import personnages.Personnage;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import personnages.Monstre;
import personnages.Entite;

public class Donjon {
    private int m_num;
    private int m_nb_monstres;
    private int m_nb_personnages;
    private int m_longueur;
    private int m_largeur;
    private Hashtable<Integer, Entite> m_entites;
    private Hashtable<String, int[]> m_cases;

    public Donjon(int num, int longueur, int largeur) {
        m_num = num;
        m_longueur = longueur;
        m_largeur = largeur;
        m_entites = new Hashtable<>();
        m_cases = new Hashtable<>(longueur * largeur);
    }

    public Donjon(int num) {
        m_num = num;
        switch (num){
            case 1:initDonjon1();break;
            case 2:initDonjon2();break;
            case 3:initDonjon3();break;
        }

    }




    /// ////PAR DEFAUT////////////
    private void initDonjon1() {
        m_longueur=7;
        m_largeur=10;
        m_entites = new Hashtable<>();
        m_cases = new Hashtable<>(70);
    }
    private void initDonjon2() {
        m_longueur=16;
        m_largeur=20;
        m_entites = new Hashtable<>();
        m_cases = new Hashtable<>(320);
    }
    private void initDonjon3() {
        m_longueur=18;
        m_largeur=17;
        m_entites = new Hashtable<>();
        m_cases = new Hashtable<>(306);
    }
    /// /////////////////////////////////////////




    // AJOUT D'UN PERSONNAGE
    public void ajoutPersonnage(Personnage e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);//ajouter le perso dans notre tableau d'entites

        m_nb_personnages++;
    }



    // AJOUT D'UN MONSTRE
    public void ajoutMonstre(Monstre e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);//ajouter le monstre dans notre tableau d'entites

        m_nb_monstres++;
    }

    public void ajoutObstacle(int obstacle) {

    }



    // CONNAITRE L'ID DE CHAQUE ENTITE
    public void afficherID() {
        System.out.println("Nous avons ajouté à la partie :");
        for (Map.Entry<Integer, Entite> entry : getEntites().entrySet()) { //pour chaque case de notre dico d'entite
            System.out.println(entry.getValue().getNom());//le nom de l'entite
            System.out.println(" --> "+entry.getKey()+"\n");//son id dans la game
        }
    }



    public int getNombreEntites() {
        return m_entites.size();
    }

    public Hashtable<Integer, Entite> getEntites() {
        ;
        return m_entites;
    }



    //METHODE QUI NOMMERA TOUTES LES CASES DU PLATEAU
    private void nommerCases() {
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                char lettre = (char) ('A' + j); // parcours l'alphabet
                String nomCase = "" + lettre + i; // concatenation
                m_cases.put(nomCase, new int[]{0, 0}); //la ligne du dico a un nom et les tabs pour le moment rien dessus
            }
        }
    }


    // ORDRE DE JEU: LISTE DES ENTITES PAR ORDRE D'INITIATIVES
    public ArrayList<Entite> ordreDeJeu() {
        ArrayList<Entite> joueurs = new ArrayList<>(m_entites.values()); //creation d'une liste de tous les perso
        // On attribue un score aléatoire a chaque entite
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
            return "Aucune entité avec cet ID dans le donjon" ;}

        for (Map.Entry<String, int[]> entry : m_cases.entrySet()) { //je parcours toutes mes cases
            String nomCase = entry.getKey();
            int[] valeurs = entry.getValue(); //le tableau des ID et equip

            if (valeurs[0] == entiteID) { //quand on trouve notre perso dans la map

                char lettre = nomCase.charAt(0); // colonne
                int numero = Integer.parseInt(nomCase.substring(1)); // ligne

                Entite joueur= m_entites.get(entiteID); //recup entite

                int distance =joueur.getDistance();
                // Calcul direction
                switch (direction.toLowerCase()) {
                    case "haut": numero -= distance; break;
                    case "bas": numero += distance; break;
                    case "gauche": lettre -= distance; break;
                    case "droite": lettre += distance; break;
                    case "diagonale haut gauche": lettre -= distance; numero -= distance; break;
                    case "diagonale haut droite": lettre += distance; numero -= distance; break;
                    case "diagonale bas gauche": lettre -= distance; numero += distance; break;
                    case "diagonale bas droite": lettre += distance; numero += distance; break;
                    default:
                        return "Direction invalide";
                }


                String nouvelleCase = "" + lettre + numero; //on accede a la nouvelle case

                if (m_cases.containsKey(nouvelleCase)) { //si notre tableau de case a bien la case (on est pas aller trop haut par ex
                    int[] destination = m_cases.get(nouvelleCase); //on recup le tableau de cette nouvelle case
                    if (destination[0] == 0) { //aucun perso ou obstacle n'est dans cette case
                        m_cases.put(nouvelleCase, valeurs); // on met le tableau de l'ancienne case ici
                        m_cases.put(nomCase, new int[]{0}); // libérer l'ancienne case
                        retourne=(e.getNom() + " se déplace vers " + nouvelleCase);
                    } else {
                        retourne=("Case occupée !");
                    }
                } else {
                    retourne=("Case inexistante !");
                }

                return retourne;
            }
        }

        return ("Entité non trouvée dans le donjon.");
    }


    public String finDonjon(Personnage perso) {
        if (!perso.estVivant()) {
            return (perso.getNom()+" est mort, votre équipe a échoué le Donjon "+m_num+"...");
        }
        if (m_nb_monstres==0) {
            return ("Victoire! donjon : " +m_num+" terminé \n"+"Félicitation! tous les monstres on été vaincus, et l'équipe est au complet. Á bientot dans un nouveau Donjon :)");
        }
        return "l'equipe est au complet: "+m_nb_personnages+"\n"+"il reste encore "+m_nb_monstres+" à vaincre, courage!";

    }




    // Affichage du plateau
    public void Affichage_plateau() {
        int tour = 1;
        String[][] plateau = new String[m_longueur][m_largeur];

        //affichage entete du plateau
        System.out.println("Donjon "+m_num+":\n");
        if (tour>m_entites.size()) {
            System.out.println("tour "+tour+"\t\t"+m_entites.get(tour- (m_entites.size()) ).getNom()); //dans le tableau des personnages (dans l'ordre, on prend le nom du perso avec l'id
            System.out.println("À toi de jouer!");

        }
        else {
            System.out.println("tour "+tour+"\t\t"+m_entites.get(tour).getNom());
            System.out.println("À toi de jouer!");
        }

        // Initialiser toutes les cases à '.'
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                plateau[i][j] = ".";
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

                    if (e instanceof Personnage) {
                        plateau[y][x] = (String) ("" + id);
                    } else if (e instanceof Monstre) {
                        plateau[y][x] = (String) (("" + id));
                        plateau[y][x] += 'M';
                    }
                }
            }
        }

        // Placer les obstacles et équipements
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            String caseNom = caseEntry.getKey();
            int[] valeurs = caseEntry.getValue();
            int x = caseNom.charAt(0) - 'A';
            int y = Integer.parseInt(caseNom.substring(1)) - 1;

            if (valeurs[0] > m_entites.size()) {
                plateau[y][x] = "x";
            }

            if (valeurs[1] != 0) {
                // Ajout d’un * si équipement
                if (plateau[y][x] == ".") {
                    plateau[y][x] = "*";
                } else {
                    // fusionner l’équipement avec un autre élément
                    plateau[y][x] += "*";
                }
            }
        }

        // Affichage en-tête colonne A B C ...
        System.out.print("   ");
        for (int j = 0; j < m_largeur; j++) {
            System.out.print(" " + (char) ('A' + j));
        }
        System.out.println();

        // Affichage des lignes du plateau
        for (int i = 0; i < m_longueur; i++) {
            System.out.printf("%2d ", i + 1); // numéro de ligne
            for (int j = 0; j < m_largeur; j++) {
                System.out.print(" " + plateau[i][j]);
            }
            System.out.println();
        }
    }

    public void Affichage_ordre() {
        System.out.println(ordreDeJeu());
    }

    public void Affichage_deplacement(int entiteID, String direction) {
        System.out.println(seDeplacer(entiteID, direction));
    }

    public void Affichage_tours() {
        Affichage_plateau();
        System.out.println("les monstres sont suivis d'un M|| les equipements sont représentés par des *\n\n");
        getEntites();
    }


    }
