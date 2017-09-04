package event;

import java.util.EventListener;

/**
 * Created by Marko on 24-May-15.
 */
public interface UpdateListener extends EventListener {
    void updatePerformed(UpdateEvent e);
}
