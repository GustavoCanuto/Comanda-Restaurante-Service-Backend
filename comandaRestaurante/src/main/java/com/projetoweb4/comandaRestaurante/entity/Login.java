package com.projetoweb4.comandaRestaurante.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoCadastrar;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.text.Normalizer;

@Table(name = "tb_login")
@Entity(name = "Login")
public class Login  implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String senha;
	
	@ManyToOne
    @JoinColumn(name = "fk_funcionario")
    private Funcionario funcionario;
	
	@ManyToOne
    @JoinColumn(name = "fk_status_geral")
    private StatusGeral statusGeral;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	 // Adiciona o prefixo ROLE_ ao cargo, que é necessário pelo Spring Security
    	   // Remove caracteres especiais e converte para maiúsculas
    	 String role = formatarRole(funcionario.getCargoFuncionario().getCargo());

    	 return List.of(new SimpleGrantedAuthority(role));

       // return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
	public Login(Long id, String email, String senha, Funcionario funcionario, StatusGeral statusGeral) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.funcionario = funcionario;
		this.statusGeral = statusGeral;
	}

	public Login() {
		super();
	}

	public Login(LoginDtoCadastrar dados, Funcionario funcionario, StatusGeral statusGeral) {
		this.email = dados.email();
		this.senha = dados.senha();
		this.funcionario = funcionario;
		this.statusGeral = statusGeral;
	}

	public void atualizarInformacoes(LoginDtoCadastrar dados, Funcionario funcionario, StatusGeral statusGeral) {

		if (dados.email() != null && !dados.email().isBlank()) {
			this.email = dados.email();
		}
		
		if (dados.senha() != null && !dados.senha().isBlank()) {
			this.senha = dados.senha();
		}
		
		if (funcionario != null) {
			this.funcionario = funcionario;
		}
		
		if (statusGeral != null) {
			this.statusGeral = statusGeral;
		}
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public StatusGeral getStatusGeral() {
		return statusGeral;
	}

	public void setStatusGeral(StatusGeral statusGeral) {
		this.statusGeral = statusGeral;
	}
	
	public String formatarRole(String cargo) {
	    // Remove caracteres especiais e converte para maiúsculas
	    String role = Normalizer.normalize(cargo, Normalizer.Form.NFD)
	                   .replaceAll("[^\\p{ASCII}]", "")
	                   .toUpperCase();
	    return "ROLE_" + role;
	}
	
}
