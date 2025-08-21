package com.example.note.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.note.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
