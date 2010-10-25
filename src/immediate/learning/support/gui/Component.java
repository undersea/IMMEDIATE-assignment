package immediate.learning.support.gui;

import java.io.Serializable;
import javax.swing.JComponent;

public interface Component extends Serializable {
    public JComponent configure();
    public String getName();
    public String getDescription();
}