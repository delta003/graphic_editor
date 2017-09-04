package commands;

/**
 * Created by Marko on 06-Jun-15.
 */
public interface AbstractCommand {
    void doCommand();
    void undoCommand();
}
