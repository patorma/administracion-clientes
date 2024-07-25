package com.patricio.contreras.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@NotEmpty
	private String email;
	
	@Column(nullable =false)
	@NotNull(message = "no puede estar vacio el telefono2")
	private Integer telefono;
	
	@Column(name = "fecha_nacimiento")
	@NotNull(message = "no puede estar vacia la fecha")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@NotNull(message = "no puede estar vacio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ciudad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Ciudad ciudad;
	
	private static final long serialVersionUID = 1L;

}
