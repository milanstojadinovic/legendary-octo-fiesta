package com.linkedinlearning.demo.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.linkedinlearning.demo.data.entity.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

}
