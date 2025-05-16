import equipement.Arme;
import equipement.Armure;
import equipement.Equipement;
import personnages.Personnage;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import personnages.Monstre;
import personnages.Entite;



public class Donjon {
    //LONGEUR C'EST LES CHIFFRES (LIGNES)
    //LARGEUR C'EST LES LETTRES (COLONNES)

    //==========attributs====================
    private int m_num;
    private int m_nb_monstres;
    private int m_nb_personnages;
    private int m_longueur;
    private int m_largeur;
    private Hashtable<Integer, Entite> m_entites;
    private Hashtable<String, int[]> m_cases;
    //===========================================


    //===========constructeurs=========================================
    public Donjon(int num, int longueur, int largeur) {
        m_num = num;
        m_longueur = longueur;
        m_largeur = largeur;
        m_entites = new Hashtable<>();
        m_cases = new Hashtable<>(longueur * largeur);
        nommerCases();
    }

    public Donjon(int num) { //par defaut
        m_num = num;
        switch (num){
            case 1:initDonjon1(); break;
            case 2:initDonjon2(); break;
            case 3:initDonjon3(); break;
            default:System.out.println("model de donjon inexistant");
        }
    }
    //============================================================================



    /// ////PAR DEFAUT////////////
    /// choix des tailles par defaut
    /// choix des obstacles et equipements par defaut
    ///
    public void initDonjon1() {
        m_longueur=7;
        m_largeur=10;
        m_entites = new Hashtable<>();
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

    public void initDonjon2() {
        m_longueur=16;
        m_largeur=20;
        m_entites = new Hashtable<>();
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

    public void initDonjon3() {
        m_longueur=18;
        m_largeur=17;
        m_entites = new Hashtable<>();
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

        m_nb_personnages++;
    }


    // AJOUT D'UN MONSTRE
    public void ajoutMonstre(Monstre e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);//ajouter le monstre dans notre tableau d'entites

        m_nb_monstres++;
    }

   //AJOUT D'UN OBSTACLE
    public boolean ajoutObstacle(String position) {
        int idObs = 700; //supperieur au nombre max de perso (taille du plateau max 625)
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getKey().equals(position)) {
                int [] val= caseEntry.getValue();
                val[0]=idObs;
                return true;
            }
        }
        return false; //si l'operation n'a pas eu lieu, position introuvable, on return faux
    }

    //AJOUT D'UN EQUIPEMENT
    public String ajoutEquipement(Equipement equip, String position) {
        int id;
        switch(equip.getNom()) {
            // Armures légères
            case  "armure d'écailles": id = 1; break;
            case "demi-plate": id = 2; break;
            case "cotte de mailles": id =3; break;
            case "harnois": id =4; break;
            case "bâton": id =5; break;
            case "masse d'armes": id =6; break;
            case "épée longue": id =7; break;
            case "rapière": id =8; break;
            case "arbalète légère": id =9; break;
            case "fronde": id =10; break;
            case "arc court": id =11; break;

            default: id =20; //nombre au hasard
        }
        int [] val=null;



        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getKey().equals(position)) {
                val = caseEntry.getValue();
                break; // on a trouvé la bonne case, on sort de la boucle
            }
        }
        if (val!=null && id!=20) {
            val[1]=id; //je mets l'id de l'equipement dans la deuxieme case du dico des positions
            return "equipement ajouté au plateau avec succes";
        }

        return "l'equipement n'existe pas";
    }


    //servira pour cree un equipement
    public String getNomEquipementParId (int id) {
        switch (id) {
            case 1: return "armure d'écailles";
            case 2: return "demi-plate";
            case 3: return "cotte de mailles";
            case 4: return "harnois";
            case 5: return "bâton";
            case 6: return "masse d'armes";
            case 7: return "épée longue";
            case 8: return"rapière";
            case 9: return"arbalète légère";
            case 10: return "fronde";
            case 11 : return "arc court";

            default: return "l'equipement n'existe pas";
        }
    }



    public int get_id (Entite entite) {
        for(Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            if (entry.getValue().equals(entite)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    private Boolean existe_case(String position) {
        for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) {
            if (caseEntry.getKey().equals(position)) {
                return true;
            }
        }
        return false;
    }
    ///POSITIONER ENTITE
    public String positionner(Entite e, String position) {
        int id = get_id(e);
        if (id == 0) {
            return "Entité non existante";
        }
        if (!existe_case(position)) {
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
        return m_entites;
    }



    //METHODE QUI NOMMERA TOUTES LES CASES DU PLATEAU
    public void nommerCases() {
        for (int i = 1; i <= m_longueur; i++) {
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
                        m_cases.put(nomCase, new int[]{0, 0}); // libérer l'ancienne case


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

            if (valeurs[0] == 700) {
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
