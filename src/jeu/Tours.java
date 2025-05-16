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

    public boolean ajouterTourPersonnage(Personnage personnage, Donjon donjon, Narrateur narrateur){
        boolean fin = false;
        Scanner scan = new Scanner(System.in);


        System.out.println("=====================================================================================");
        System.out.println("donjon : " + donjon.getNumDonjons());
        System.out.println("jeu.Tours : " + m_nu);

        donjon.affichageOrdre();

        System.out.println("Aller " + personnage.getNom() + ", a toi de jouer!");
        System.out.println("=====================================================================================");

        personnage.toStringDetails();
        int id = donjon.getId(personnage);

        donjon.afficherIDentite();

        donjon.affichagePlateau();
        System.out.println("les monstres sont suivis d'un M|| les equipements sont représentés par des *\n\n");

        System.out.println("=====================================================================================");
        personnage.toStringDetails();


        for (int i = 3; i > 0; i--) {

            System.out.println(" vous avez " + i + "action(s), que choisissez vous? \n " +
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
                }
                case "3" -> {
                    System.out.println("La meilleure defense est l'attaque, qui voulez-vous attaquer?(un id suffit)");
                    String attaqué = scan.nextLine();
                    int decision = Integer.parseInt(attaqué);

                    Entite attaque = donjon.getEntiteParId(decision);
                    if (attaque != null) {
                        personnage.attaquer(attaque);
                        attaque.recevoirDegats(2); //NOMBRE AU HASARD, JE SAIS PAS COMMENT GÉRER CA
                        int finito=donjon.finDonjon(personnage);
                       if (finito==0 || finito==1) {
                           fin=true;
                       }
                    }
                }
                case "4" -> {
                    int distance=personnage.getDistance();
                    String dis= String.valueOf(distance);

                    System.out.println("Vous souhaitez changer de position, dites en plus, où voulez vous aller? pas plus de "+dis+" cases");
                    String position = scan.nextLine();
                    String deplacement = donjon.seDeplacer(id, position);
                    System.out.println(deplacement);
                }

                case "5" -> {
                    System.out.println("Vous optez pour une protection personnel");
                    int armeId;
                    for (Map.Entry<String, int[]> positions : donjon.getCases().entrySet()) { //on parcours le tab de caes
                        if (positions.getValue()[0] == id) { //si dans cette case on a le perso
                            armeId = positions.getValue()[1]; //on recup l'id de l'equiment dispo
                            String arm = String.valueOf((armeId));//on recup le nom de cet equipement
                            if (armeId>4 && armeId<12) { //si l'id est plus grand que 4 c'est une arme
                                Arme arme = new Arme(arm); //on cree cette arme
                                personnage.equiper(arme);//le perso s'equipe
                                System.out.println("Le personnage " + armeId + " s'equipe avec " + arm);
                            } else if (armeId>0 && armeId<5) { //si l'id est plus petit que 5 c'est une armure
                                Armure armure = new Armure(arm);  //on cree l'armure
                                personnage.equiper(armure);//le perso s'equipe
                                System.out.println("Le personnage " + armeId + " s'equipe avec " + arm);
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
        return fin;

    }



    public boolean ajouterTourMonstre(Monstre monstre, Donjon donjon, Narrateur narrateur){
        boolean fin = false;
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
                }
                case "3" -> {
                    System.out.println(monstre.getNom()+"Qui va etre votre victime?(un id suffit)");
                    String victime = scan.nextLine();
                    int decision = Integer.parseInt(victime);

                    Entite attaque = donjon.getEntiteParId(decision);
                    if (attaque != null) {
                        monstre.attaquer(attaque);
                        attaque.recevoirDegats(2); //AU HASARD
                        int finito=donjon.finDonjon((Personnage) attaque);
                        if (finito==0) {
                            fin=true;
                        }
                    }
                }
                case "4" -> {
                    int distance =monstre.getDistance();
                    String dis= String.valueOf(distance);
                    System.out.println("Une stratégie se met en place, quelle position est la plus adapté? pas plus de "+distance+" cases");
                    String position = scan.nextLine();
                    String deplacement = donjon.seDeplacer(id, position);
                    System.out.println(deplacement);
                }

                default -> {
                    System.out.println("mauvais choix, recommencer.");
                    i++;
                }
            }
            donjon.affichagePlateau(); //j'affiche le plateau apres chaque choix
        }
        return fin;

    }

}
