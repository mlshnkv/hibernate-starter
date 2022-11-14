package org.example.hibernatestarter.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernatestarter.converter.BirthdayConverter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
@TypeDef(name = "mlshnkv",typeClass = JsonBinaryType.class)
public class User {

	@Id
	private String username;

	private String firstname;

	private String lastname;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "birth_date")
	@Convert(converter = BirthdayConverter.class)
	private Birthday birthDate;

	//jsonb это более локаничное название класса:
	// вместо @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType")
	//указываем @Type(type = "jsonb"), потому что "jsonb" возвращается методом:
	// com.vladmihalcea.hibernate.type.json.JsonBinaryType.getName
//	@Type(type = "jsonb")
	@Type(type = "mlshnkv")
	private String info;
}
