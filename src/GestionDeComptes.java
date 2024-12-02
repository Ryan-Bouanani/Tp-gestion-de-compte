import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionDeComptes {
    private final List<Compte> comptes;

    public GestionDeComptes() {
        comptes = new ArrayList<>();
    }

    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

    public void afficherComptes() {
        comptes.forEach(Compte::information);
    }

    public void trierComptes() {
        Collections.sort(comptes);
    }

    // Ajout d'une méthode getComptes() dans la classe GestionDeComptes
    public List<Compte> getComptes() {
        return comptes;
    }
}