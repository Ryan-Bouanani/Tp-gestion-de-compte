import java.util.ArrayList;
import java.util.List;

public abstract class Compte implements Comparable<Compte> {
    protected List<Operation> operations;
    protected String proprietaire;

    // Constructeur par défaut
    public Compte() {
        this.operations = new ArrayList<>();
    }

    // Constructeur avec proprietaire
    public Compte(String proprietaire) {
        this.proprietaire = proprietaire;
        this.operations = new ArrayList<>();
    }

    // Méthode pour créditer
    public void crediter(double montant) {
        operations.add(new Operation(montant, Mouvement.CREDIT));
    }

    // Méthode pour débiter
    public void debiter(double montant) {
        operations.add(new Operation(montant, Mouvement.DEBIT));
    }

    // Créditer un compte et débiter un autre
    public void crediter(double montant, Compte compteADebiter) {
        this.crediter(montant);
        compteADebiter.debiter(montant);
    }

    // Débiter un compte et créditer un autre
    public void debiter(double montant, Compte compteACrediter) {
        this.debiter(montant);
        compteACrediter.crediter(montant);
    }

    // Calculer le solde
    public double calculSolde() {
        return operations.stream()
                .mapToDouble(op ->
                        op.getType() == Mouvement.CREDIT ? op.getMontant() : -op.getMontant())
                .sum();
    }

    // Méthode abstraite pour calculer les bénéfices
    public abstract double calculBenefice();

    // Solde final avec bénéfices
    public double soldeFinal() {
        return calculSolde() + calculBenefice();
    }

    // Ajout de getProprietaire() et setProprietaire() dans la classe abstraite Compte
    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    // Méthode pour afficher les informations
    public void information() {
        System.out.println("*******************************************");
        System.out.println("Propriétaire : " + proprietaire);
        System.out.println("Solde : " + String.format("%.2f", soldeFinal()));
        System.out.println("Opérations : ");
        operations.forEach(op -> System.out.println(op));
        System.out.println("*******************************************");
    }

    // Implémentation de Comparable pour trier par solde
    @Override
    public int compareTo(Compte autre) {
        return Double.compare(this.soldeFinal(), autre.soldeFinal());
    }
}