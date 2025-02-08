package com.techbridge.apis.repository;

import com.techbridge.apis.model.Sermon;
import com.techbridge.apis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u WHERE u.email = :name or u.username = :name")
    Optional<User> findByUsernameorEmail(@Param("name") String name);

    @Query(value = "SELECT u FROM User u WHERE lower(u.email) = :username AND u.password = :password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Transactional
    @Modifying
    @Query("UPDATE User u set  u.token = null, u.password = :password WHERE lower(u.username) = :username OR lower(u.email) = :username")
    void updateForgotPassword(@Param("password") String password, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u set  u.token = :token WHERE lower(u.username) = :username OR lower(u.email) = :username")
    void updateRegistrationToken(@Param("token") String token, @Param("username") String username);


}


