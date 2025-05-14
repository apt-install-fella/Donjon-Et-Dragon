import personnages.Personnage;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import personnages.Monstre;
import personnages.Entite;

public class Donjon {
    private int m_num;
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

    // Ajouter une entité (personnage ou monstre)
    public void ajoutEntite(Entite e) {
        int idEntite = m_entites.size() + 1;
        m_entites.put(idEntite, e);
    }

    // Ordre de jeu (en fonction des scores des dés)
    public void ordreDeJeu() {
        Random random = new Random();
        Hashtable<Integer, Integer> scores = new Hashtable<>();

        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) {
            int id = entry.getKey();
            Entite e = entry.getValue();

            int score = 1 + random.nextInt(20);
            scores.put(id, score);

            System.out.println(e.getNom() + " a obtenu : " + score);
        }

        ArrayList<Integer> ids = new ArrayList<>(m_entites.keySet());
        ids.sort((id1, id2) -> Integer.compare(scores.get(id2), scores.get(id1)));

        System.out.println("\nOrdre de jeu :");
        for (int id : ids) {
            System.out.println("- " + m_entites.get(id).getNom());
        }
    }

    // Déplacement d'une entité
    public void seDeplacer(int entitéID, String direction) {
        Entite e = m_entites.get(entitéID);
        if (e == null) {
            System.out.println("Aucune entité avec cet ID.");
            return;
        }

        for (Map.Entry<String, int[]> entry : m_cases.entrySet()) {
            String nomCase = entry.getKey();
            int[] valeurs = entry.getValue();

            if (valeurs.length > 0 && valeurs[0] == entitéID) {
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
                        System.out.println("Direction invalide");
                        return;
                }

                String nouvelleCase = "" + lettre + numero;

                if (m_cases.containsKey(nouvelleCase)) {
                    int[] destination = m_cases.get(nouvelleCase);
                    if (destination.length == 0 || destination[0] == 0) {
                        m_cases.put(nouvelleCase, valeurs); // déplacer
                        m_cases.put(nomCase, new int[]{0}); // libérer l'ancienne case
                        System.out.println(e.getNom() + " se déplace vers " + nouvelleCase);
                    } else {
                        System.out.println("Case occupée !");
                    }
                } else {
                    System.out.println("Case inexistante !");
                }

                return;
            }
        }

        System.out.println("Entité non trouvée dans le donjon.");
    }

    // Affichage du plateau
    public void Affichage_plateau() {
        char[][] plateau = new char[m_longueur][m_largeur];

        // Initialiser le plateau avec des points (cases vides)
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                plateau[i][j] = '.'; // '.' représente une case vide
            }
        }

        // Affichage des entités sur le plateau
        for (Map.Entry<Integer, Entite> entry : m_entites.entrySet()) { //pour chaque perso/monstre
            int id = entry.getKey();
            Entite e = entry.getValue();

            for (Map.Entry<String, int[]> caseEntry : m_cases.entrySet()) { //on parcours tout le plateau
                String caseNom = caseEntry.getKey(); //le nom de la case A2
                int[] caseValeurs = caseEntry.getValue(); //le tableau a deux cases de cette case

                if (caseValeurs[0] == id) {//si la premiere case du tab a la meme valeur qur l'id
                    int x = caseNom.charAt(0) - 'A'; // Calculer la colonne (en fonction du nom de la case)
                    int y = Integer.parseInt(caseNom.substring(1)) - 1; // Calculer la ligne

                    // Si c'est un personnage, on affiche son ID
                    if (e instanceof Personnage) {
                        plateau[y][x] = (char) ('0' + id); // Affichage de l'ID du personnage
                    }
                    // Si c'est un monstre, on affiche l'ID et un "M"
                    else if (e instanceof Monstre) {
                        plateau[y][x] = (char) ('0' + id); // Affichage de l'ID du monstre
                        plateau[y][x]=+'M'; // Ajout d'un "M" pour indiquer que c'est un monstre
                    }
                }
            }
        }

        // Gestion des obstacles et des équipements
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) { // Parcours de la grille
                String caseNom = "" + (char) ('A' + j) + (i + 1); // Nom de la case
                char currentChar = plateau[i][j]; // Valeur actuelle de la case

                // Vérifier si l'ID dans la première position du tableau m_cases est supérieur à la taille de m_entites
                if (m_cases.containsKey(caseNom)) {
                    int[] caseValeurs = m_cases.get(caseNom);

                    // Si l'ID est supérieur à la taille de m_entites, afficher un obstacle (x)
                    if (caseValeurs.length > 0 && caseValeurs[0] > m_entites.size()) {
                        plateau[i][j] = 'x'; // Afficher un 'x' pour un obstacle
                    }

                    // Vérifier si le deuxième élément du tableau est différent de 0 (il y'a equipement)
                    if (caseValeurs[1] != 0) {
                        if (currentChar == '.') {
                            plateau[i][j] = '*'; // Remplacer le point par un '*' si la case est occupée
                        }
                        else {
                            plateau[i][j] = (char) (currentChar + '*'); // Ajouter '*' à ce qui existe déjà
                        }
                    }
                }
            }
        }

        // Affichage final du plateau
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                System.out.print(plateau[i][j] + " ");
            }
            System.out.println();
        }
    }

}
