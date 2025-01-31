package com.good.util.test;

import com.good.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QualificationTest {

    private Session session;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    void testCreateQualification() {
        Transaction transaction = session.beginTransaction();

        try {
            // Création de l'adresse à Lévis, Canada
            Adresse adresse = new Adresse(12, "Rue des Roses", "Lévis", "G6V 1Z2", "Québec", "Canada");
            session.save(adresse);

            // Création du pilote
            Pilote pilote = new Pilote(123, "Dupont", "Jean", adresse, "0102030405", LocalDate.of(2020, 1, 1), BigDecimal.valueOf(2500.00));
            session.save(pilote);

            // Création du type
            Type type = new Type("Avion", "Type d'avion");
            session.save(type);

            // Création de l'ID de la qualification
            QualificationId qualificationId = new QualificationId(123, "Avion");

            // Création de la qualification
            Qualification qualification = new Qualification(qualificationId, pilote, type);
            session.save(qualification);

            // Commit de la transaction
            transaction.commit();

            // Vérification
            assertNotNull(qualification.getId(), "L'ID de la qualification doit être généré");
            assertNotNull(qualification.getPilote(), "Le pilote associé à la qualification doit exister");
            assertNotNull(qualification.getType(), "Le type associé à la qualification doit exister");
        } catch (Exception e) {
            transaction.rollback();
            fail("La création de la qualification a échoué : " + e.getMessage());
        }
    }

    @Test
    void testReadQualification() {
        // Récupérer la qualification par son ID
        QualificationId qualificationId = new QualificationId(123, "Avion");
        Qualification qualification = session.get(Qualification.class, qualificationId);

        // Vérification
        assertNotNull(qualification, "La qualification doit exister");
        assertEquals("Dupont", qualification.getPilote().getNom(), "Le nom du pilote doit être 'Dupont'");
        assertEquals("Avion", qualification.getType().getNom(), "Le nom du type doit être 'Avion'");
    }

    @Test
    void testUpdateQualification() {
        Transaction transaction = session.beginTransaction();

        try {
            // Récupérer la qualification existante
            QualificationId qualificationId = new QualificationId(123, "Avion");
            Qualification qualification = session.get(Qualification.class, qualificationId);
            assertNotNull(qualification, "La qualification doit exister avant la mise à jour");

            // Mise à jour des informations
            qualification.getPilote().setNom("Durand");
            qualification.getType().setNom("Helicopter");
            session.update(qualification);

            // Commit de la transaction
            transaction.commit();

            // Vérification
            Qualification updatedQualification = session.get(Qualification.class, qualificationId);
            assertEquals("Durand", updatedQualification.getPilote().getNom(), "Le nom du pilote doit être mis à jour à 'Durand'");
            assertEquals("Helicopter", updatedQualification.getType().getNom(), "Le nom du type doit être mis à jour à 'Helicopter'");
        } catch (Exception e) {
            transaction.rollback();
            fail("La mise à jour de la qualification a échoué : " + e.getMessage());
        }
    }

    @Test
    void testDeleteQualification() {
        Transaction transaction = session.beginTransaction();

        try {
            // Récupérer la qualification existante
            QualificationId qualificationId = new QualificationId(123, "Avion");
            Qualification qualification = session.get(Qualification.class, qualificationId);
            assertNotNull(qualification, "La qualification doit exister avant la suppression");

            // Suppression de la qualification
            session.delete(qualification);

            // Commit de la transaction
            transaction.commit();

            // Vérification
            Qualification deletedQualification = session.get(Qualification.class, qualificationId);
            assertNull(deletedQualification, "La qualification doit être supprimée de la base de données");
        } catch (Exception e) {
            transaction.rollback();
            fail("La suppression de la qualification a échoué : " + e.getMessage());
        }
    }

    @AfterAll
    void tearDown() {
        if (session != null) {
            session.close();
        }
    }
}
