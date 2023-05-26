package com.volkonovskij.security.provider;

import com.volkonovskij.domain.SystemRoles;
import com.volkonovskij.domain.hibernate.AuthenticationInfo;
import com.volkonovskij.domain.hibernate.HibernateRole;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.repository.springdata.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsProvider implements UserDetailsService {


    private final UserDataRepository userDataRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Optional<HibernateUser> searchResult = userDataRepository.findByAuthenticationInfoEmail(email);

            if (searchResult.isPresent()) {
                HibernateUser user = searchResult.get();
                AuthenticationInfo authenticationInfo = user.getAuthenticationInfo();
                Set<HibernateRole> roles = user.getRoles();

                return new org.springframework.security.core.userdetails.User(
                        authenticationInfo.getEmail(),
                        authenticationInfo.getUserPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                roles
                                        .stream()
                                        .map(HibernateRole::getRoleName)
                                        .map(SystemRoles::name)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this email not found");
        }
    }
}
