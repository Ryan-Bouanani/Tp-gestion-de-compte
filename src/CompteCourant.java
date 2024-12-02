public class CompteCourant extends Compte {
    public double decouvertAutorise;

    // Constructeurs
    public CompteCourant() {
        super();
    }

    public CompteCourant(String proprietaire, double decouvertAutorise) {
        super(proprietaire);
        this.decouvertAutorise = decouvertAutorise;
    }

    // Méthode de calcul de bénéfice (toujours 0 pour un compte courant)
    @Override
    public double calculBenefice() {
        return 0;
    }

    // Redéfinition de la méthode information pour afficher le découvert
    @Override
    public void information() {
        System.out.println("*******************************************");
        System.out.println("Propriétaire : " + proprietaire);
        System.out.println("Solde : " + String.format("%.2f", soldeFinal()));
        System.out.println("Découvert autorisé : " + String.format("%.2f", decouvertAutorise));
        System.out.println("Opérations : ");
        operations.forEach(System.out::println);
        System.out.println("*******************************************");
    }
}