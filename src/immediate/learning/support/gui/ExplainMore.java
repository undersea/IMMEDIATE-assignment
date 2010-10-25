package immediate.learning.support.gui;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.Dimension;

public class ExplainMore implements Component {
    private static final long serialVersionUID = 1L;
    private String explanation;

    public ExplainMore() {
        explanation = new String();
    }

    public void configure() {
        JFrame frame = new Configuration(explanation);
        System.out.println(explanation);
    }

    public String getName() {
        return "Explain More";
    }


    public String getDescription() {
        return "A means of giving more explaination about a concept";
    }

    class Configuration extends JFrame {
        String explanation;
        private static final long serialVersionUID = 1L;
        public Configuration(String explanation) {
            this.explanation = explanation;
            setTitle("Explain More");
            this.explanation = "Explain this";
            JTextPane area = new JTextPane();
            getContentPane().add(area);
            setPreferredSize(new Dimension(200, 200));
            pack();
            setVisible(true);
        }
    }
}