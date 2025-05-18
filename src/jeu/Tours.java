package jeu;

import equipement.Arme;
import equipement.Armure;
import equipement.Equipement;
import personnages.Monstre;
import personnages.Personnage;


import java.util.Map;
import java.util.Scanner;

import personnages.Entite;

//cette fonction devra être appelée dans une boucle qui parcourt tous les joueurs dans l'ordre
//la liste des joueurs est disponible dans la classe donjon, donjon.afficher ordre
//si nécéssaire une fonction qui renvoit la liste peut être créée pour faciliter le travail du main


public class Tours {
    private final int m_nu;

    public Tours(int num) {
        this.m_nu = num;

    }

    public void ajouterTourPersonnage(Personnage personnage, Donjon donjon, Narrateur narrateur){
        Scanner scan = new Scanner(System.in);


        System.out.println("=====================================================================================");
        System.out.println("Donjon : " + donjon.getNumDonjons());
        System.out.println("Tour : " + m_nu);
        System.out.println("\n\t Les personnages et monstres joueront comme suit :");
        donjon.affichageOrdre();

        System.out.println("\nAller " + personnage.getNom() + ", à toi de jouer !");
        System.out.println("=====================================================================================");


        System.out.println("Voici l'id de tout le monde :");
        donjon.afficherIDentite();
        System.out.println("\n");
        donjon.affichagePlateau();
        System.out.println("Les monstres sont suivis d'un M || les équipements sont représentés par des *\n\n");

        System.out.println("=====================================================================================");
        int id = donjon.getId(personnage);

        personnage.toStringDetails();


        for (int i = 3; i > 0; i--) {
            System.out.println(personnage.toStringDetails());

            System.out.println(" vous avez " + i + " action(s), que choisissez-vous? \n" +
                    "[1] s'équiper\n" +
                    "[2] se déplacer\n" +
                    "[3] attaquer\n" +
                    "[4] ramasser un équipement\n");
            String choix = scan.nextLine();
            switch (choix) {
                case "1" -> {
                    System.out.println("Vous souhaitez vous équiper, voici un rappel de votre inventaire :");
                    System.out.println(personnage.afficherInventaire());
                    int choixEquipement;
                    do{
                        System.out.println("Choisissez un nombre entre 1 et " + personnage.tailleInventaire());
                        choixEquipement = scan.nextInt();
                        scan.nextLine();
                    }while(choixEquipement < 1 || choixEquipement > personnage.tailleInventaire());

                    Equipement equipement = personnage.getEquipement(choixEquipement);

                    if(equipement.getClasse().equals("Arme")){
                        Arme arme = (Arme) equipement;
                       personnage.equiper(arme);
                        System.out.println("Vous avez équipé " + arme.toString());
                    }
                    else{
                        Armure armure = (Armure) equipement;
                        personnage.equiper(armure);
                        System.out.println("Vous avez équipé " + armure.toString());
                    }

                }
                case "2" -> {
                    int distance=personnage.getDistance();
                    String dis= String.valueOf(distance);

                    System.out.println("Vous souhaitez changer de position, dites en plus, où voulez-vous aller ? Le déplacement sera de " + dis + " cases");
                    System.out.println("[1] haut | [2] bas | [3] gauche | [4] droite | [5] diagonale haut gauche | [6] diagonale haut droite | [7] diagonale bas gauche | [8] diagonale bas droite");
                    String direction= scan.nextLine();
                    String position;

                    switch (direction) {
                        case "1":
                            position = "haut";
                            break;
                        case "2":
                            position = "bas";
                            break;
                        case "3":
                            position = "gauche";
                            break;
                        case "4":
                            position = "droite";
                            break;
                        case "5":
                            position = "diagonale haut gauche";
                            break;
                        case "6":
                            position = "diagonale haut droite";
                            break;
                        case "7":
                            position = "diagonale bas gauche";
                            break;
                        case "8":
                            position = "diagonale bas droite";
                            break;
                        default:
                            position = "invalide";
                            break;
                    }


                    if (position.equals("invalide")) {
                        System.out.println("Direction invalide. Pas de déplacement.");
                    } else {
                        String deplacement = donjon.seDeplacer(id, position);
                        donjon.affichagePlateau();
                        System.out.println(deplacement);
                    }

                }

                case "3" -> {
                    System.out.println("La meilleure défense est l'attaque, qui voulez-vous attaquer ? (un id suffit)");
                    int decision = scan.nextInt(); //donne id de la cible
                    scan.nextLine();

                    Entite cible = donjon.getEntiteParId(decision); //je recupere la cible
                    if (cible != null) { //si la cible existe bien
                        String attaquer=personnage.attaquer(cible); //on l'attaque
                        System.out.println(attaquer);
                        if(!cible.estVivant()){
                            donjon.m_nb_monstres--;
                            if(donjon.m_nb_monstres <=0){
                                return;
                            }
                        }
                    }
                    else {
                        System.out.println("Attaque impossible");
                    }
                }

                case "4" -> {
                    System.out.println("Vous voulez ramassez l'équipement à vos pieds...");
                    int armeId;

                    for (Map.Entry<String, int[]> positions : donjon.getCases().entrySet()) { //on parcours le tab de caes
                        if (positions.getValue()[0] == id) { //si dans cette case on a le perso
                            armeId = positions.getValue()[1]; //on recup l'id de l'equiment dispo
                            if (armeId <= 0) continue;

                            String equipement = String.valueOf((armeId));     //on recup le nom de cet equipement
                            if (armeId>4 && armeId<12) {                //si l'id est plus grand que 4 c'est une arme
                                Arme arme = new Arme(equipement);           //on cree cette arme
                                personnage.ajoutEquipement(arme);     //on l'ajoute à son inventaire
                                System.out.println("Vous avez ramassé : " + arme.toString());
                            } else if (armeId>0 && armeId<5) {          //si l'id est plus petit que 5 c'est une armure
                                Armure armure = new Armure(equipement);       //on cree l'armure
                                personnage.ajoutEquipement(armure);           //on l'ajoute à son inventaire
                                System.out.println("Vous avez ramassé : " + armure.toString());
                            }
                        }

                    }
                }

                default -> {
                    System.out.println("Mauvais choix, recommencez.");
                    i++;
                }
            }

            donjon.affichagePlateau(); //j'affiche le plateau apres chaque choix

            System.out.println(personnage.getNom() + ", voulez-vous commenter l'action précédente ? (o/n)");
            String action = scan.nextLine();

            if(action.equalsIgnoreCase("o")) {
                System.out.println("Ecrivez votre commentaire...");
                String commentaire = scan.nextLine();
            }
            else {
                System.out.println(narrateur.getPseudo() + " voulez-vous ajouter quelque chose ?");
                action = scan.nextLine();
                if(action.equalsIgnoreCase("o")) {
                    System.out.println("Ecrivez votre commentaire...");
                    String commentaire = scan.nextLine();
                }
            }
            System.out.println();

        }

    }



    public void ajouterTourMonstre(Monstre monstre, Donjon donjon, Narrateur narrateur){
        Scanner scan = new Scanner(System.in);


        System.out.println("=====================================================================================");
        System.out.println("Donjon : " + donjon.getNumDonjons());
        System.out.println("Tour : " + m_nu);

        donjon.affichageOrdre();

        System.out.println("Le monstre "+monstre.getNom()+" est prêt à jouer, préparez-vous !");
        System.out.println("=====================================================================================");

        monstre.toStringDetails();
        int id = donjon.getId(monstre);

        donjon.afficherIDentite();


        donjon.affichagePlateau();
        System.out.println("Les monstres sont suivis d'un M|| les équipements sont représentés par des *\n\n");

        System.out.println("=====================================================================================");
        monstre.toStringDetails();


        for (int i = 3; i > 0; i--) {
            System.out.println(monstre.toStringDetails());

            System.out.println(" vous avez " + i + " action(s), que choisissez-vous ? \n " +
                    "[1] attaquer\n" +
                    "[2] se déplacer");
            String choix = scan.nextLine();
            switch (choix) {
                case "1" -> {
                    /*
                    System.out.println("Attention, "+narrateur.getPseudo()+" a pris la parole. Merci de l'écouter.");
                    String nouveau = scan.nextLine();
                    narrateur.commenter(nouveau);
                     */

                    System.out.println(monstre.getNom()+", qui va être votre victime ? (un id suffit)");
                    int decision = scan.nextInt(); //donne id de la cible
                    scan.nextLine();

                    Entite cible = donjon.getEntiteParId(decision); //je récupère la cible
                    if (cible != null) { //si la cible existe bien
                        String attaquer=monstre.attaquer(cible); //on l'attaque
                        System.out.println(attaquer);
                        if(!cible.estVivant()){
                            donjon.m_nb_personnages--;
                            if(donjon.m_nb_personnages <=0){
                                return;
                            }
                        }

                    }
                    else {
                        System.out.println("Attaque impossible");
                    }

                }

                case "2" -> {
                    /*le personnage peut dire des trucs?
                    System.out.println(monstre.getNom() + " souhaite commenter : ");
                    String commentaire = scan.nextLine();
                    narrateur.commenter(commentaire);
                     */

                    int distance =monstre.getDistance();
                    String dis= String.valueOf(distance);
                    System.out.println("Une stratégie se met en place, quelle position est la plus adaptée ? Pas plus de "+distance+" cases");
                    System.out.println("[1] haut | [2] bas | [3] gauche | [4] droite | [5] diagonale haut gauche | [6] diagonale haut droite | [7] diagonale bas gauche | [8] diagonale bas droite");
                    String direction= scan.nextLine();
                    String position;

                    switch (direction) {
                        case "1":
                            position = "haut";
                            break;
                        case "2":
                            position = "bas";
                            break;
                        case "3":
                            position = "gauche";
                            break;
                        case "4":
                            position = "droite";
                            break;
                        case "5":
                            position = "diagonale haut gauche";
                            break;
                        case "6":
                            position = "diagonale haut droite";
                            break;
                        case "7":
                            position = "diagonale bas gauche";
                            break;
                        case "8":
                            position = "diagonale bas droite";
                            break;
                        default:
                            position = "invalide";
                            break;
                    }

                    if (position.equals("invalide")) {
                        System.out.println("Direction invalide. Pas de déplacement.");
                    } else {
                        String deplacement = donjon.seDeplacer(id, position);
                        System.out.println(deplacement);
                    }

                }

                default -> {
                    System.out.println("Mauvais choix, recommencez.");
                    i++;
                }
            }
            donjon.affichagePlateau(); //j'affiche le plateau apres chaque choix

            System.out.println(monstre.getNom() + ", voulez-vous commenter l'action précédente ? (o/n)");
            String action = scan.nextLine();

            if(action.equalsIgnoreCase("o")) {
                System.out.println("Ecrivez votre commentaire...");
                String commentaire = scan.nextLine();
            }
            else {
                System.out.println(narrateur.getPseudo() + " voulez-vous ajouter quelque chose ?");
                action = scan.nextLine();
                if(action.equalsIgnoreCase("o")) {
                    System.out.println("Ecrivez votre commentaire...");
                    String commentaire = scan.nextLine();
                }
            }
            System.out.println();

        }
        //on retourne fin pour savoir si ce tour a conduit vers la fin d'un donjon ou pas

    }

}
