package com.echo.core.components;

import java.util.concurrent.LinkedBlockingDeque;

public class SingletonUtil {
    public final static LinkedBlockingDeque<StringBuffer> linkedBlockingDeque = new LinkedBlockingDeque<>();
}
