package org.pos.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.pos.model.User;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;


public class UserRespositoryTest {
    private IUserRepository userRepository;
    private EntityManagerFactory emf;
    private EntityManager entityManager;


    public User saveUserForTesting(String name, String password, String Type){
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setType(Type);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @BeforeEach
    public void setUp() {
        this.userRepository = new UserRepository();
        this.emf = Persistence.createEntityManagerFactory("pos_pu");
        this.entityManager = emf.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        this.entityManager.close();
        this.emf.close();
    }

    @Test
    public void saveUser(){
        User user = new User();
        boolean registering= userRepository.registerUser(
                "test_Username1", "test_Username##1", "Admin");
        System.out.println("+++++++"+user.getId());
        assertTrue(registering);

    }

    @Test
    public void deleteUser(){
        userRepository.registerUser(
                "test_Username", "test_Username##", "Admin");
        System.out.println("=============2");
        boolean deleted = userRepository.deleteUser("test_Username");
        System.out.println("============="+ deleted);
        assertTrue(deleted);

    }

    @Test
    public void getUser(){
        saveUserForTesting("Lol", "Lol123", "Admin");
        userRepository.getUser("Lol", "Lol123");
    }


}
