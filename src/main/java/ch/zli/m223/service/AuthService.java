package ch.zli.m223.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;

import ch.zli.m223.model.User;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class AuthService {
    @Inject
    EntityManager entityManager;

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    public String genToken(String username, String password){
        List <User> users = userService.findAll();
        for (User user: users){
            if(user.getUsername().equals(users) && user.getPassword().equals(users)) {
                String token = Jwt.issuer("https://example.com/issuer") 
                 .upn(user.getUsername()) 
                 .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
                 .claim(Claims.birthdate.name(), "2001-07-13") 
               .sign();
            return token;
        }
    }
    
    }




}
