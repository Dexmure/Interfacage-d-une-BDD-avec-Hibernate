// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 1 relation
// 1 : N Avion

package com.good.util.test;

import com.good.util.HibernateUtil;
import com.good.util.Type;
import com.good.util.Avion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD) // Par méthode, pas par classe
public class TypeTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession(); // Ouvre une session avant chaque test
    }

    @AfterEach
    void tearDown() {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback(); // Annule la transaction si elle est active
        }
        session.close(); // Ferme la session après chaque test
    }

    @Test
    void testCreateType() {
        Transaction transaction = session.beginTransaction(); // Ouvre une nouvelle transaction
        Type type = new Type();
        type.setNom("Jet");
        type.setCapacite(100);
        type.setPoids(5000.0);
        type.setRayonAction(2000.0);
        session.save(type);
        transaction.commit(); // Commit de la transaction

        assertNotNull(type.getNom(), "Le nom du type ne doit pas être nul");
        assertEquals(100, type.getCapacite(), "La capacité du type devrait être 100");
    }

    @Test
    void testReadType() {
        Transaction transaction = session.beginTransaction();
        Type type = new Type();
        type.setNom("Avion de chasse");
        type.setCapacite(50);
        type.setPoids(4000.0);
        type.setRayonAction(1500.0);
        session.save(type);
        transaction.commit();

        Type retrievedType = session.get(Type.class, type.getNom());

        assertNotNull(retrievedType, "Le type récupéré ne devrait pas être nul");
        assertEquals("Avion de chasse", retrievedType.getNom(), "Le nom du type devrait correspondre");
    }

    @Test
    void testUpdateType() {
        Transaction transaction = session.beginTransaction();
        Type type = new Type();
        type.setNom("Hélicoptère");
        type.setCapacite(10);
        type.setPoids(1200.0);
        type.setRayonAction(1000.0);
        session.save(type);
        transaction.commit();

        transaction = session.beginTransaction(); // Nouvelle transaction
        type.setNom("Hélicoptère militaire");
        type.setCapacite(20);
        session.update(type);
        transaction.commit();

        Type updatedType = session.get(Type.class, type.getNom());
        assertEquals("Hélicoptère militaire", updatedType.getNom(), "Le nom du type devrait être mis à jour");
        assertEquals(20, updatedType.getCapacite(), "La capacité du type devrait être mise à jour");
    }

    @Test
    void testDeleteType() {
        Transaction transaction = session.beginTransaction();
        Type type = new Type();
        type.setNom("Avion de ligne");
        type.setCapacite(200);
        type.setPoids(8000.0);
        type.setRayonAction(3500.0);
        session.save(type);
        transaction.commit();

        transaction = session.beginTransaction();
        session.delete(type);
        transaction.commit();

        Type deletedType = session.get(Type.class, type.getNom());
        assertNull(deletedType, "Le type devrait être supprimé de la base de données");
    }

    @Test
    void testTypeRelationAvion() {
        Transaction transaction = session.beginTransaction();
        Type type = new Type();
        type.setNom("Transport");
        type.setCapacite(150);
        type.setPoids(6000.0);
        type.setRayonAction(2500.0);
        Avion avion1 = new Avion();  // Crée un avion et l'associe au type
        Avion avion2 = new Avion();  // Crée un autre avion
        avion1.setType(type);
        avion2.setType(type);
        type.setAvions(List.of(avion1, avion2));  // Liste d'avions
        session.save(type);
        session.save(avion1);
        session.save(avion2);
        transaction.commit();

        Type retrievedType = session.get(Type.class, type.getNom());
        assertNotNull(retrievedType, "Le type ne devrait pas être nul");
        assertEquals(2, retrievedType.getAvions().size(), "Le type devrait avoir deux avions associés");
    }
}
