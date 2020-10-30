package com.ssafy.IMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" })})
public class User {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank
	@NaturalId
	@Column(name = "email")
	@Email
	private String email;

	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;

	@NotBlank
	@Column(name = "name")
	private String name;

	@Column(name = "company1")
	private String company1;

	@Column(name = "company2")
	private String company2;

	@Column(name = "company3")
	private String company3;

	public User(String email, String password, String name, String company1, String company2, String company3){
		this.email = email;
		this.password = password;
		this.name = name;
		this.company1 = company1;
		this.company2 = company2;
		this.company3 = company3;
	}

}
