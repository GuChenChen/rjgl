package com.fykj.scaffold.security.business.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class BackendUserDetail extends User {

	private static final long serialVersionUID = 4374424667663347893L;

	/**
	 *
	 */
	private String id;

	private String nickName;

	private String logo;
	/**
	 * 角色关键字
	 */
	private String roleId;

	private String roleCode;

	/**
	 * 角色名称
	 */
	private String roleName;

	private String type;

	public BackendUserDetail(String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public BackendUserDetail(String username, String password, boolean enabled,
                             boolean accountNonExpired, boolean credentialsNonExpired,
                             boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

}
