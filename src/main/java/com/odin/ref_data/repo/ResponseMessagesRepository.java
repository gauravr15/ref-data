package com.odin.ref_data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odin.ref_data.entity.ResponseMessages;


@Repository
public interface ResponseMessagesRepository extends JpaRepository<ResponseMessages, Integer> {

}
