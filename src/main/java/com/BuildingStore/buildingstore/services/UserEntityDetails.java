package com.BuildingStore.buildingstore.services;

import com.BuildingStore.buildingstore.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserEntityDetails implements UserDetails {

    private final UserEntity userEntity;

    public UserEntityDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // В вашем случае можно добавить роли, если они есть в UserEntity.
        // Здесь создаётся простая роль ADMIN, но можно адаптировать под ваш бизнес.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword(); // Возвращаем пароль из UserEntity
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername(); // Возвращаем имя пользователя из UserEntity
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Можно изменить, если у вас есть поле для отслеживания срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Можно добавить логику, если аккаунт может быть заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Аналогично, можно добавить логику для истечения срока действия пароля
    }

    @Override
    public boolean isEnabled() {
        return true; // Можно добавить логику для проверки активного состояния пользователя
    }
}
