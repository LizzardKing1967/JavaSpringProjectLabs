package com.BuildingStore.buildingstore.services;

import com.BuildingStore.buildingstore.materialRepository.UserRepository;
import com.BuildingStore.buildingstore.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // Конструктор для инжекции UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из базы данных
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Создаем и возвращаем UserDetails с использованием конструктора
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
    }
}
