package repository;

import jakarta.persistence.*;
import model.User;

public class UserRepository implements IUserRepository{
    EntityManagerFactory factory;
    EntityManager manager;


    public UserRepository() {
        factory = Persistence.createEntityManagerFactory("User_persi_unit");
        manager = factory.createEntityManager();

    }

    public static void main(String[] args) {

        UserRepository user = new UserRepository();
        user.registerUser("Nombus0", "12234", "Admin");
        user.deleteUser("Nombus0");
    }
    /**
     We are extracting a single user for the database
     @param  username of the user that we want to extract info of
     @param  password of the user that we want to extract info of
     @return User that we are extracting
     */
    @Override
    public User getUser(String username, String password) {
        manager.getTransaction().begin();
        Query query = manager.createQuery(
                "SELECT s FROM User s WHERE s.username = '"+username+"' AND s.password = '" + password + "'");
        User user = (User) query.getSingleResult();
        return user;
    }


    /**
     We are registering the user into the database
     @param  username of the user that we want to register into the database
     @param  password of the user that we want to register into the database
     @return True if the user was successfully registered and False if otherwise
     */
    @Override
    public boolean registerUser(String username, String password, String type) {
        User userInf;
        manager.getTransaction().begin();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(type);
        manager.persist(user);
        manager.getTransaction().commit();

        Query query = manager.createQuery(
                "SELECT s FROM User s WHERE s.username = '"+username+"' AND s.password = '" + password + "'");


        try {
            userInf = (User) query.getSingleResult();
        }catch(NoResultException e){
            userInf = null;
        }

        if (userInf==null){
            return false;
        }
        return true;
    }


    /**
     We are deleting the user from the database
     @param  username of the user that we want to delete from the database
     @return True if the user was successfully deleted and False if otherwise
     */
    @Override
    public boolean deleteUser(String username) {
        User userInf;
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT s FROM User s WHERE s.username = '"+username+"'");
        User user = (User) query.getSingleResult();
        manager.remove(user);
        manager.getTransaction().commit();

        Query query1 = manager.createQuery("SELECT s FROM User s WHERE s.username = '"+username+"'");

        try {
            userInf = (User) query1.getSingleResult();
        }catch(NoResultException e){
            userInf = null;
        }

        if (userInf==null){
            return true;
        }
        return false;
    }

    /**
     * Closing the Entity manager and the EntityManagerFactory
     */
    @Override
    public void closeAll(){
        manager.close();
        factory.close();
    }
}
