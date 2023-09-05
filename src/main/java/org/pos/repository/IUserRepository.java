package org.pos.repository;

import org.pos.model.User;

public interface IUserRepository {

    /**
     We are extracting a single user for the database
     @param  username of the user that we want to extract info of
     @param  password of the user that we want to extract info of
     @return User that we are extracting
     */
    User getUser(String username, String password);

    /**
     We are registering the user into the database
     @param  username of the user that we want to register into the database
     @param  password of the user that we want to register into the database
     @return True if the user was successfully registered and False if otherwise
     */
    boolean registerUser(String username, String password, String type);

    public void closeAll();

    /**
     We are deleting the user from the database
     @param  username of the user that we want to delete from the database
     @return True if the user was successfully deleted and False if otherwise
     */
    boolean deleteUser(String username);
}
