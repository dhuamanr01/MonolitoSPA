package com.monolito365.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class UsuarioDTO implements Serializable {
  private static final long serialVersionUID = -4872705658825231581L;

	private Integer id;

	private RoleDTO role;

	@JsonProperty("user_name")
	private String userName;

	private String password;

	private Boolean estado;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	private List<ResetTokenDTO> tokens = new ArrayList<ResetTokenDTO>();

	private List<ProyectoDTO> proyectos = new ArrayList<ProyectoDTO>();

	private List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{" + UsuarioDTO.class.getSimpleName() + ":");
    sb.append("id").append("=").append(this.id).append(", ");
    sb.append("role").append("=").append(this.role).append(", ");
    sb.append("userName").append("=").append(this.userName).append(", ");
    sb.append("password").append("=").append(this.password).append(", ");
    sb.append("estado").append("=").append(this.estado).append(", ");
    sb.append("createdAt").append("=").append(createdAt).append(", ");
    sb.append("updatedAt").append("=").append(updatedAt);
    sb.append("}");

    return sb.toString();
  }

}