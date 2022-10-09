package com.monolito365.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.monolito365.common.constants.Constants;
import com.monolito365.utilities.DateUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema="monolito365", name="archivos_proyectos")
public class ProyectoArchivoDomain implements Serializable {
  private static final long serialVersionUID = 2878697413683046647L;

  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="proyecto_id", referencedColumnName="id", nullable=false)
	private ProyectoDomain proyecto;

	@Column(name="nombre", unique=true, nullable=false, length=100)
	private String nombre;

	@Column(name="ruta", nullable=false, length=255)
	private String ruta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at", nullable=true)
	private Date updatedAt;

	@Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{" + ProyectoArchivoDomain.class.getSimpleName() + ":");
    sb.append("id").append("=").append(this.id).append(", ");
    sb.append("proyecto").append("=").append(this.proyecto).append(", ");
    sb.append("nombre").append("=").append(this.nombre).append(", ");
    sb.append("ruta").append("=").append(this.ruta).append(", ");
    sb.append("createdAt").append("=").append(DateUtil.format(Constants.Format.DateTime.DateTime, this.createdAt)).append(", ");
    sb.append("updatedAt").append("=").append(updatedAt == null ? "null" : DateUtil.format(Constants.Format.DateTime.DateTime, this.updatedAt));
    sb.append("}");

    return sb.toString();
  }

}
