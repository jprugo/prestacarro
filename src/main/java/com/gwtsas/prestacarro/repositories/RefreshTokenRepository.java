package com.gwtsas.prestacarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gwtsas.prestacarro.entities.RefreshToken;
import com.gwtsas.prestacarro.entities.User;

import java.util.Optional;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long id);
    Optional<RefreshToken> findByToken(String token);
	int deleteByUser(User user);
}