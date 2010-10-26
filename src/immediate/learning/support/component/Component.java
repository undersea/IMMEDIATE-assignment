package immediate.learning.support.component;

import java.io.Serializable;
import javax.swing.JComponent;

public interface Component extends Serializable {
    public String getAttr(String key);
    public JComponent configure();
    public String getName();
    public String getDescription();
    public JComponent getComponent();
}