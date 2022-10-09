package com.monolito365.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ResetTokenDTO implements Serializable{
  private static final long serialVersionUID = -8293691504428137310L;

	private Long id;
	private String token;
  private UsuarioDTO usuario;
	private String expiracion;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{" + ResetTokenDTO.class.getSimpleName() + ":");
    sb.append("id").append("=").append(this.id).append(", ");
    sb.append("token").append("=").append(this.token).append(", ");
    sb.append("usuario").append("=").append(this.usuario).append(", ");
    sb.append("expiracion").append("=").append(this.expiracion);
    sb.append("}");

    return sb.toString();
  }

}
