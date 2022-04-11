package com.echo.client.console.strategy;


/**
 * an object that handle the command.
 * 1.Where are commands from?
 * <p>
 *     Usually, the commands come from console.
 *     the command directory should be wrote down to form a document.
 * </p>
 * 2.the implement of the interface will register to the strategy mode occasionally.
 *
 */
public interface CommandHandler {

    void handle(String command);
}
