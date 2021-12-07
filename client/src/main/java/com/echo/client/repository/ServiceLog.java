package com.echo.client.repository;

import com.echo.client.domain.Transport;
import org.springframework.data.repository.CrudRepository;

public interface ServiceLog extends CrudRepository<Transport, String> {
}
