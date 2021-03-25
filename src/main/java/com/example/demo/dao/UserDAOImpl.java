package com.example.demo.dao;

import com.example.demo.models.User;
import org.hibernate.Session;
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
    UserDaoJPA userDaoJPA;

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
//        return entityManager.find(User.class, id);
        return entityManager.createQuery("select u from User u where u.id=:id", User.class)
            .setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public void save(User user) {
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

        if (!updatedUser.getRoles().isEmpty()) {
            userFromDB.setRoles(updatedUser.getRoles());
        }

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
