package com.klef.jfsd_exam;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mysql.cj.Session;

public class ClientDemo {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        org.hibernate.Session session = null;

        try {
            // Insert records
            session = sessionFactory.openSession();
            session.beginTransaction();

            Client client1 = new Client();
            client1.setName("Pujitha");
            client1.setGender("Female");
            client1.setAge(18);
            client1.setLocation("India");
            client1.setEmail("pujitha22@gmail.com");
            client1.setMobileNumber("9876543210");

            Client client2 = new Client();
            client2.setName("Tanu");
            client2.setGender("Female");
            client2.setAge(20);
            client2.setLocation("Los Angeles");
            client2.setEmail("tanu5@gmail.com");
            client2.setMobileNumber("8765432190");

            session.save(client1);
            session.save(client2);
            session.getTransaction().commit();

            // Retrieve and print all records
            session = sessionFactory.openSession();
            Query<Client> query = session.createQuery("from Client", Client.class);
            List<Client> clients = query.getResultList();

            for (Client client : clients) {
                System.out.println(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
            sessionFactory.close();
        }
    }
}