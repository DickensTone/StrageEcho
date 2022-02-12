package com.domain;

import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Transport, String> {
    
}
