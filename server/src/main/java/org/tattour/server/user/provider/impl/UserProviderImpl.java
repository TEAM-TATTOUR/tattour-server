package org.tattour.server.user.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.user.domain.User;
import org.tattour.server.user.provider.UserProvider;
import org.tattour.server.user.repository.impl.UserRepositoryImpl;

@Service
@RequiredArgsConstructor
public class UserProviderImpl implements UserProvider {
    private final UserRepositoryImpl userRepository;
    @Override
    public Integer checkDuplicateByEmail(String email) {
        return userRepository.findByEmail(email).map(User::getId).orElse(null);
    }
}
