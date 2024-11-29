package com.grupo2.orientanet.security;

import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findOneByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario no encontrado con el Email: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRole().getName());

        return new UserPrincipal(usuario.getId(),
                                usuario.getEmail(),
                                usuario.getContrasena(),
                                Collections.singletonList(authority),
                                usuario);
    }
}
