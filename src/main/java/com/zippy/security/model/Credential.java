package com.zippy.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Collection;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "credential")
public class Credential implements UserDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String username;

  @Column
  private String password;

  @Column(name="personal_information_id")
  private long personalInformationId;

  @Column(name="role_id")
  private long roleId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="role_id", referencedColumnName = "id", updatable = false, insertable = false)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.getName())));
  }

  @Override
  public boolean isAccountNonExpired() {
      return true     ;
  }

  @Override
  public boolean isAccountNonLocked() {
      return true     ;
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true     ;
  }

  @Override
  public boolean isEnabled() {
      return true     ;
  }
}
