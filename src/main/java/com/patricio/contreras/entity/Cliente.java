package com.patricio.contreras.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name ="clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =false)
	@NotEmpty
	@Size(min = 3,max = 60)
	private String nombre;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min =3, max = 60)
	private String apellido;
	
	@Column(unique = true,nullable=false,length = 90)
	private String email;
	
	@Column(nullable =false)
	@NotEmpty
	private int telefono;
	
	private static final long serialVersionUID = 1L;

}
