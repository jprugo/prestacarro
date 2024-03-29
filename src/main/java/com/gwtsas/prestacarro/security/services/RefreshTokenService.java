package com.gwtsas.prestacarro.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.RefreshToken;
import com.gwtsas.prestacarro.exceptions.TokenRefreshException;
import com.gwtsas.prestacarro.repositories.RefreshTokenRepository;
import com.gwtsas.prestacarro.repositories.UserRepository;

@Service
public class RefreshTokenService {
	
  @Value("${com.gwtsas.prestacarro.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  private RefreshTokenRepository refreshTokenRepository;

  private UserRepository userRepository;

  @Autowired
  public RefreshTokenService(
          RefreshTokenRepository refreshTokenRepository,
          UserRepository userRepository
  ){
    this.refreshTokenRepository = refreshTokenRepository;
    this.userRepository = userRepository;
  }
  
  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setUser(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }
    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }

}
