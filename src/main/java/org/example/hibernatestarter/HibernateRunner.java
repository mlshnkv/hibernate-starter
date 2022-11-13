package org.example.hibernatestarter;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.example.hibernatestarter.converter.BirthdayConverter;
import org.example.hibernatestarter.entity.Birthday;
import org.example.hibernatestarter.entity.Role;
import org.example.hibernatestarter.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();

//		либо так указывать что camelCase переводить в under_scores либо маппинг указывать в аннотации @Comlumn над полем класса
//		configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

		configuration.addAnnotatedClass(User.class);
		//Либо вешаю аннотацию над полем в сущности @Convert(converter = BirthdayConverter.class), либо прописываю в классе конфигурации
		// autoApply в true либо здесь либо в аннотации @Converter над классом @Converter(autoApply = true)
		configuration.addAttributeConverter(BirthdayConverter.class, true);
		configuration.registerTypeOverride(new JsonBinaryType());
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
			 Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			User user = User.builder()
					.username("ivan@gmail.com")
					.firstname("Ivan")
					.lastname("Ivanov")
					.birthDate(new Birthday(LocalDate.of(1991, 1, 15)))
					.role(Role.ADMIN)
					.info("""
							{
							"name": "Ivan",
							"id": 25
							}
							""")
					.build();
			session.save(user);
			session.getTransaction().commit();
		}
	}
}
