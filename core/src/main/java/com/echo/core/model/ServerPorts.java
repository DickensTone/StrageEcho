package com.echo.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServerPorts {
    private List<Integer> ports;

    public ServerPorts(){
        ports = new ArrayList<>();
    }

    public ServerPorts(List<Integer> list){
        Objects.requireNonNull(list);
        ports = list;
    }
}
