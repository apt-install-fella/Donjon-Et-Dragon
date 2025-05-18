package jeu;

import equipement.Arme;
import equipement.Armure;
import personnages.Monstre;
import personnages.Personnage;


import java.util.Map;
import java.util.Scanner;

import personnages.Entite;

//cette fonction devra etre appelée dans une boucle qui parcours tous les joueurs dans l'ordre
//la liste des joueurs est disponible dans la classe donjon, donjon.afficher ordre
//si nécéssaire une foncttion qui renvoit la liste peut etre crée pour faciliter le travail du main


public class Tours {
    private final int m_nu;

    public Tours(int num) {
        this.m_nu = num;

    }

    public void ajouterTourPersonnage(Personnage personnage, Donjon donjon, Narrateur narrateur){
        Scanner scan = new Scanner(System.in);


        System.out.println("=====================================================================================");
        System.out.println("donjon : " + donjon.getNumDonjons());
        System.out.println("Tours : " + m_nu);
        System.out.println("\n\t Les personnages et monstre joueront comme suit:");
        donjon.affichageOrdre();

        System.out.println("\nAller " + personnage.getNom() + ", a toi de jouer!");
        System.out.println("=====================================================================================");


        System.out.println("Voici l'id de tout le monde:");
        donjon.afficherIDentite();
        System.out.println("\n");
        donjon.affichagePlateau();
        System.out.println("les monstres sont suivis d'un M || les équipements sont représentés par des *\n\n");

        System.out.println("=====================================================================================");
        int id = donjon.getId(personnage);

        personnage.toStringDetails();


        for (int i = 3; i > 0; i--) {

            System.out.println(" vous avez " + i + "action(s), que choisissez vous? \n" +
                    "[1] laisser le maître du jeu commenter l'action précédente\n" +
                    "[2] commenter action précédente\n" +
                    "[3] attaquer\n" +
                    "[4] se déplacer\n" +
                    "[5] s'équiper");
            String choix = scan.nextLine();
            switch (choix) {
                case "1" -> {
                    System.out.println("Laisser au "+narrateur.getPseudo()+" la parole? bien, que veut-il nous dire?");
                    String nouveau = scan.nextLine();
                    narrateur.commenter(nouveau);

                }
                case "2" -> {
//le personnage peut dire des trucs?
                    System.out.println(personnage.getNom() + " souhaite commenter : ");
                    String commentaire = scan.nextLine();
                    narrateur.commenter(commentaire);
                }
                case "3" -> {
                    System.out.println("La meilleure defense est l'attaque, qui voulez-vous attaquer?(un id suffit)");
                    String cible = scan.nextLine(); //donne id de la cible
                    int decision = Integer.parseInt(cible); //on convert l'id en int

                    Entite attaque = donjon.getEntiteParId(decision); //je recupere la cible
                    if (attaque != null) { //si la cible existe bien
                        String attaquer=personnage.attaquer(attaque); //on l'attaque
                        System.out.println(attaquer);

                    }
                    else {
                        System.out.println("attaque impossible");
                    }
                }
                case "4" -> {
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

                case "5" -> {
                    System.out.println("Vous optez pour une protection personnel");
                    int armeId;

                    for (Map.Entry<String, int[]> positions : donjon.getCases().entrySet()) { //on parcours le tab de caes
                        if (positions.getValue()[0] == id) { //si dans cette case on a le perso
                            armeId = positions.getValue()[1]; //on recup l'id de l'equiment dispo
                            if (armeId <= 0) continue;

                            String arme = String.valueOf((armeId));//on recup le nom de cet equipement
                            if (armeId>4 && armeId<12) { //si l'id est plus grand que 4 c'est une arme
                                Arme newArm = new Arme(arme); //on cree cette arme
                                personnage.equiper(newArm);//le perso s'equipe
                                System.out.println("Le personnage " + personnage.getNom() + " s’équipe avec " + arme);
                            } else if (armeId>0 && armeId<5) { //si l'id est plus petit que 5 c'est une armure
                                Armure armure = new Armure(arme);  //on cree l'armure
                                personnage.equiper(armure);//le perso s'equipe
                                System.out.println("Le personnage " + personnage.getNom() + " s’équipe avec " + arme);
                            }
                        }

                    }
                }

                default -> {
                    System.out.println("mauvais choix, recommencer.");
                    i++;
                }
            }

        }

    }



    public void ajouterTourMonstre(Monstre monstre, Donjon donjon, Narrateur narrateur){
        Scanner scan = new Scanner(System.in);


        System.out.println("=====================================================================================");
        System.out.println("donjon : " + donjon.getNumDonjons());
        System.out.println("jeu.Tours : " + m_nu);

        donjon.affichageOrdre();

        System.out.println("Le monstre "+monstre.getNom()+" est pret à jouer, préparer vous!");
        System.out.println("=====================================================================================");

        monstre.toStringDetails();
        int id = donjon.getId(monstre);

        donjon.afficherIDentite();


        donjon.affichagePlateau();
        System.out.println("les monstres sont suivis d'un M|| les équipements sont représentés par des *\n\n");

        System.out.println("=====================================================================================");
        monstre.toStringDetails();


        for (int i = 3; i > 0; i--) {

            System.out.println(" vous avez " + i + "action(s), que choisissez vous? \n " +
                    "[1] laisser le maître du jeu commenter l'action précédente\n" +
                    "[2] commenter action précédente\n" +
                    "[3] attaquer\n" +
                    "[4] se déplacer");
            String choix = scan.nextLine();
            switch (choix) {
                case "1" -> {
                    System.out.println("Attention, "+narrateur.getPseudo()+" a pris la parole. Merci de l'écouter.");
                    String nouveau = scan.nextLine();
                    narrateur.commenter(nouveau);
                }
                case "2" -> {
//le personnage peut dire des trucs?
                    System.out.println(monstre.getNom() + " souhaite commenter : ");
                    String commentaire = scan.nextLine();
                    narrateur.commenter(commentaire);
                }
                case "3" -> {
                    System.out.println(monstre.getNom()+", qui va etre votre victime?(un id suffit)");
                    String cible = scan.nextLine(); //donne id de la cible
                    int decision = Integer.parseInt(cible); //on convert l'id en int

                    Entite attaque = donjon.getEntiteParId(decision); //je recupere la cible
                    if (attaque != null) { //si la cible existe bien
                        String attaquer=monstre.attaquer(attaque); //on l'attaque
                        System.out.println(attaquer);

                    }
                    else {
                        System.out.println("attaque impossible");
                    }
                }
                case "4" -> {
                    int distance =monstre.getDistance();
                    String dis= String.valueOf(distance);
                    System.out.println("Une stratégie se met en place, quelle position est la plus adapté? pas plus de "+distance+" cases");
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
                    System.out.println("mauvais choix, recommencer.");
                    i++;
                }
            }
            donjon.affichagePlateau(); //j'affiche le plateau apres chaque choix
        }
        //on retourne fin pour savoir si ce tour a conduit vers la fin d'un donjon ou pas

    }

}
