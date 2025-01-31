// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard

package com.good.util.test;

import com.good.util.Adresse;
import com.good.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD) // Par méthode, pas par classe
public class AdresseTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession(); // Ouvre une session avant chaque test
    }

    @AfterEach
    void tearDown() {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback(); // Assure-toi que la transaction est annulée si elle est active
        }
        session.close(); // Ferme la session après chaque test
    }

    @Test
    void testCreateAdresse() {
        Transaction transaction = session.beginTransaction(); // Ouvre une nouvelle transaction pour ce test
        Adresse adresse = new Adresse(123, "Rue des Lilas", "Lévis", "G6V 4Z7", "Québec", "Canada");
        session.save(adresse);
        transaction.commit(); // Commit de la transaction

        assertNotNull(adresse.getId(), "L'adresse devrait avoir un ID après l'enregistrement");
    }

    @Test
    void testReadAdresse() {
        Transaction transaction = session.beginTransaction();
        Adresse adresse = new Adresse(456, "Rue des Roses", "Lévis", "G6V 4Z8", "Québec", "Canada");
        session.save(adresse);
        transaction.commit();

        Adresse retrievedAdresse = session.get(Adresse.class, adresse.getId());

        assertNotNull(retrievedAdresse, "L'adresse récupérée ne devrait pas être nulle");
        assertEquals("Rue des Roses", retrievedAdresse.getNomRue(), "Le nom de la rue devrait correspondre");
    }

    @Test
    void testUpdateAdresse() {
        Transaction transaction = session.beginTransaction();
        Adresse adresse = new Adresse(789, "Rue des Tulipes", "Lévis", "G6V 4Z9", "Québec", "Canada");
        session.save(adresse);
        transaction.commit();

        transaction = session.beginTransaction(); // Ouvre une nouvelle transaction
        adresse.setNomRue("Rue des Marguerites");
        session.update(adresse);
        transaction.commit();

        Adresse updatedAdresse = session.get(Adresse.class, adresse.getId());
        assertEquals("Rue des Marguerites", updatedAdresse.getNomRue(), "Le nom de la rue devrait être mis à jour");
    }

    @Test
    void testDeleteAdresse() {
        Transaction transaction = session.beginTransaction();
        Adresse adresse = new Adresse(321, "Rue des Coquelicots", "Lévis", "G6V 4Z6", "Québec", "Canada");
        session.save(adresse);
        transaction.commit();

        transaction = session.beginTransaction();
        session.delete(adresse);
        transaction.commit();

        Adresse deletedAdresse = session.get(Adresse.class, adresse.getId());
        assertNull(deletedAdresse, "L'adresse devrait être supprimée de la base de données");
    }
}
