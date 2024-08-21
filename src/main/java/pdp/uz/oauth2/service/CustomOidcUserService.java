package pdp.uz.oauth2.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomOidcUserService extends OidcUserService {


    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        Set<String> authorities = oidcUser.getAuthorities().stream()
                .map(authority -> authority instanceof OidcUserAuthority ? authority.getAuthority() : authority.getAuthority())
                .collect(Collectors.toSet());

        return oidcUser;
    }
}
