package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import ch.zli.m223.model.User;

/*
 * @author Andelo Batinic
 * @version 26.09 2022
 * This service handles the endpoint-methods for the User
 */

@ApplicationScoped
public class UserService {
    @Inject
    EntityManager entityManager;

    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    public User findById(Long id){
        var user = entityManager.find(User.class, id);
        return user;
    }

    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(Long id, User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        var user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
