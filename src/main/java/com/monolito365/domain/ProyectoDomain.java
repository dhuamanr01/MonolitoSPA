package com.monolito365.domain;

import java.io.Serializable;
//import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.monolito365.common.constants.Constants;
import com.monolito365.utilities.DateUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"archivos"})
@Entity
@Table(schema="monolito365", name="proyectos")
public class ProyectoDomain implements Serializable {
  private static final long serialVersionUID = -3915106155870664737L;

  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="usuario_id", referencedColumnName="id", nullable=false)
	private UsuarioDomain usuario;

	@Column(name="proyecto", nullable=false, length=100)
	private String proyecto;

	@Column(name="descripcion", nullable=false, length=200)
	private String descripcion;

  @Column(name="estado", nullable=false)
	private Boolean estado;

  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_expiracion", nullable=false)
	private Date fechaExpiracion;

  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
	private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at", nullable=true)
	private Date updatedAt;

  @OneToMany(mappedBy="proyecto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private Set<ProyectoArchivoDomain> archivos = new HashSet<ProyectoArchivoDomain>(0);

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{" + ProyectoDomain.class.getSimpleName() + ":");
    sb.append("id").append("=").append(this.id).append(", ");
    sb.append("usuario").append("=").append(this.usuario).append(", ");
    sb.append("proyecto").append("=").append(this.proyecto).append(", ");
    sb.append("descripcion").append("=").append(this.descripcion).append(", ");
    sb.append("estado").append("=").append(this.estado).append(", ");
    sb.append("fechaExpiracion").append("=").append(DateUtil.format(Constants.Format.DateTime.DateTime, this.fechaExpiracion)).append(", ");
    sb.append("createdAt").append("=").append(DateUtil.format(Constants.Format.DateTime.DateTime, this.createdAt)).append(", ");
    sb.append("updatedAt").append("=").append(updatedAt == null ? "null" : DateUtil.format(Constants.Format.DateTime.DateTime, this.updatedAt));
    sb.append("}");

    return sb.toString();
  }

}
