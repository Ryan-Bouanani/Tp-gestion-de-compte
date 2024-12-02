import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final GestionDeComptes gestionComptes = new GestionDeComptes();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne

            switch (choix) {
                case 1:
                    creerCompteCourant();
                    break;
                case 2:
                    creerCompteEpargne();
                    break;
                case 3:
                    crediterCompte();
                    break;
                case 4:
                    debiterCompte();
                    break;
                case 5:
                    effectuerVirement();
                    break;
                case 6:
                    gestionComptes.afficherComptes();
                    break;
                case 7:
                    gestionComptes.trierComptes();
                    gestionComptes.afficherComptes();
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choix != 8);

//        // TESTS
//        // Créer la gestion de comptes
//        GestionDeComptes gestionComptes = new GestionDeComptes();
//
//        // Créer un compte courant de Nicolas avec un découvert de 2000 €
//        CompteCourant compteNicolas = new CompteCourant("Nicolas", 2000);
//
//        // Créer un compte courant de Jérémie avec un découvert de 500 €
//        CompteCourant compteJeremie = new CompteCourant("Jérémie", 500);
//
//        // Créer un compte épargne pour Nicolas avec un taux de 2.05%
//        CompteEpargne compteEpargneNicolas = new CompteEpargne("Nicolas", 0.025);
//
//        // Ajouter les comptes à la gestion de comptes
//        gestionComptes.ajouterCompte(compteNicolas);
//        gestionComptes.ajouterCompte(compteJeremie);
//        gestionComptes.ajouterCompte(compteEpargneNicolas);
//
//        // Opérations sur les comptes
//        // Créditer le compte de Nicolas qui touche son salaire de 1000 €
//        compteNicolas.crediter(1000);
//
//        // Créditer le compte épargne de Nicolas de 200 €
//        compteEpargneNicolas.crediter(200);
//
//        // Débiter le compte de Nicolas qui fait le plein de sa voiture : 50 €
//        compteNicolas.debiter(50);
//
//        // Créditer le compte de Nicolas qui a reçu un cadeau de la banque de 100 €
//        compteNicolas.crediter(100);
//
//        // Créditer à nouveau le compte épargne de Nicolas de 100 €
//        compteEpargneNicolas.crediter(100);
//
//        // Débiter le compte de Jérémie qui achète un nouveau PC : 500 €
//        compteJeremie.debiter(500);
//
//        // Débiter le compte de Jérémie qui rembourse ses dettes à Nicolas : 200 € mais ne va pas passer car découvert depassé
//        compteJeremie.debiter(200);
//
//        // Afficher le résumé des comptes de Nicolas et Jérémie
//        System.out.println("Résumé du compte courant de Nicolas :");
//        compteNicolas.information();
//
//        System.out.println("Résumé du compte courant de Jérémie :");
//        compteJeremie.information();
//
//        System.out.println("Résumé du compte épargne de Nicolas :");
//        compteEpargneNicolas.information();
    }

    private static void afficherMenu() {
        System.out.println("\n--- Gestion de Comptes Bancaires ---");
        System.out.println("1. Créer un compte courant");
        System.out.println("2. Créer un compte épargne");
        System.out.println("3. Créditer un compte");
        System.out.println("4. Débiter un compte");
        System.out.println("5. Effectuer un virement");
        System.out.println("6. Afficher la liste des comptes");
        System.out.println("7. Trier les comptes");
        System.out.println("8. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void creerCompteCourant() {
        System.out.print("Nom du propriétaire : ");
        String nom = scanner.nextLine();
        System.out.print("Découvert autorisé : ");
        double decouvert = scanner.nextDouble();
        CompteCourant compte = new CompteCourant(nom, decouvert);
        gestionComptes.ajouterCompte(compte);
        System.out.println("Compte courant créé avec succès.");
    }

    private static void creerCompteEpargne() {
        System.out.print("Nom du propriétaire : ");
        String nom = scanner.nextLine();
        System.out.print("Taux d'abondement (en %) : ");
        double taux = scanner.nextDouble() / 100.0;
        CompteEpargne compte = new CompteEpargne(nom, taux);
        gestionComptes.ajouterCompte(compte);
        System.out.println("Compte épargne créé avec succès.");
    }

    private static Compte selectionnerCompte(String message) {
        List<Compte> comptesProprietaire = new ArrayList<>();

        // Demander le nom du propriétaire
        System.out.print("Nom du propriétaire : ");
        String nom = scanner.nextLine();

        // Trouver tous les comptes du propriétaire
        for (Compte compte : gestionComptes.getComptes()) {
            if (compte.getProprietaire().equalsIgnoreCase(nom)) {
                comptesProprietaire.add(compte);
            }
        }

        // Vérifier si le propriétaire a des comptes
        if (comptesProprietaire.isEmpty()) {
            System.out.println("Aucun compte trouvé pour ce propriétaire.");
            return null;
        }

        // Afficher les comptes disponibles
        System.out.println(message);
        for (int i = 0; i < comptesProprietaire.size(); i++) {
            Compte compte = comptesProprietaire.get(i);
            String type = compte instanceof CompteCourant ? "Courant" : "Épargne";
            System.out.println((i+1) + ". Compte " + type + " - Solde : " +
                    String.format("%.2f", compte.soldeFinal()) + " €");
        }

        // Sélectionner un compte avec gestion des erreurs
        while (true) {
            try {
                System.out.print("Choisissez un compte (1-" + comptesProprietaire.size() + ") : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la ligne

                // Vérifier que le choix est dans la plage valide
                if (choix < 1 || choix > comptesProprietaire.size()) {
                    System.out.println("Choix invalide. Veuillez choisir un numéro entre 1 et " + comptesProprietaire.size());
                    continue;
                }

                return comptesProprietaire.get(choix - 1);
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez saisir un nombre valide.");
                scanner.nextLine(); // Vider le buffer
            }
        }
    }

    private static void effectuerVirement() {
        System.out.println("Sélection du compte source :");
        Compte compteSource = selectionnerCompte("Sélectionnez un compte source :");

        System.out.println("Sélection du compte destinataire :");
        Compte compteDestinataire = selectionnerCompte("Sélectionnez un compte destinataire :");

        if (compteSource != null && compteDestinataire != null) {
            // Autoriser les virements entre comptes du même propriétaire
            if (compteSource == compteDestinataire) {
                System.out.println("Erreur : Impossible de faire un virement sur le même compte.");
                return;
            }

            System.out.print("Montant du virement : ");
            double montant = scanner.nextDouble();
            scanner.nextLine(); // Consommer la ligne

            compteSource.debiter(montant, compteDestinataire);
            System.out.println("Virement effectué avec succès.");

            System.out.println("Compte source :");
            compteSource.information();
            System.out.println("Compte destinataire :");
            compteDestinataire.information();
        }
    }

    // Ajout de gestion d'erreurs dans les autres méthodes de saisie
    private static void crediterCompte() {
        Compte compte = selectionnerCompte("Sélectionnez un compte à créditer :");
        if (compte != null) {
            while (true) {
                try {
                    System.out.print("Montant à créditer : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine(); // Consommer la ligne

                    if (montant <= 0) {
                        System.out.println("Le montant doit être positif.");
                        continue;
                    }

                    compte.crediter(montant);
                    System.out.println("Compte crédité avec succès.");
                    compte.information();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez saisir un montant valide.");
                    scanner.nextLine(); // Vider le buffer
                }
            }
        }
    }

    private static void debiterCompte() {
        Compte compte = selectionnerCompte("Sélectionnez un compte à débiter :");
        if (compte != null) {
            while (true) {
                try {
                    System.out.print("Montant à débiter : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine(); // Consommer la ligne

                    if (montant <= 0) {
                        System.out.println("Le montant doit être positif.");
                        continue;
                    }

                    // Stocker le solde initial avant de tenter le débit
                    double soldeInitial = compte.soldeFinal();

                    // Tenter le débit
                    compte.debiter(montant);

                    // Vérifier si le débit a réellement eu lieu
                    if (compte.soldeFinal() == soldeInitial) {
                        System.out.println("Le débit a été annulé.");
                    } else {
                        System.out.println("Compte débité avec succès.");
                        compte.information();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez saisir un montant valide.");
                    scanner.nextLine(); // Vider le buffer
                }
            }
        }
    }
}