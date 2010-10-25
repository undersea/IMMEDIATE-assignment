package immediate.learning.support.gui;

import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Dimension;

public class ExplainMore implements Component {
    private static final long serialVersionUID = 1L;
    private String explanation;

    public ExplainMore() {
        explanation = new String();
    }

    public JComponent configure() {
        return new Configuration(explanation);
    }

    public String getName() {
        return "Explain More";
    }


    public String getDescription() {
        return "A means of giving more explaination about a concept";
    }

    class Configuration extends Box {
        String explanation;
        private static final long serialVersionUID = 1L;
        public Configuration(String explanation) {
            super(BoxLayout.Y_AXIS);
            this.explanation = explanation;
            JLabel titleLabel = new JLabel("Explain More");
            add(titleLabel);
            this.explanation = "Explain this";
            JTextPane area = new JTextPane();
            add(area);
            area.setPreferredSize(new Dimension(200, 200));

            //setVisible(true);
        }
    }
}