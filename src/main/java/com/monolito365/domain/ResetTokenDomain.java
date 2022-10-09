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
@Table(schema="monolito365", name="reset_token")
public class ResetTokenDomain implements Serializable{
  private static final long serialVersionUID = -8293691504428137310L;

  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="token", nullable=false, unique=true, length=255)
	private String token;

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
  @JoinColumn(name="id_usuario", referencedColumnName="id", nullable=false)
  private UsuarioDomain usuario;

	@Temporal(TemporalType.TIMESTAMP)
  @Column(name="expiracion", nullable=false)
	private Date expiracion;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{" + ResetTokenDomain.class.getSimpleName() + ":");
    sb.append("id").append("=").append(this.id).append(", ");
    sb.append("token").append("=").append(this.token).append(", ");
    sb.append("usuario").append("=").append(this.usuario).append(", ");
    sb.append("expiracion").append("=").append(DateUtil.format(Constants.Format.DateTime.DateTime, this.expiracion));
    sb.append("}");

    return sb.toString();
  }

}
