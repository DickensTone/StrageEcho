package com.domain;

import org.springframework.data.repository.CrudRepository;

public interface ServiceLog extends CrudRepository<Transport, String> {
    
}
