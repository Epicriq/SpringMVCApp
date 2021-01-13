package ru.asuslov.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.asuslov.springcourse.models.Person;
import ru.asuslov.springcourse.utils.HibernateSessionFactoryUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

@Component
public class PersonDAO {

    public List<Person> index() {
        List<Person> people = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("select p from Person p").list();
        return people;
    }

    public Person show(Integer id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Person.class, id);
    }

    public Boolean isExists(String email){
        String query = "SELECT count(1) FROM Person p WHERE p.email='" + email + "'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            if ((Long)session.createQuery(query).uniqueResult() == 0)
                return false;
            else
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void save(Person person){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }

    public void update(Integer id, Person updatePerson){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updatePerson);
        transaction.commit();
        session.close();
    }

    public void delete(Person person) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(person);
        transaction.commit();
        session.close();
    }
}