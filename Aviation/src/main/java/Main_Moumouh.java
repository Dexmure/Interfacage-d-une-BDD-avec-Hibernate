import java.time.LocalDate;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.good.util.*;

public class Main_Moumouh {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        

        Scanner scanner = new Scanner(System.in);
        

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gérer les employés (Employé, Technicien, Pilote)");
            System.out.println("2. Gérer les adresse");
            System.out.println("3. Gérer les avions");
            System.out.println("4. Gérer les types d'avions");
            System.out.println("5. Gérer les tests ");
            System.out.println("6. Gérer  les associatons AvionTests");
            System.out.println("7. Gérer les réparations");
            System.out.println("8. Gérer les qualifications");
            System.out.println("9. Gérer les spécialisations");
            System.out.println("10. Gérer le pilotage (Piloter)");
            System.out.println("11. Gérer les examens");
            System.out.println("12. Gérer les examinations");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> gererEmployes(session, scanner);
                case 2 -> gererAdresses(session, scanner);
                case 3 -> gererAvions(session, scanner);
                case 4 -> gererTypes(session, scanner);
                case 5 -> gererTests(session, scanner);
                case 6 -> gererAssocierAvionTest(session, scanner);
                case 7 -> gererReparations(session, scanner);
                case 8 -> gererQualifications(session, scanner);
                case 9 -> gererSpecialisations(session, scanner);
                case 10 -> gererPiloter(session, scanner);
                case 11 -> gererExamen(session, scanner);
                case 12 -> gererExamination(session, scanner);
                case 0 -> {
                    System.out.println("Fermeture de l'application...");
                    session.close();
                    sessionFactory.close();
                    scanner.close();
                    return;
                }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    // 1. Gestion des employés (Employé, Technicien, Pilote)
    public static void gererEmployes(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des employés ---");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Rechercher un employé");
        System.out.println("3. Supprimer un employé");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.println("Est-ce un Technicien (1) ou un Pilote (2) ?");
            int typeEmploye = scanner.nextInt();
            scanner.nextLine();

            // Création de l'adresse
            Adresse adresse = new Adresse();
            System.out.print("Entrez le numéro de rue : ");
            adresse.setNumeroRue(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Entrez le nom de la rue : ");
            adresse.setNomRue(scanner.nextLine());
            System.out.print("Entrez la ville : ");
            adresse.setVille(scanner.nextLine());
            System.out.print("Entrez le code postal : ");
            adresse.setCodePostal(scanner.nextLine());
            System.out.print("Entrez la province : ");
            adresse.setProvince(scanner.nextLine());
            System.out.print("Entrez le pays : ");
            adresse.setPays(scanner.nextLine());
            
            // Sauvegarder l'adresse
            session.save(adresse);

            if (typeEmploye == 1) {
                // Technicien
                Technicien technicien = new Technicien();
                System.out.print("Entrez le matricule : ");
                technicien.setMatricule(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Entrez le nom : ");
                technicien.setNom(scanner.nextLine());
                System.out.print("Entrez le prénom : ");
                technicien.setPrenom(scanner.nextLine());
                System.out.print("Entrez le téléphone : ");
                technicien.setTel(scanner.nextLine());
                System.out.print("Entrez la date d'engagement (yyyy-MM-dd) : ");
                technicien.setDateEngagement(LocalDate.parse(scanner.nextLine()));
                System.out.print("Entrez le salaire : ");
                technicien.setSalaire(scanner.nextBigDecimal());
                technicien.setAdresse(adresse); // Associer l'adresse
                session.save(technicien);
                System.out.println("Technicien sauvegardé !");
            } else if (typeEmploye == 2) {
                // Pilote
                Pilote pilote = new Pilote();
                System.out.print("Entrez le matricule : ");
                pilote.setMatricule(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Entrez le nom : ");
                pilote.setNom(scanner.nextLine());
                System.out.print("Entrez le prénom : ");
                pilote.setPrenom(scanner.nextLine());
                System.out.print("Entrez le téléphone : ");
                pilote.setTel(scanner.nextLine());
                System.out.print("Entrez la date d'engagement (yyyy-MM-dd) : ");
                pilote.setDateEngagement(LocalDate.parse(scanner.nextLine()));
                System.out.print("Entrez le salaire : ");
                pilote.setSalaire(scanner.nextBigDecimal());
                pilote.setAdresse(adresse); // Associer l'adresse
                session.save(pilote);
                System.out.println("Pilote sauvegardé !");
            } else {
                System.out.println("Type d'employé invalide !");
            }
        } else if (choix == 2) {
            System.out.print("Entrez le matricule de l'employé : ");
            int matricule = scanner.nextInt();
            Employe employe = session.get(Employe.class, matricule);
            if (employe != null) {
                System.out.println("Employé trouvé : " + employe.getNom() + " " + employe.getPrenom());
            } else {
                System.out.println("Aucun employé trouvé avec ce matricule.");
            }
        } else if (choix == 3) {
            System.out.print("Entrez le matricule de l'employé à supprimer : ");
            int matricule = scanner.nextInt();
            Employe employe = session.get(Employe.class, matricule);
            if (employe != null) {
                session.delete(employe);
                System.out.println("Employé supprimé !");
            } else {
                System.out.println("Aucun employé trouvé avec ce matricule.");
            }
        } else {
            System.out.println("Choix invalide !");
        }
    }
    
    
    
 // CRUD pour Adresse
    private static void gererAdresses(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Adresses ---");
        System.out.println("1. Afficher toutes les adresses");
        System.out.println("2. Modifier une adresse");
        System.out.println("3. Supprimer une adresse");
        System.out.println("4. Rechercher une adresse (Hibernate Search)");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            
            case 1 -> { // Afficher toutes les adresses
                System.out.println("\n--- Liste des Adresses ---");
                session.createQuery("from Adresse", Adresse.class).list()
                    .forEach(adresse -> System.out.println(
                            "ID: " + adresse.getId() +
                            ", Numéro: " + adresse.getNumeroRue() +
                            ", Rue: " + adresse.getNomRue() +
                            ", Ville: " + adresse.getVille() +
                            ", Code Postal: " + adresse.getCodePostal() +
                            ", Province: " + adresse.getProvince() +
                            ", Pays: " + adresse.getPays()
                    ));
            }
            case 2 -> { // Modifier une adresse
                System.out.print("Entrez l'ID de l'adresse à modifier : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Adresse adresse = session.get(Adresse.class, id);
                if (adresse != null) {
                    System.out.print("Entrez la nouvelle ville : ");
                    adresse.setVille(scanner.nextLine());
                    System.out.print("Entrez le nouveau code postal : ");
                    adresse.setCodePostal(scanner.nextLine());

                    Transaction tx = session.beginTransaction();
                    session.update(adresse);
                    tx.commit();
                    System.out.println("Adresse mise à jour avec succès !");
                } else {
                    System.out.println("Adresse introuvable !");
                }
            }
            case 3 -> { // Supprimer une adresse
                System.out.print("Entrez l'ID de l'adresse à supprimer : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Adresse adresse = session.get(Adresse.class, id);
                if (adresse != null) {
                    Transaction tx = session.beginTransaction();
                    session.delete(adresse);
                    tx.commit();
                    System.out.println("Adresse supprimée !");
                } else {
                    System.out.println("Adresse introuvable !");
                }
            }
           
            default -> System.out.println("Choix invalide !");
        }
    }



    // 2. Gestion des avions
    private static void gererAvions(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Avions ---");
        System.out.println("1. Ajouter un Avion");
        System.out.println("2. Afficher les Avions");
        System.out.println("3. Supprimer un Avion");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("Matricule de l'Avion : ");
            int matricule = scanner.nextInt();
            scanner.nextLine();
            if (session.get(Avion.class, matricule) == null) {
                System.out.print("Type de l'Avion : ");
                String nomType = scanner.nextLine();
                Type type = session.createQuery("from Type where nom = :nom", Type.class).setParameter("nom", nomType).uniqueResult();
                if (type != null) {
                    Avion avion = new Avion();
                    avion.setMatricule(matricule);
                    avion.setType(type);
                    Transaction tx = session.beginTransaction();
                    session.save(avion);
                    tx.commit();
                    System.out.println("Avion ajouté avec succès.");
                } else {
                    System.out.println("Type introuvable.");
                }
            } else {
                System.out.println("Avion déjà existant.");
            }
        } else if (choix == 2) {
            session.createQuery("from Avion", Avion.class).list()
                    .forEach(avion -> System.out.println("Matricule : " + avion.getMatricule() + ", Type : " + avion.getType().getNom()));
        } else if (choix == 3) {
            System.out.print("Matricule de l'Avion à supprimer : ");
            int matricule = scanner.nextInt();
            Avion avion = session.get(Avion.class, matricule);
            if (avion != null) {
                Transaction tx = session.beginTransaction();
                session.delete(avion);
                tx.commit();
                System.out.println("Avion supprimé.");
            } else {
                System.out.println("Avion introuvable.");
            }
        }
    }

    // 3. Gestion des types d'avions
    private static void gererTypes(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Types ---");
        System.out.println("1. Ajouter un Type");
        System.out.println("2. Afficher tous les Types");
        System.out.println("3. Supprimer un Type");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("Nom du Type : ");
            String nom = scanner.nextLine();
            if (session.createQuery("from Type where nom = :nom", Type.class).setParameter("nom", nom).uniqueResult() == null) {
                Type type = new Type();
                type.setNom(nom);
                System.out.print("Capacité : ");
                type.setCapacite(scanner.nextInt());
                System.out.print("Poids : ");
                type.setPoids(scanner.nextDouble());
                System.out.print("Rayon d'action : ");
                type.setRayonAction(scanner.nextDouble());
                Transaction tx = session.beginTransaction();
                session.save(type);
                tx.commit();
                System.out.println("Type ajouté avec succès.");
            } else {
                System.out.println("Type déjà existant.");
            }
        } else if (choix == 2) {
            session.createQuery("from Type", Type.class).list()
                    .forEach(type -> System.out.println("Nom : " + type.getNom() + ", Capacité : " + type.getCapacite()));
        } else if (choix == 3) {
            System.out.print("Nom du Type à supprimer : ");
            String nom = scanner.nextLine();
            Type type = session.createQuery("from Type where nom = :nom", Type.class).setParameter("nom", nom).uniqueResult();
            if (type != null) {
                Transaction tx = session.beginTransaction();
                session.delete(type);
                tx.commit();
                System.out.println("Type supprimé.");
            } else {
                System.out.println("Type introuvable.");
            }
        }
    }

    // 4. Gestion des tests
 // Gestion des tests
    private static void gererTests(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Tests ---");
        System.out.println("1. Ajouter un test");
        System.out.println("2. Afficher tous les tests");
        System.out.println("3. Supprimer un test");
        System.out.println("4. Rechercher un test par numéro");
        System.out.println("5. Modifier un test");
        System.out.println("6. Rechercher un test par nom ou seuil (Hibernate Search)");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> { // Ajouter un test
                System.out.print("Entrez le numéro du test : ");
                int numero = scanner.nextInt();
                scanner.nextLine();
                Test test = session.get(Test.class, numero);
                if (test == null) {
                    test = new Test();
                    test.setNumero(numero);
                    System.out.print("Entrez le nom du test : ");
                    test.setNom(scanner.nextLine());
                    System.out.print("Entrez le seuil du test : ");
                    test.setSeuil(scanner.nextDouble());
                    Transaction tx = session.beginTransaction();
                    session.save(test);
                    tx.commit();
                    System.out.println("Test ajouté avec succès !");
                } else {
                    System.out.println("Un test avec ce numéro existe déjà.");
                }
            }
            case 2 -> { // Afficher tous les tests
                session.createQuery("from Test", Test.class).list()
                    .forEach(test -> System.out.println("Numéro: " + test.getNumero() + ", Nom: " + test.getNom() + ", Seuil: " + test.getSeuil()));
            }
            case 3 -> { // Supprimer un test
                System.out.print("Entrez le numéro du test à supprimer : ");
                int numero = scanner.nextInt();
                Test test = session.get(Test.class, numero);
                if (test != null) {
                    Transaction tx = session.beginTransaction();
                    session.delete(test);
                    tx.commit();
                    System.out.println("Test supprimé !");
                } else {
                    System.out.println("Test introuvable.");
                }
            }
            case 4 -> { // Rechercher un test par numéro
                System.out.print("Entrez le numéro du test à rechercher : ");
                int numero = scanner.nextInt();
                Test test = session.get(Test.class, numero);
                if (test != null) {
                    System.out.println("Test trouvé : Numéro: " + test.getNumero() + ", Nom: " + test.getNom() + ", Seuil: " + test.getSeuil());
                } else {
                    System.out.println("Aucun test trouvé avec ce numéro.");
                }
            }
            case 5 -> { // Modifier un test
                System.out.print("Entrez le numéro du test à modifier : ");
                int numero = scanner.nextInt();
                scanner.nextLine();
                Test test = session.get(Test.class, numero);
                if (test != null) {
                    System.out.print("Entrez le nouveau nom du test : ");
                    test.setNom(scanner.nextLine());
                    System.out.print("Entrez le nouveau seuil du test : ");
                    test.setSeuil(scanner.nextDouble());
                    Transaction tx = session.beginTransaction();
                    session.update(test);
                    tx.commit();
                    System.out.println("Test mis à jour avec succès !");
                } else {
                    System.out.println("Test introuvable !");
                }
            }
            
            default -> System.out.println("Choix invalide.");
        }
    }

 // 5. Gestion des AvionTests
    private static void gererAssocierAvionTest(Session session, Scanner scanner) {
        System.out.println("\n--- Associer un Avion à un Test ---");
        System.out.println("1. Ajouter une association Avion-Test");
        System.out.println("2. Afficher toutes les associations Avion-Test");
        System.out.println("3. Supprimer une association Avion-Test");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Entrez le matricule de l'avion : ");
                int avionMatricule = scanner.nextInt();
                scanner.nextLine();
                Avion avion = session.get(Avion.class, avionMatricule);
                if (avion == null) {
                    System.out.println("Avion introuvable.");
                    return;
                }

                System.out.print("Entrez le numéro du test : ");
                int testNumero = scanner.nextInt();
                scanner.nextLine();
                Test test = session.get(Test.class, testNumero);
                if (test == null) {
                    System.out.println("Test introuvable.");
                    return;
                }

                System.out.print("Entrez la date du test (yyyy-MM-dd) : ");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                AvionTest avionTest = session.createQuery("from AvionTest where avion.matricule = :matricule and test.numero = :numero and dateTest = :date", AvionTest.class)
                    .setParameter("matricule", avionMatricule)
                    .setParameter("numero", testNumero)
                    .setParameter("date", java.sql.Date.valueOf(date))
                    .uniqueResult();

                if (avionTest == null) {
                    avionTest = new AvionTest();
                    avionTest.setAvion(avion);
                    avionTest.setTest(test);
                    avionTest.setDateTest(java.sql.Date.valueOf(date));
                    Transaction tx = session.beginTransaction();
                    session.save(avionTest);
                    tx.commit();
                    System.out.println("Association Avion-Test ajoutée avec succès !");
                } else {
                    System.out.println("L'association Avion-Test existe déjà.");
                }
            }
            case 2 -> {
                session.createQuery("from AvionTest", AvionTest.class).list()
                    .forEach(avionTest -> System.out.println(
                        "Avion: " + avionTest.getAvion().getMatricule() +
                        ", Test: " + avionTest.getTest().getNom() +
                        ", Date: " + avionTest.getDateTest()));
            }
            case 3 -> {
                System.out.print("Entrez le matricule de l'avion : ");
                int avionMatricule = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Entrez le numéro du test : ");
                int testNumero = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Entrez la date de l'association (yyyy-MM-dd) : ");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                AvionTest avionTest = session.createQuery("from AvionTest where avion.matricule = :matricule and test.numero = :numero and dateTest = :date", AvionTest.class)
                    .setParameter("matricule", avionMatricule)
                    .setParameter("numero", testNumero)
                    .setParameter("date", java.sql.Date.valueOf(date))
                    .uniqueResult();

                if (avionTest != null) {
                    Transaction tx = session.beginTransaction();
                    session.delete(avionTest);
                    tx.commit();
                    System.out.println("Association Avion-Test supprimée.");
                } else {
                    System.out.println("Association introuvable.");
                }
            }
            default -> System.out.println("Choix invalide.");
        }
    }


    // 6. Gestion des réparations
    private static void gererReparations(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Réparations ---");
        System.out.println("1. Ajouter une réparation");
        System.out.println("2. Afficher toutes les réparations");
        System.out.println("3. Supprimer une réparation");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        if (choix == 1) {
            Reparation reparation = new Reparation();

            // Associer un avion
            System.out.print("Entrez le matricule de l'avion : ");
            int matriculeAvion = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante
            Avion avion = session.get(Avion.class, matriculeAvion);

            if (avion == null) {
                System.out.println("Avion introuvable !");
                return; // Arrêter si l'avion n'existe pas
            }
            reparation.setAvion(avion); // Lier l'objet Avion
            reparation.setAvionMatricule(matriculeAvion); // Lier le matricule pour la clé composite

            // Associer un technicien
            System.out.print("Entrez le matricule du technicien : ");
            int matriculeTechnicien = scanner.nextInt();
            scanner.nextLine();
            Technicien technicien = session.get(Technicien.class, matriculeTechnicien);

            if (technicien == null) {
                System.out.println("Technicien introuvable !");
                return; // Arrêter si le technicien n'existe pas
            }
            reparation.setTechnicien(technicien); // Lier l'objet Technicien
            reparation.setTechnicienMatricule(matriculeTechnicien); // Lier le matricule pour la clé composite

            // Renseigner les autres champs
            System.out.print("Entrez le coût total : ");
            reparation.setCoutTotal(scanner.nextBigDecimal());
            System.out.print("Entrez la date de la réparation (yyyy-MM-dd) : ");
            reparation.setDate(LocalDate.parse(scanner.next()));

            // Sauvegarder la réparation
            Transaction tx = session.beginTransaction();
            session.save(reparation);
            tx.commit();
            System.out.println("Réparation ajoutée avec succès !");
        } else if (choix == 2) {
            session.createQuery("from Reparation", Reparation.class).list().forEach(rep -> {
                System.out.println("Avion : " + rep.getAvion().getMatricule() +
                                   ", Technicien : " + rep.getTechnicien().getMatricule() +
                                   ", Coût : " + rep.getCoutTotal() +
                                   ", Date : " + rep.getDate());
            });
        } else if (choix == 3) {
            System.out.print("Entrez le matricule de l'avion : ");
            int matriculeAvion = scanner.nextInt();
            System.out.print("Entrez le matricule du technicien : ");
            int matriculeTechnicien = scanner.nextInt();

            ReparationId reparationId = new ReparationId(matriculeAvion, matriculeTechnicien);
            Reparation reparation = session.get(Reparation.class, reparationId);
            if (reparation != null) {
                Transaction tx = session.beginTransaction();
                session.delete(reparation);
                tx.commit();
                System.out.println("Réparation supprimée avec succès !");
            } else {
                System.out.println("Réparation introuvable !");
            }
        } else {
            System.out.println("Choix invalide !");
        }
    }


    // 7. Gestion des qualifications
    private static void gererQualifications(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Qualifications ---");
        System.out.println("1. Ajouter une Qualification");
        System.out.println("2. Afficher toutes les Qualifications");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("Matricule du Pilote : ");
            int piloteMatricule = scanner.nextInt();
            scanner.nextLine();
            Pilote pilote = session.get(Pilote.class, piloteMatricule);
            if (pilote != null) {
                System.out.print("Nom du Type : ");
                String typeNom = scanner.nextLine();
                Type type = session.get(Type.class, typeNom);
                if (type != null) {
                    Qualification qualification = new Qualification();
                    qualification.setPilote(pilote);
                    qualification.setType(type);
                    qualification.setId(new QualificationId(piloteMatricule, typeNom));
                    Transaction tx = session.beginTransaction();
                    session.save(qualification);
                    tx.commit();
                    System.out.println("Qualification ajoutée.");
                } else {
                    System.out.println("Type introuvable.");
                }
            } else {
                System.out.println("Pilote introuvable.");
            }
        } else if (choix == 2) {
            session.createQuery("from Qualification", Qualification.class).list()
                    .forEach(qual -> System.out.println(
                            "Pilote : " + qual.getPilote().getNom() +
                            ", Type : " + qual.getType().getNom()));
        }
    }

    // 8. Gestion des spécialisations
    private static void gererSpecialisations(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Spécialisations ---");
        System.out.println("1. Ajouter une Spécialisation");
        System.out.println("2. Afficher toutes les Spécialisations");
        System.out.println("3. Supprimer une Spécialisation");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("Matricule du Technicien : ");
            int techMatricule = scanner.nextInt();
            scanner.nextLine();
            Technicien technicien = session.get(Technicien.class, techMatricule);
            if (technicien != null) {
                System.out.print("Nom du Type : ");
                String typeNom = scanner.nextLine();
                Type type = session.get(Type.class, typeNom);
                if (type != null) {
                    Specialisation specialisation = new Specialisation();
                    specialisation.setTechnicien(technicien);
                    specialisation.setType(type);
                    specialisation.setId(new SpecialisationId(techMatricule, typeNom));
                    Transaction tx = session.beginTransaction();
                    session.save(specialisation);
                    tx.commit();
                    System.out.println("Spécialisation ajoutée.");
                } else {
                    System.out.println("Type introuvable.");
                }
            } else {
                System.out.println("Technicien introuvable.");
            }
        } else if (choix == 2) {
            session.createQuery("from Specialisation", Specialisation.class).list()
                    .forEach(spec -> System.out.println(
                            "Technicien : " + spec.getTechnicien().getNom() +
                            ", Type : " + spec.getType().getNom()));
        } else if (choix == 3) {
            System.out.print("Matricule du Technicien : ");
            int techMatricule = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nom du Type : ");
            String typeNom = scanner.nextLine();
            Specialisation specialisation = session.get(Specialisation.class, new SpecialisationId(techMatricule, typeNom));
            if (specialisation != null) {
                Transaction tx = session.beginTransaction();
                session.delete(specialisation);
                tx.commit();
                System.out.println("Spécialisation supprimée.");
            } else {
                System.out.println("Spécialisation introuvable.");
            }
        }
    }

    // 9. Gestion du pilotage (Piloter)
    private static void gererPiloter(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Associations Pilote-Avion ---");
        System.out.println("1. Ajouter une association Pilote-Avion");
        System.out.println("2. Afficher toutes les associations Pilote-Avion");
        System.out.println("3. Supprimer une association Pilote-Avion");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Entrez le matricule du pilote : ");
                int piloteMatricule = scanner.nextInt();
                scanner.nextLine();
                Pilote pilote = session.get(Pilote.class, piloteMatricule);
                if (pilote == null) {
                    System.out.println("Pilote introuvable.");
                    return;
                }

                System.out.print("Entrez le matricule de l'avion : ");
                int avionMatricule = scanner.nextInt();
                scanner.nextLine();
                Avion avion = session.get(Avion.class, avionMatricule);
                if (avion == null) {
                    System.out.println("Avion introuvable.");
                    return;
                }

                PiloterId piloterId = new PiloterId();
                piloterId.setPilote_matricule(piloteMatricule);
                piloterId.setAvion_matricule(avionMatricule);

                Piloter piloter = session.get(Piloter.class, piloterId);
                if (piloter == null) {
                    piloter = new Piloter();
                    piloter.setId(piloterId);
                    piloter.setPilote(pilote);
                    piloter.setAvion(avion);
                    Transaction tx = session.beginTransaction();
                    session.save(piloter);
                    tx.commit();
                    System.out.println("Association Pilote-Avion ajoutée avec succès !");
                } else {
                    System.out.println("L'association Pilote-Avion existe déjà.");
                }
            }
            case 2 -> {
                session.createQuery("from Piloter", Piloter.class).list()
                    .forEach(piloter -> System.out.println(
                        "Pilote: " + piloter.getPilote().getNom() +
                        ", Avion: " + piloter.getAvion().getMatricule()));
            }
            case 3 -> {
                System.out.print("Entrez le matricule du pilote : ");
                int piloteMatricule = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Entrez le matricule de l'avion : ");
                int avionMatricule = scanner.nextInt();
                scanner.nextLine();

                PiloterId piloterId = new PiloterId();
                piloterId.setPilote_matricule(piloteMatricule);
                piloterId.setAvion_matricule(avionMatricule);

                Piloter piloter = session.get(Piloter.class, piloterId);
                if (piloter != null) {
                    Transaction tx = session.beginTransaction();
                    session.delete(piloter);
                    tx.commit();
                    System.out.println("Association Pilote-Avion supprimée.");
                } else {
                    System.out.println("Association introuvable.");
                }
            }
            default -> System.out.println("Choix invalide.");
        }
    }


    // 10. Gestion des examens
    private static void gererExamen(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Examens ---");
        System.out.println("1. Ajouter un examen");
        System.out.println("2. Afficher tous les examens");
        System.out.println("3. Supprimer un examen");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                Examen examen = new Examen();
                System.out.print("Entrez la description de l'examen : ");
                examen.setDescription(scanner.nextLine());
                System.out.print("Entrez le nom (examencol) de l'examen : ");
                examen.setExamencol(scanner.nextLine());
                Transaction tx = session.beginTransaction();
                session.save(examen);
                tx.commit();
                System.out.println("Examen ajouté avec succès !");
            }
            case 2 -> {
                session.createQuery("from Examen", Examen.class).list()
                    .forEach(examen -> System.out.println(
                        "Examen ID: " + examen.getIdentifiant() +
                        ", Description: " + examen.getDescription() +
                        ", Examencol: " + examen.getExamencol()));
            }
            case 3 -> {
                System.out.print("Entrez l'ID de l'examen à supprimer : ");
                int examenId = scanner.nextInt();
                scanner.nextLine();
                Examen examen = session.get(Examen.class, examenId);
                if (examen != null) {
                    Transaction tx = session.beginTransaction();
                    session.delete(examen);
                    tx.commit();
                    System.out.println("Examen supprimé.");
                } else {
                    System.out.println("Examen introuvable.");
                }
            }
            default -> System.out.println("Choix invalide.");
        }
    }
    
    
    // 11. Gestion des  examinations
    
 
    private static void gererExamination(Session session, Scanner scanner) {
        System.out.println("\n--- Gestion des Associations Pilote-Examen (Examination) ---");
        System.out.println("1. Ajouter une examination");
        System.out.println("2. Afficher toutes les examinations");
        System.out.println("3. Supprimer une examination");
        System.out.println("4. Modifier une examination");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> { // Ajouter une examination
                System.out.print("Entrez le matricule du pilote : ");
                int piloteMatricule = scanner.nextInt();
                scanner.nextLine();
                Pilote pilote = session.get(Pilote.class, piloteMatricule);
                if (pilote == null) {
                    System.out.println("Pilote introuvable.");
                    return;
                }

                System.out.print("Entrez l'ID de l'examen : ");
                int examenId = scanner.nextInt();
                scanner.nextLine();
                Examen examen = session.get(Examen.class, examenId);
                if (examen == null) {
                    System.out.println("Examen introuvable.");
                    return;
                }

                ExaminationId examinationId = new ExaminationId();
                examinationId.setPilote_matricule(piloteMatricule);
                examinationId.setExamen_identifiant(examenId);

                Examination examination = session.get(Examination.class, examinationId);
                if (examination == null) {
                    examination = new Examination();
                    examination.setId(examinationId);
                    examination.setPilote_matricule(pilote);
                    examination.setExamen_identifiant(examen);
                    System.out.print("Entrez la date de l'examination (yyyy-MM-dd) : ");
                    examination.setDate(java.sql.Date.valueOf(LocalDate.parse(scanner.nextLine())));
                    System.out.print("Entrez le rapport de l'examination : ");
                    examination.setRapport(scanner.nextLine());
                    Transaction tx = session.beginTransaction();
                    session.save(examination);
                    tx.commit();
                    System.out.println("Examination ajoutée avec succès !");
                } else {
                    System.out.println("L'examination existe déjà.");
                }
            }
            case 2 -> { // Afficher toutes les examinations
                session.createQuery("from Examination", Examination.class).list()
                    .forEach(examination -> System.out.println(
                        "Pilote: " + examination.getPilote_matricule().getNom() +
                        ", Examen: " + examination.getExamen_identifiant().getDescription() +
                        ", Date: " + examination.getDate() +
                        ", Rapport: " + examination.getRapport()));
            }
            case 3 -> { // Supprimer une examination
                System.out.print("Entrez le matricule du pilote : ");
                int piloteMatricule = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Entrez l'ID de l'examen : ");
                int examenId = scanner.nextInt();
                scanner.nextLine();

                ExaminationId examinationId = new ExaminationId();
                examinationId.setPilote_matricule(piloteMatricule);
                examinationId.setExamen_identifiant(examenId);

                Examination examination = session.get(Examination.class, examinationId);
                if (examination != null) {
                    Transaction tx = session.beginTransaction();
                    session.delete(examination);
                    tx.commit();
                    System.out.println("Examination supprimée.");
                } else {
                    System.out.println("Examination introuvable.");
                }
            }
            case 4 -> { // Modifier une examination
                System.out.print("Entrez le matricule du pilote : ");
                int piloteMatricule = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Entrez l'ID de l'examen : ");
                int examenId = scanner.nextInt();
                scanner.nextLine();

                ExaminationId examinationId = new ExaminationId();
                examinationId.setPilote_matricule(piloteMatricule);
                examinationId.setExamen_identifiant(examenId);

                Examination examination = session.get(Examination.class, examinationId);
                if (examination != null) {
                    System.out.print("Entrez la nouvelle date de l'examination (yyyy-MM-dd) : ");
                    examination.setDate(java.sql.Date.valueOf(LocalDate.parse(scanner.nextLine())));
                    System.out.print("Entrez le nouveau rapport de l'examination : ");
                    examination.setRapport(scanner.nextLine());
                    Transaction tx = session.beginTransaction();
                    session.update(examination);
                    tx.commit();
                    System.out.println("Examination mise à jour avec succès !");
                } else {
                    System.out.println("Examination introuvable.");
                }
            }
            default -> System.out.println("Choix invalide.");
        }
    }



}
