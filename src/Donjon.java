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

    // Ajouter une entité (personnage ou monstre) avec un id
    public void ajoutEntite(Entite e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);//ajout de l'entite dans notre tableau d'entites

        if (e instanceof Monstre) m_nb_monstres++;
        else if (e instanceof Personnage) m_nb_personnages++;
    }

    public void afficherEntites() {
        System.out.println("Nous avons ajouté à la partie :");
        for (Map.Entry<Integer, Entite> entry : getEntites().entrySet()) {
            System.out.println(entry.getValue().getNom());
        }
    }



    public int getNombreEntites() {
        return m_entites.size();
    }

    public Hashtable<Integer, Entite> getEntites() {
        ;
        return m_entites;
    }



    //methode qui nommerra toutes les cases du plateau
    public void nommerCases() {
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                char lettre = (char) ('A' + j); // parcours l'alphabet
                String nomCase = "" + lettre + i; // concatenation
                m_cases.put(nomCase, new int[]{0, 0}); //la ligne du dico a un nom et les tabs pour le moment rien dessus
            }
        }
    }



    public void getCases() {
        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            System.out.println(entry.getValue().getNom());
            System.out.println("--> "+entry.getKey()+"\n");
        }
    }



    // Ordre de jeu (en fonction des scores des dés)
    public StringBuilder ordreDeJeu() {
        Random random = new Random();
        Hashtable<Integer, Integer> scores = new Hashtable<>();

        StringBuilder retourne = new StringBuilder();

        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            int id = entry.getKey();
            Entite e = entry.getValue();

            int score = 1 + random.nextInt(20);
            scores.put(id, score);

        }

        ArrayList<Integer> ids = new ArrayList<>(m_entites.keySet());
        ids.sort((id1, id2) -> Integer.compare(scores.get(id2), scores.get(id1)));

        retourne.append("\nOrdre de jeu :");
        for (int id : ids) {
            retourne.append("- " + m_entites.get(id).getNom());
        }

        return retourne;
    }

    // Déplacement d'une entité
    public String seDeplacer(int entiteID, String direction) {
        Entite e = m_entites.get(entiteID);
        String retourne;
        if (e == null) {
            return "Aucune entité avec cet ID.";
        }

        for (Map.Entry<String, int[]> entry : m_cases.entrySet()) {
            String nomCase = entry.getKey();
            int[] valeurs = entry.getValue();

            if (valeurs.length > 0 && valeurs[0] == entiteID) {
                char lettre = nomCase.charAt(0); // colonne
                int numero = Integer.parseInt(nomCase.substring(1)); // ligne

                // Calcul direction
                switch (direction.toLowerCase()) {
                    case "haut": numero -= 1; break;
                    case "bas": numero += 1; break;
                    case "gauche": lettre -= 1; break;
                    case "droite": lettre += 1; break;
                    case "diagonale haut gauche": lettre -= 1; numero -= 1; break;
                    case "diagonale haut droite": lettre += 1; numero -= 1; break;
                    case "diagonale bas gauche": lettre -= 1; numero += 1; break;
                    case "diagonale bas droite": lettre += 1; numero += 1; break;
                    default:
                        return "Direction invalide";
                }

                String nouvelleCase = "" + lettre + numero;

                if (m_cases.containsKey(nouvelleCase)) {
                    int[] destination = m_cases.get(nouvelleCase);
                    if (destination.length == 0 || destination[0] == 0) {
                        m_cases.put(nouvelleCase, valeurs); // déplacer
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
