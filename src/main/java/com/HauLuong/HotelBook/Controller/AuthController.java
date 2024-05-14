package com.HauLuong.HotelBook.Controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HauLuong.HotelBook.Exception.UserAlreadyExistsException;
import com.HauLuong.HotelBook.Model.User;
import com.HauLuong.HotelBook.Request.LoginRequest;
import com.HauLuong.HotelBook.Response.JwtResponse;
import com.HauLuong.HotelBook.Security.Jwt.JwtUtils;
import com.HauLuong.HotelBook.Security.User.HotelUserDetails;
import com.HauLuong.HotelBook.Service.IUserService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final IUserService userService;
    private final  AuthenticationManager authenticationManager;
    private final  JwtUtils jwtUtils;

    public AuthController(IUserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		super();
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        HotelUserDetails userDetails = (HotelUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),	
                userDetails.getEmail(),
                jwt,
                roles));
    }
}
