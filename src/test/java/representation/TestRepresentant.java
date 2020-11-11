package representation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRepresentant {
	// Quelques constantes
	private static final float FIXE_BASTIDE = 1000f;
	private static final float INDEMNITE_OCCITANIE = 200f;
        
	
	private Representant r; // L'objet à tester
	private ZoneGeographique occitanie;
	
	@BeforeEach
	public void setUp() {
		// Initialiser les objets utilisés dans les tests
		occitanie = new ZoneGeographique(1, "Occitanie");
		occitanie.setIndemniteRepas(INDEMNITE_OCCITANIE);

		r = new Representant(36, "Bastide", "Rémi", occitanie);	
		r.setSalaireFixe(FIXE_BASTIDE);				
	}
	
	@Test
	public void testSalaireMensuel() {
		float CA = 50000f;
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		// On enregistre un CA pour le mois 0 (janvier)
		r.enregistrerCA(0, CA);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		
		assertEquals(// Comparaison de "float"
			// valeur attendue
			FIXE_BASTIDE + INDEMNITE_OCCITANIE + CA * POURCENTAGE,
			// Valeur calculée
			salaire,
			// Marge d'erreur tolérée
			0.001,
			// Message si erreur
			"Le salaire mensuel est incorrect"
		); 
	}

	@Test
	public void testCAParDefaut() {
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		
		// On n'enregistre aucun CA
		//r.enregistrerCA(0, 10000f);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		// Le CA du mois doit avoir été initialisé à 0
		
		assertEquals(
			FIXE_BASTIDE + INDEMNITE_OCCITANIE, 
			salaire, 
			0.001,
			"Le CA n'est pas correctement initialisé"
		);
	}

	@Test
	public void testCANegatifImpossible() {
		
		try {
			// On enregistre un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(0, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un CA négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
        @Test
        public void numeroIsCorrectlyInitialized() {
            assertEquals(36, r.getNumero(), "Initialisation incorrecte du numero");                                  
        }
        @Test
        public void adresseIsCorrectlyInitialized() {
            r.setAdresse("18 Rue Lavedan");
            assertEquals("18 Rue Lavedan", r.getAdresse(), "Initialisation incorrecte du adresse");
        }
       @Test
       public void nomIsCorrectlyInitialized() {
           
            assertEquals("Bastide", r.getNom(), "Initialisation incorrecte du nom");
       }
       @Test
       public void prenomIsCorrectlyInitialized() {
            assertEquals("Rémi", r.getPrenom(), "Initialisation incorrecte du prenom");
       }
       @Test
        public void salaireFixIsCorrectlyInitialized() {
            r.setSalaireFixe(FIXE_BASTIDE);
            assertEquals(FIXE_BASTIDE, r.getSalaireFixe(), "Initialisation incorrecte du Salaire Fixe");
        }
        @Test
        public void secteurIsCorrectlyInitialized() {
            r.setSecteur(occitanie);
            assertEquals(occitanie, r.getSecteur(), "Initialisation incorrecte du Secteur");
        }
        @Test
	public void testMonthMoreThan11() {
		
		try {
			
			r.enregistrerCA(12, 10000f);
			
			fail("Un mois > 11 doit générer une exception"); 			
		} catch (IllegalArgumentException e) {
			
		}
        }
        @Test
	public void testMonthLessThan0() {
		
		try {
			
			r.enregistrerCA(-1, 10000f);
			
			fail("Un mois < 0 doit générer une exception"); 			
		} catch (IllegalArgumentException e) {
			
		}
        }
        @Test
        public void shouldReturnString() { 
            r = new Representant(30, "Majid", "Saif", occitanie); 
            assertEquals("Representant{" + "numero=" + 30 + ", nom=" + "Majid" + ", prenom=" + "Saif" + '}', r.toString(), "String is not returned correctly");
                       
        }}



	
