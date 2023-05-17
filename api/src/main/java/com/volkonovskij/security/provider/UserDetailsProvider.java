package com.volkonovskij.security.provider;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.SystemRoles;
import com.volkonovskij.domain.User;
import com.volkonovskij.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsProvider implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> searchResult = userService.findByLogin(username);

            if (searchResult.isPresent()) {
                User user = searchResult.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                userService.getUserAuthorities(user.getId())
                                        .stream()
                                        .map(Role::getSystemRole)
                                        .map(SystemRoles::name)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}