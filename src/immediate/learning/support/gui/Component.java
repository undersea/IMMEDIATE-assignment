package immediate.learning.support.gui;

import java.io.Serializable;


public interface Component extends Serializable {
    public void configure();
    public String getName();
    public String getDescription();
}