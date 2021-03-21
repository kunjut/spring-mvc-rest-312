package com.example.demo.dao;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> index() {
        return entityManager.createQuery(
                "select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User show(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
//        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User updatedUser) {
        User userFromDB = show(updatedUser.getId());
        userFromDB.setUsername(updatedUser.getUsername());
        userFromDB.setEmail(updatedUser.getEmail());

        if (!updatedUser.getPassword().isEmpty()) {
            userFromDB.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        }
//        userFromDB.setRoles(updatedUser.getRoles());

        if (!updatedUser.getRoles().isEmpty()) {
            userFromDB.setRoles(updatedUser.getRoles());
        }
//        updatedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));

//        entityManager.merge(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(show(id));
    }

    @Override
    public User getUserByName(String username) {
        return entityManager.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
