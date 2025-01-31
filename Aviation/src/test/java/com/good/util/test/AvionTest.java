package com.good.util.test;

import com.good.util.Avion;
import com.good.util.Type;
import com.good.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AvionTest {

    private Session session;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    void testCreateAvion() {
        Transaction transaction = null;
        try {
            // Démarrage de la transaction
            transaction = session.beginTransaction();

            // Création d'un Type associé
            Type type = new Type();
            type.setNom("Commercial");
            session.save(type);

            // Création d'un Avion
            Avion avion = new Avion();
            avion.setMatricule(12345);
            avion.setType(type);
            session.save(avion);

            // Validation de la transaction
            transaction.commit();

            // Vérification
            assertNotNull(avion.getMatricule(), "L'avion doit avoir un matricule");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Annulation de la transaction en cas d'erreur
            }
            throw e;  // Re-lancer l'exception pour qu'elle apparaisse dans le rapport de test
        }
    }

    @Test
    void testReadAvion() {
        // Lecture de l'avion créé précédemment
        Avion avion = session.get(Avion.class, 12345);

        // Vérification
        assertNotNull(avion, "L'avion ne doit pas être null");
        assertEquals("Commercial", avion.getType().getNom(), "Le type d'avion doit être 'Commercial'");
    }

    @Test
    void testUpdateAvion() {
        Transaction transaction = null;
        try {
            // Démarrage de la transaction
            transaction = session.beginTransaction();

            // Lecture de l'avion existant
            Avion avion = session.get(Avion.class, 12345);
            assertNotNull(avion, "L'avion doit exister");

            // Modification
            avion.getType().setNom("Militaire");
            session.update(avion);

            // Validation de la transaction
            transaction.commit();

            // Vérification
            Avion updatedAvion = session.get(Avion.class, 12345);
            assertEquals("Militaire", updatedAvion.getType().getNom(), "Le type d'avion doit être mis à jour à 'Militaire'");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Annulation de la transaction en cas d'erreur
            }
            throw e;  // Re-lancer l'exception pour qu'elle apparaisse dans le rapport de test
        }
    }

    @Test
    void testDeleteAvion() {
        Transaction transaction = null;
        try {
            // Démarrage de la transaction
            transaction = session.beginTransaction();

            // Lecture de l'avion existant
            Avion avion = session.get(Avion.class, 12345);
            assertNotNull(avion, "L'avion doit exister avant la suppression");

            // Suppression
            session.delete(avion);

            // Validation de la transaction
            transaction.commit();

            // Vérification
            Avion deletedAvion = session.get(Avion.class, 12345);
            assertNull(deletedAvion, "L'avion doit être supprimé");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Annulation de la transaction en cas d'erreur
            }
            throw e;  // Re-lancer l'exception pour qu'elle apparaisse dans le rapport de test
        }
    }

    @AfterAll
    void tearDown() {
        if (session != null) {
            session.close();
        }
    }
}
