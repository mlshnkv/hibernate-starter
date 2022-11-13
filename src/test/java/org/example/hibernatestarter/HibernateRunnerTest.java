package org.example.hibernatestarter;

import org.example.hibernatestarter.entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

class HibernateRunnerTest {

	@Test
	void checkReflectionApi() throws SQLException, IllegalAccessException {
		User user = User.builder()
				.username("ivan@gmail.com")
				.firstname("Ivan")
				.lastname("Ivanov")
				.birthDate(LocalDate.of(1991, 1, 15))
				.age(31)
				.build();

		String sql = """
				insert
				into
				%s
				(%s)
				values
				(%s)
				""";
		String tableName = ofNullable(user.getClass().getAnnotation(Table.class))
				.map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
				.orElse(user.getClass().getName());
		Field[] declaredFields = user.getClass().getDeclaredFields();
		String columnNames = Arrays.stream(user.getClass().getDeclaredFields())
				.map(field -> ofNullable(field.getAnnotation(Column.class))
						.map(Column::name)
						.orElse(field.getName()))
				.collect(Collectors.joining(", "));
		String columnValues = Arrays.stream(declaredFields)
				.map(field -> "?")
				.collect(Collectors.joining(", "));

		System.out.println(sql.formatted(tableName, columnNames, columnValues));

		Connection connection = null;
		PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));

		for (int i = 0; i < declaredFields.length; i++) {
			preparedStatement.setObject(i, declaredFields[i].get(user));
		}

	}
}