package com.good.util.test;

import com.good.util.Pilote;
import com.good.util.Employe;
import com.good.util.Adresse;
import com.good.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PiloteTest {

    private Session session;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    void testCreatePilote() {
        Transaction transaction = session.beginTransaction();

        try {
            // Création de l'adresse à Lévis, Canada
            Adresse adresse = new Adresse(12, "Rue des Roses", "Lévis", "G6V 1Z2", "Québec", "Canada");
            session.save(adresse);

            // Création d'un pilote
            Pilote pilote = new Pilote(123, "Dupont", "Jean", adresse, "0102030405", LocalDate.of(2020, 1, 1), BigDecimal.valueOf(2500.00));
            session.save(pilote);

            // Commit de la transaction
            transaction.commit();

            // Vérification
            assertNotNull(pilote.getMatricule(), "Le matricule du pilote doit être généré");
        } catch (Exception e) {
            transaction.rollback();
            fail("La création du pilote a échoué : " + e.getMessage());
        }
    }

    @Test
    void testReadPilote() {
        // Récupérer le pilote par son matricule
        Pilote pilote = session.get(Pilote.class, 123);

        // Vérification
        assertNotNull(pilote, "Le pilote doit exister");
        assertEquals("Dupont", pilote.getNom(), "Le nom du pilote doit être 'Dupont'");
        assertEquals("Jean", pilote.getPrenom(), "Le prénom du pilote doit être 'Jean'");
    }

    @Test
    void testUpdatePilote() {
        Transaction transaction = session.beginTransaction();

        try {
            // Récupérer le pilote existant
            Pilote pilote = session.get(Pilote.class, 123);
            assertNotNull(pilote, "Le pilote doit exister avant la mise à jour");

            // Mise à jour des informations
            pilote.setNom("Durand");
            pilote.setPrenom("Pierre");
            session.update(pilote);

            // Commit de la transaction
            transaction.commit();

            // Vérification
            Pilote updatedPilote = session.get(Pilote.class, 123);
            assertEquals("Durand", updatedPilote.getNom(), "Le nom du pilote doit être mis à jour à 'Durand'");
            assertEquals("Pierre", updatedPilote.getPrenom(), "Le prénom du pilote doit être mis à jour à 'Pierre'");
        } catch (Exception e) {
            transaction.rollback();
            fail("La mise à jour du pilote a échoué : " + e.getMessage());
        }
    }

    @Test
    void testDeletePilote() {
        Transaction transaction = session.beginTransaction();

        try {
            // Récupérer le pilote existant
            Pilote pilote = session.get(Pilote.class, 123);
            assertNotNull(pilote, "Le pilote doit exister avant la suppression");

            // Suppression du pilote
            session.delete(pilote);

            // Commit de la transaction
            transaction.commit();

            // Vérification
            Pilote deletedPilote = session.get(Pilote.class, 123);
            assertNull(deletedPilote, "Le pilote doit être supprimé de la base de données");
        } catch (Exception e) {
            transaction.rollback();
            fail("La suppression du pilote a échoué : " + e.getMessage());
        }
    }

    @AfterAll
    void tearDown() {
        if (session != null) {
            session.close();
        }
    }
}
