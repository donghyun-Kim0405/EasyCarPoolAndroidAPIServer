package com.example.AndroidAPIServer.repository;

import com.example.AndroidAPIServer.domain.entity.ChatRoomEntity;
import com.example.AndroidAPIServer.domain.entity.PostPassenger;
import com.example.AndroidAPIServer.domain.entity.User;
import com.example.AndroidAPIServer.dto.post.PostPassengerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostPassengerRepository extends JpaRepository<PostPassenger, Long>{

    @Query("SELECT p FROM PostPassenger p WHERE p.email = :email")
    public List<PostPassenger> findPassengerPostByEmail(@Param("email") String email);

    @Query("SELECT p FROM PostPassenger p WHERE p.departure LIKE %:district% OR p.destination LIKE %:district%")
    public List<PostPassenger> findPassengerPostByDistrict(@Param("district") String district);

    //시간순으로 findAll() 수행하도록 변경할것
}
