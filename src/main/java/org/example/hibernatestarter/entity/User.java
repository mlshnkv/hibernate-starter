package org.example.hibernatestarter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernatestarter.converter.BirthdayConverter;

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
}
