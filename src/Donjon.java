import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;
//il faut importer la classe perso et montres, pour le moment pour les tours
//import Monstres;
//import Joueurs;
import personnages.*;

public class Donjon {

    private int m_num;
    private int m_longueur;
    private int m_largeur;
    private int m_nb_joueurs;
    private int m_nb_montres;
    private Hashtable<String, int[]> m_cases;

    public Donjon(int num,int joueurs, int montres, int longueur, int largeur) {
        m_num = num;
        m_nb_joueurs = joueurs;
        m_nb_montres = montres;
        m_longueur = longueur;
        m_largeur = largeur;
        m_cases = new Hashtable<>(longueur*largeur);
    }

    public Donjon(int num, int joueurs, int montres) {
        m_num = num;
        m_nb_joueurs = joueurs;
        m_nb_montres = montres;
        m_longueur= (int)(Math.random() * (15 - 25 + 1)) + 15;
        m_largeur=(int)(Math.random() * (15 - 25 + 1)) + 15;
        m_cases = new Hashtable<>(m_longueur*m_largeur);
    }

    public Donjon(int num, int joueurs, int montres, int longueur) {
        m_num = num;
        m_nb_joueurs = joueurs;
        m_nb_montres = montres;
        m_longueur= longueur;
        m_largeur=(int)(Math.random() * (11)) + 15;  // entre 15 et 25
        m_cases = new Hashtable<>(m_longueur*m_largeur); //dictionaire de cases
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


//on met les monstres, obstacles joueurs et equipements
    public void remplirPlateau() {
        int players = m_nb_joueurs+m_nb_montres;
        int equip= (int) (Math.random() * (2));

        for (int[] tableau : m_cases.values()) { //pour chaque tableau de notre dictionnaire
            tableau[0]=(int)(Math.random() * (players + 1));
            //un nombre entre 0 et le total des joueurs et monstres
            if (equip == 1) { //si equipement est a 1 on met sur la case un type d'equipement
                tableau[1] = tableau[0] = (int) (Math.random() *  6) + (players + 2); //entre le nombre de players +2, et players +7, donc 5 equipements
            }
            else { //si equip est a 0 on laisse la case vide
                tableau[1] = 0;
            }
        }
    }

    public void ordreDeJeu() {
        ArrayList<int[]> tours = new ArrayList<>();

        for (int i = 0; i < m_nb_joueurs + m_nb_montres; i++) {
            int[] temp = new int[2];
            temp[0] = i + 1; // joueur/monstre Id
            temp[1] = (int) (Math.random() * 21); // jet random entre 0 et 20
            tours.add(temp);
        }

        // Tri
        for (int i = 0; i < tours.size(); i++) {
            for (int j = 0; j < tours.size() - 1 - i; j++) {
                if (tours.get(j)[1] < tours.get(j + 1)[1]) { //verif la case 1 du tab j et j+1
                    int[] temp = tours.get(j);
                    tours.set(j, tours.get(j + 1));
                    tours.set(j + 1, temp);
                }
            }
        }



        //affichage des tours
        //suprimable si marche mal
        System.out.println("Lancement des des! qui pourra avancer en premier? *roulement de tambours*");
        for (int[] player : tours) {
            System.out.println("Joueur/Monstre " + player[0] + " a un score de : " + player[1]);
        }

        System.out.println("L'ordre sera donc: ");
        for (int[] player : tours) {
            System.out.println("" + player[0]);
        }

    }

    //toutes directions???????
    public void seDeplacer(int perso, String direction) {
        // int distance=perso.getVitesse() / 3;
        int distance =3; //a retier quand on aura la vitesse de chaque perso
        //bouger gauche droite charB +- 1
        //bouger haut bas 2 +-1

        //parcours toute cases du dico
        for (Map.Entry<String, int[]> emplacement : m_cases.entrySet()) {
            String nomCase = emplacement.getKey();      // nom de la case
            int[] valeurs = emplacement.getValue();     // par exemple {0, 0}

            if (valeurs[0] == perso) {
                //recupere position exacte colonne et ligne
                char lettre = nomCase.charAt(0);                    // Prend le 1er caractère
                int numero = Integer.parseInt(nomCase.substring(1)); // prend tout sauf 1 caractere(le premier) et converti le rreste en int

                int numHaut= numero - distance;
                char lettreGauche = (char)(lettre - distance);
                int numBas= numero + distance;
                char lettreDroite = (char)(lettreGauche + distance);

                //verification des differentes directions


            }
        }
    }
}


