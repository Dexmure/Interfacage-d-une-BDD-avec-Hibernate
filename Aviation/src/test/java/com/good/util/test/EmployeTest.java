// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard

package com.good.util.test;

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
public class EmployeTest {

    private Session session;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    void testCreateEmploye() {
        Transaction transaction = session.beginTransaction();

        // Créer une adresse pour l'employé
        Adresse adresse = new Adresse(123, "Rue de Lévis", "Lévis", "G6V 4Z2", "Québec", "Canada");
        session.save(adresse);

        // Création d'un employé
        Employe employe = new Employe();
        employe.setNom("Dupont");
        employe.setPrenom("Jean");
        employe.setAdresse(adresse);
        employe.setTel("0123456789");
        employe.setDateEngagement(LocalDate.of(2022, 1, 15));
        employe.setSalaire(new BigDecimal("3000.00"));
        session.save(employe);

        transaction.commit();

        // Vérification
        assertNotNull(employe.getMatricule(), "L'employé doit avoir un matricule après l'enregistrement");
    }

    @Test
    void testReadEmploye() {
        // Lecture de l'employé par matricule (supposons que l'employé a le matricule 1)
        Employe employe = session.get(Employe.class, 1);

        // Vérification
        assertNotNull(employe, "L'employé doit exister");
        assertEquals("Dupont", employe.getNom(), "Le nom de l'employé doit être 'Dupont'");
    }

    @Test
    void testUpdateEmploye() {
        Transaction transaction = session.beginTransaction();

        // Lecture de l'employé existant
        Employe employe = session.get(Employe.class, 1); // Supposons que l'employé a le matricule 1
        assertNotNull(employe, "L'employé doit exister avant la mise à jour");

        // Mise à jour
        employe.setSalaire(new BigDecimal("3500.00"));
        session.update(employe);

        transaction.commit();

        // Vérification
        Employe updatedEmploye = session.get(Employe.class, 1);
        assertEquals(new BigDecimal("3500.00"), updatedEmploye.getSalaire(), "Le salaire de l'employé doit être mis à jour à 3500.00");
    }

    @Test
    void testDeleteEmploye() {
        Transaction transaction = session.beginTransaction();

        // Lecture de l'employé existant
        Employe employe = session.get(Employe.class, 1); // Supposons que l'employé a le matricule 1
        assertNotNull(employe, "L'employé doit exister avant la suppression");

        // Suppression
        session.delete(employe);

        transaction.commit();

        // Vérification
        Employe deletedEmploye = session.get(Employe.class, 1);
        assertNull(deletedEmploye, "L'employé doit être supprimé");
    }

    @AfterAll
    void tearDown() {
        if (session != null) {
            session.close();
        }
    }
}

