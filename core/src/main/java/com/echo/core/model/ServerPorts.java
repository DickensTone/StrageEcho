package com.echo.core.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ServerPorts<E> {
    private List<E> ports;

    public ServerPorts(){
        ports = new LinkedList<>();
    }

    public ServerPorts(List<E> list){
        Objects.requireNonNull(list);
        ports = list;
    }

    public List<E> getPorts(){
        return ports;
    }

    @Override
    public String toString() {
        return "ServerPorts{" +
                "ports=" + ports +
                '}';
    }
}
