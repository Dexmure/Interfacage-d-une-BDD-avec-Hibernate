// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard

package com.good.util.test;

import com.good.util.Employe;
import com.good.util.Technicien;
import com.good.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TechnicienTest {

    private Session session;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    void testCreateTechnicien() {
        Transaction transaction = session.beginTransaction();

        // Créer un employé
        Employe employe = new Employe();
        employe.setNom("Martin");
        employe.setPrenom("Pierre");
        employe.setTel("0987654321");
        session.save(employe);

        // Créer un technicien lié à l'employé
        Technicien technicien = new Technicien();
        technicien.setMatricule(12345);
        technicien.setEmploye(employe);
        session.save(technicien);

        transaction.commit();

        // Vérification
        assertNotNull(technicien.getMatricule(), "Le technicien doit avoir un matricule après l'enregistrement");
    }

    @Test
    void testReadTechnicien() {
        // Lecture du technicien par matricule
        Technicien technicien = session.get(Technicien.class, 12345);  // Supposons que le technicien a le matricule 12345

        // Vérification
        assertNotNull(technicien, "Le technicien doit exister");
        assertEquals(12345, technicien.getMatricule(), "Le matricule du technicien doit être 12345");
    }

    @Test
    void testUpdateTechnicien() {
        Transaction transaction = session.beginTransaction();

        // Lecture du technicien existant
        Technicien technicien = session.get(Technicien.class, 12345); // Supposons que le technicien a le matricule 12345
        assertNotNull(technicien, "Le technicien doit exister avant la mise à jour");

        // Mise à jour du technicien
        technicien.getEmploye().setTel("0123456789");
        session.update(technicien);

        transaction.commit();

        // Vérification
        Technicien updatedTechnicien = session.get(Technicien.class, 12345);
        assertEquals("0123456789", updatedTechnicien.getEmploye().getTel(), "Le téléphone de l'employé du technicien doit être mis à jour");
    }

    @Test
    void testDeleteTechnicien() {
        Transaction transaction = session.beginTransaction();

        // Lecture du technicien existant
        Technicien technicien = session.get(Technicien.class, 12345); // Supposons que le technicien a le matricule 12345
        assertNotNull(technicien, "Le technicien doit exister avant la suppression");

        // Suppression
        session.delete(technicien);

        transaction.commit();

        // Vérification
        Technicien deletedTechnicien = session.get(Technicien.class, 12345);
        assertNull(deletedTechnicien, "Le technicien doit être supprimé");
    }

    @AfterAll
    void tearDown() {
        if (session != null) {
            session.close();
        }
    }
}
