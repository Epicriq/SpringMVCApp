package ru.asuslov.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.asuslov.springcourse.models.Person;
import ru.asuslov.springcourse.utils.HibernateSessionFactoryUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Component
public class PersonDAO {

    public List<Person> index() {
        List<Person> people = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("select p from Person p").list();
        return people;
    }

    public Person show(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Person.class, id);
    }

    public void save(Person person){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }

    public void update(int id, Person updatePerson){
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