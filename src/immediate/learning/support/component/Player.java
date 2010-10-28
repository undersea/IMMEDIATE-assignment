package immediate.learning.support.component;

import javax.swing.JComponent;

public class Player implements Component {
    private static final long serialVersionUID = 1L;

    public String getAttr(String key) {
        return null;
    }

    public JComponent configure() {
        return null;
    }

    public String getName() {
        return "Player";
    }

    public String getDescription() {
        return "A audio player";
    }

    public JComponent getComponent() {
        return null;
    }


    public String toString() {
        return getName();
    }
}