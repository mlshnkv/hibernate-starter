package org.example.hibernatestarter.converter;

import org.example.hibernatestarter.entity.Birthday;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.util.Optional;

//Либо прописываю в аннотации, либо в файле конфигурации
//@Converter(autoApply = true)
public class BirthdayConverter implements AttributeConverter<Birthday, Date> {
	@Override
	public Date convertToDatabaseColumn(Birthday attribute) {
		return Optional.ofNullable(attribute)
				.map(Birthday::birthDate)
				.map(Date::valueOf)
				.orElse(null);
	}

	@Override
	public Birthday convertToEntityAttribute(Date dbData) {
		return Optional.ofNullable(dbData)
				.map(Date::toLocalDate)
				.map(Birthday::new)
				.orElse(null);
	}
}
