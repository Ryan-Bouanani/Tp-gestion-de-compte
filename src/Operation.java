public class Operation {
    private double montant;
    private Mouvement type;

    // Constructeur par défaut
    public Operation() {}

    // Constructeur avec paramètres
    public Operation(double montant, Mouvement type) {
        this.montant = montant;
        this.type = type;
    }

    // Getters et setters
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Mouvement getType() {
        return type;
    }

    public void setType(Mouvement type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (type == Mouvement.CREDIT ? "+" : "-") + String.format("%.2f", montant);
    }
}