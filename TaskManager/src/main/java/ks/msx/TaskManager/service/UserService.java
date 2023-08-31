package ks.msx.TaskManager.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.TaskManager.dto.UserDTO;
import ks.msx.TaskManager.entity.Roles;
import ks.msx.TaskManager.entity.User;
import ks.msx.TaskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException"));
    }

    public void saveUser(UserDTO dto, HttpServletRequest request) throws ServletException, IOException {
        userRepository.save(
                User.builder()
                        .username(dto.getUsername())
                        .password(new BCryptPasswordEncoder(12).encode(dto.getPassword()))
                        .role(Roles.USER)
                        .account_non_locked(true)
                        .build()
        );
        request.login(dto.getUsername(), dto.getPassword());
    }

    public User getCurrentUser(String username){
        return userRepository.findUserByUsername(username).orElseThrow();
    }
}
