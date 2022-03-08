package uz.elmurodov.meal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.elmurodov.meal.domain.AuthUser;
import uz.elmurodov.meal.dto.auth.AuthUserDto;
import uz.elmurodov.meal.dto.auth.SessionDto;
import uz.elmurodov.meal.dto.response.DataDto;
import uz.elmurodov.meal.repository.AuthUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public record AuthUserService(AuthUserRepository repositoy) implements AbstractService, UserDetailsService {

    public ResponseEntity<DataDto<SessionDto>> getToken(AuthUserDto dto) {
        return null;
    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repositoy.findByUsernameAndDeletedFalse(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthority())
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }
}
