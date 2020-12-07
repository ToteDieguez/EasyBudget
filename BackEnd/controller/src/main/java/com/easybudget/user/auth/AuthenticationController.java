package com.easybudget.user.auth;

import com.easybudget.user.auth.dto.TokenRequest;
import com.easybudget.user.auth.dto.TokenResponse;
import com.easybudget.user.auth.exception.AuthenticationException;
import com.easybudget.user.auth.service.AuthPersonServiceDetail;
import com.easybudget.user.auth.service.utils.AuthTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private AuthTokenUtil authTokenUtil;
    private AuthPersonServiceDetail authPersonServiceDetail;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, AuthTokenUtil authTokenUtil, AuthPersonServiceDetail authPersonServiceDetail) {
        this.authenticationManager = authenticationManager;
        this.authTokenUtil = authTokenUtil;
        this.authPersonServiceDetail = authPersonServiceDetail;
    }

    @PostMapping(value = "${jwt.get.token.uri}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest)
            throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = authPersonServiceDetail.loadUserByUsername(authenticationRequest.getUsername());

        final String token = authTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping(value = "${jwt.refresh.token.uri}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        if (authTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = authTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new TokenResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}