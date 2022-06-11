package br.dev.luisgustavosales.generatepassword.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.dev.luisgustavosales.generatepassword.enums.EnumPasswordComplexity;

@Entity
@Table(name = "tb_password_info")
public class PasswordInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "password_info_id")
	private Long id;
	
	// This values must be filled with a user id from User microsservice
	@NotBlank(message = "The userId must have a valid value")
	@Min(value = 1, message = "userId must be greater or equal than 1")
	private Long userId;
	
	@NotBlank(message = "Invalid name.")
	@Size(min=2, max=20, message="The name size must be between 2 and 20")
	private String name;
	
	@NotBlank(message = "Invalid login.")
	@Size(min=2, max=100, message="The login size must be between 2 and 100")
	private String login;
	
	@NotBlank(message = "Invalid domain.")
	@Size(min=2, max=255, message="The login size must be between 2 and 255")
	private String domain;
	
	@NotBlank(message = "password group must have a valid value")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_password_group",
			joinColumns = @JoinColumn(name = "password_info_id"),
			inverseJoinColumns = @JoinColumn(name = "password_group_id"))
	private PasswordGroup passwordGroup;
	
	@NotBlank(message = "Size must have an value")
	@Min(value = 6, message = "size must be greater or equal than 6")
	@Max(value = 24, message = "size must be less or equal than 24")
	private int size;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="password_complexity")
	private Set<EnumPasswordComplexity> passwordComplexity = new HashSet<>();
	

	public PasswordInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PasswordInfo(
			Long id, Long userId, String name, 
			String login, String domain, PasswordGroup passwordGroup,
			int size, Set<EnumPasswordComplexity> passwordComplexity) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.login = login;
		this.domain = domain;
		this.passwordGroup = passwordGroup;
		this.size = size;
		this.passwordComplexity = passwordComplexity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public PasswordGroup getPasswordGroup() {
		return passwordGroup;
	}
	public void setPasswordGroup(PasswordGroup passwordGroup) {
		this.passwordGroup = passwordGroup;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Set<EnumPasswordComplexity> getPasswordComplexity() {
		return passwordComplexity;
	}
	public void setPasswordComplexity(Set<EnumPasswordComplexity> passwordComplexity) {
		this.passwordComplexity = passwordComplexity;
	}
	
	@Override
	public String toString() {
		return "PasswordInfo [id=" + id + ", userId=" + userId + ", name=" + name + ", login=" + login + ", domain="
				+ domain + ", passwordGroup=" + passwordGroup + ", size=" + size + ", passwordComplexity="
				+ passwordComplexity + "]";
	}
	
	
}
