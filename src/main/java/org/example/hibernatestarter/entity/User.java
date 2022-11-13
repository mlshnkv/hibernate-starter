package org.example.hibernatestarter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

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
	@Column(name = "birth_date")
	private LocalDate birthDate;
	@Enumerated(EnumType.STRING)
	private Role role;
	private Integer age;
}
