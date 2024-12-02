public class CompteEpargne extends Compte {
    private double tauxAbondement;

    // Constructeurs
    public CompteEpargne() {
        super();
    }

    public CompteEpargne(String proprietaire, double tauxAbondement) {
        super(proprietaire);
        this.tauxAbondement = tauxAbondement;
    }

    // Calculer les bénéfices basés sur le solde et le taux
    @Override
    public double calculBenefice() {
        return calculSolde() * tauxAbondement;
    }

    // Redéfinition de la méthode information pour afficher le taux
    @Override
    public void information() {
        System.out.println("*******************************************");
        System.out.println("Propriétaire : " + proprietaire);
        System.out.println("Solde : " + String.format("%.2f", soldeFinal()));
        System.out.println("Taux d'abondement : " + String.format("%.2f", tauxAbondement * 100) + " %");
        System.out.println("Opérations : ");
        operations.forEach(System.out::println);
        System.out.println("*******************************************");
    }
}