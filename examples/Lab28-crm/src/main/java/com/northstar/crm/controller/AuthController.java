package com.northstar.crm.controller;

import com.northstar.crm.security.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final JwtService jwtService;

  public AuthController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> body) {
    UserDetails user = userDetailsService.loadUserByUsername(body.get("username"));
    if (!passwordEncoder.matches(body.get("password"), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
    }
    String token = jwtService.issueToken(user);
    return Map.of("accessToken", token, "tokenType", "Bearer");
  }
}
