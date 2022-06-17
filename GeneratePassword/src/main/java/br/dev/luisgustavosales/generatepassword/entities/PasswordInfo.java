package br.dev.luisgustavosales.generatepassword.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_password_info")
public class PasswordInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "password_info_id")
	private Long id;

	@NotBlank(message = "The userId must have a valid value")
	@Min(value = 1, message = "userId must be greater or equal than 1")
	private Long userId;

	@NotBlank(message = "Invalid name.")
	@Size(min = 2, max = 20, message = "The name size must be between 2 and 20")
	private String name;

	@NotBlank(message = "password login must have a valid value")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "login_id")
	private PasswordLogin login;

	@NotBlank(message = "password domain must have a valid value")
	private String domain;

	@NotBlank(message = "password group must have a valid value")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "group_id")
	private PasswordGroup passwordGroup;

	@NotBlank(message = "Size must have an value")
	@Min(value = 6, message = "size must be greater or equal than 6")
	@Max(value = 24, message = "size must be less or equal than 24")
	private int size;

	// @ElementCollection(fetch = FetchType.EAGER)
	// @CollectionTable(name="password_complexity")
	// private Set<EnumPasswordComplexity> passwordComplexity = new HashSet<>();

	public PasswordInfo() {
		super();
		// TODO Auto-generated constructor stub
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

	public PasswordLogin getLogin() {
		return login;
	}

	public void setLogin(PasswordLogin login) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordInfo other = (PasswordInfo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PasswordInfo [id=" + id + ", userId=" + userId + ", name=" + name + ", login=" + login + ", domain="
				+ domain + ", passwordGroup=" + passwordGroup + ", size=" + size + "]";
	}

	

}
