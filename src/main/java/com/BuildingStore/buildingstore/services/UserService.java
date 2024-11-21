package com.BuildingStore.buildingstore.services;

import com.BuildingStore.buildingstore.materialRepository.UserRepository;
import com.BuildingStore.buildingstore.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из базы данных
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);

        // Если пользователь не найден, выбрасываем исключение
        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // Получаем сущность пользователя из Optional
        UserEntity userEntity = userEntityOptional.get();

        // Возвращаем новый объект UserEntityDetails
        return new UserEntityDetails(userEntity);
    }
}
