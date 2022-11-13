package org.example.hibernatestarter;

import org.example.hibernatestarter.entity.Role;
import org.example.hibernatestarter.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();

//		либо так указывать что camelCase переводить в under_scores либо маппинг указывать в аннотации @Comlumn над полем класса
//		configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

		configuration.addAnnotatedClass(User.class);
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
			 Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			User user = User.builder()
					.username("ivan@gmail.com")
					.firstname("Ivan")
					.lastname("Ivanov")
					.birthDate(LocalDate.of(1991, 1, 15))
					.age(31)
					.role(Role.ADMIN)
					.build();
			session.save(user);
			session.getTransaction().commit();
		}
	}
}
