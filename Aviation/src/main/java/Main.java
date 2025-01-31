import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.good.util.Type;
import com.good.util.Avion;
import com.good.util.Adresse;
import com.good.util.Technicien;
import com.good.util.Pilote;
import com.good.util.Employe;
import com.good.util.Qualification;
import com.good.util.QualificationId;
import com.good.util.Reparation;
import com.good.util.Specialisation;
import com.good.util.SpecialisationId;
import com.good.util.Piloter;
import com.good.util.PiloterId;
import com.good.util.Examen;
import com.good.util.Examination;
import com.good.util.ExaminationId;
import com.good.util.Test;
import com.good.util.AvionTest;

public class Main {

    public static void main(String[] args) {

        // Charger la configuration de Hibernate à partir du fichier hibernate.cfg.xml
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Créer un SessionFactory à partir de la configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Ouvrir une session Hibernate
        Session session = sessionFactory.openSession();

        // Afficher un message pour vérifier que la session est ouverte
        System.out.println("Session Hibernate ouverte avec succès !");

        // Démarrer une transaction
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Création d'un type
            Type type = new Type();
            type.setNom("Commercial");      // Définir un nom unique pour l'entité Type
            type.setCapacite(150);          // Capacité
            type.setPoids(2000.0);          // Poids
            type.setRayonAction(500.0);     // Rayon d'action

            // Sauvegarder l'entité Type dans la base de données
            session.save(type);
            System.out.println("Type sauvegardé!");

            // Création d'un avion
            Avion avion = new Avion();
            avion.setMatricule(12345);       // Définir un matricule unique pour l'avion
            avion.setType(type);             // Associer l'avion au type précédemment inséré
            session.save(avion);
            System.out.println("Avion sauvegardé!");

            // *** Maxim (Technicien) ***
            Adresse adresseMaxim = new Adresse(123, "Rue de l'Exemple", "Lévis", "G6V 4Z5", "Québec", "Canada");
            session.save(adresseMaxim);

            Technicien maxim = new Technicien();
            maxim.setNom("Laurendeau");
            maxim.setPrenom("Maxim");
            maxim.setTel("418-123-4567");
            maxim.setDateEngagement(LocalDate.of(2024, 12, 13));
            maxim.setSalaire(new BigDecimal("45000.00"));
            maxim.setAdresse(adresseMaxim);
            session.save(maxim);

            System.out.println("Technicien Maxim sauvegardé !");

            // *** Mohamed (Pilote) ***
            Adresse adresseMohamed = new Adresse(124, "Rue d'Exemple 2", "Lévis", "G6V 4Z6", "Québec", "Canada");
            session.save(adresseMohamed);

            Pilote mohamed = new Pilote();
            mohamed.setNom("Mehrazi");
            mohamed.setPrenom("Mohamed");
            mohamed.setTel("418-987-6543");
            mohamed.setDateEngagement(LocalDate.of(2024, 12, 13));
            mohamed.setSalaire(new BigDecimal("50000.00"));
            mohamed.setAdresse(adresseMohamed);
            session.save(mohamed);

            System.out.println("Pilote Mohamed sauvegardé !");

            // *** Samuel (Employé lambda) ***
            Adresse adresseSamuel = new Adresse(125, "Rue de la Paix", "Lévis", "G6V 4Z7", "Québec", "Canada");
            session.save(adresseSamuel);

            Employe samuel = new Employe();
            samuel.setNom("Simard");
            samuel.setPrenom("Samuel");
            samuel.setTel("418-456-7890");
            samuel.setDateEngagement(LocalDate.of(2024, 12, 13));
            samuel.setSalaire(new BigDecimal("40000.00"));
            samuel.setAdresse(adresseSamuel);
            session.save(samuel);

            System.out.println("Employé Samuel sauvegardé !");

            // Ajout d'une qualification pour Mohamed sur le type "Commercial"
            QualificationId qualificationId = new QualificationId(mohamed.getMatricule(), type.getNom());
            Qualification qualification = new Qualification();
            qualification.setId(qualificationId);
            qualification.setPilote(mohamed);
            qualification.setType(type);
            session.save(qualification);

            System.out.println("Qualification ajoutée pour Mohamed sur le type 'Commercial' !");
            
            // Ajouter une réparation
            Reparation reparation = new Reparation();
            reparation.setAvionMatricule(avion.getMatricule());
            reparation.setTechnicienMatricule(maxim.getMatricule());
            reparation.setAvion(avion);
            reparation.setTechnicien(maxim);
            reparation.setCoutTotal(new BigDecimal("1500.00")); // Exemple de coût total pour la réparation
            reparation.setDate(LocalDate.now()); // Date actuelle

            // Sauvegarder la réparation dans la base de données
            session.save(reparation);
            System.out.println("Réparation sauvegardée pour l'avion avec matricule " + avion.getMatricule() + " par le technicien Maxim !");
            
            // Ajout d'une spécialisation pour Maxim sur le type "Commercial"
            SpecialisationId specialisationId = new SpecialisationId(maxim.getMatricule(), type.getNom());
            Specialisation specialisation = new Specialisation();
            specialisation.setId(specialisationId);
            specialisation.setTechnicien(maxim);
            specialisation.setType(type);

            // Sauvegarder la spécialisation dans la base de données
            session.save(specialisation);
            System.out.println("Spécialisation ajoutée pour Maxim sur le type 'Commercial' !");            
         
            // Associer Mohamed au pilotage de l'avion
            PiloterId piloterId = new PiloterId();
            piloterId.setPilote_matricule(mohamed.getMatricule());
            piloterId.setAvion_matricule(avion.getMatricule());

            Piloter piloter = new Piloter();
            piloter.setId(piloterId);
            piloter.setPilote(mohamed);
            piloter.setAvion(avion);

            session.save(piloter);
            System.out.println("Mohamed est maintenant associé au pilotage de l'avion commercial !");
            
            Examen examen = new Examen();
            examen.setDescription("Examen de compétences pour les avions commerciaux");
            examen.setExamencol("Commercial");
            session.save(examen);
            System.out.println("Examen sauvegardé !");

            // Création d'une examination pour Mohamed et cet examen
            ExaminationId examinationId = new ExaminationId();
            examinationId.setPilote_matricule(mohamed.getMatricule());
            examinationId.setExamen_identifiant(examen.getIdentifiant());

            Examination examination = new Examination();
            examination.setId(examinationId);
            examination.setPilote_matricule(mohamed); // Associe le pilote Mohamed
            examination.setExamen_identifiant(examen); // Associe l'examen créé
            examination.setDate(new java.util.Date()); // Date actuelle
            examination.setRapport("Examen réussi avec distinction.");

            session.save(examination);
            System.out.println("Examination ajoutée pour le pilote Mohamed !");
            
            // 1. Créer un Test
            Test test = new Test();
            test.setNumero(101);  // Définir un numéro unique pour le test
            test.setNom("Test de sécurité");
            test.setSeuil(85.0);   // Définir un seuil pour le test (ex. 85%)

            session.save(test);
            System.out.println("Test sauvegardé !");

            // 2. Créer un AvionTest et l'associer à un avion et un test
            AvionTest avionTest = new AvionTest();
            
            avionTest.setAvion(avion);  // Associer l'avion que nous avons déjà créé
            avionTest.setTest(test);    // Associer le test créé ci-dessus

            session.save(avionTest);
            System.out.println("AvionTest sauvegardé pour l'avion matricule " + avion.getMatricule() + " et le test " + test.getNom());

            // Commit de la transaction
            transaction.commit();
            System.out.println("Le Type, L'avion, Les adresses, les employés, la qualification, " +
                    "la réparation, la spécialisation, le pilotage, l'examen, l'examination, " +
                    "le test et l'AvionTest ont été insérés avec succès!");
            
        } catch (Exception e) {
            // En cas d'erreur, annuler la transaction
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
