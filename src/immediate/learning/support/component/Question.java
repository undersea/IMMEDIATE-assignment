package immediate.learning.support.component;


import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;


public class Question implements Component {
    private static final long serialVersionUID = 1L;
    Configure config = new Configure();
    public Question() {
        
    }

    public String getAttr(String key) {
        if(key.toLowerCase().equals("question")) {
            return config.questionPane.getText();
        } else if(key.toLowerCase().equals("answer")) {
            return config.answerPane.getText();
        }

        return null;
    }

    public JComponent configure() {
        return config;
    }

    public String getName() {
        return "Question";
    }


    public String getDescription() {
        return "A quesion and answer component";
    }

    public JComponent getComponent() {
        return null;
    }

    class Configure extends Box {
        private static final long serialVersionUID = 1L;
        public JTextPane questionPane;
        public JTextPane answerPane;
        public Configure() {
            super(BoxLayout.Y_AXIS);
            add(new JLabel("Question"));
            questionPane = new JTextPane();
            add(questionPane);
            add(new JLabel("Answer"));
            answerPane = new JTextPane();
            add(new JTextPane());
        }
    }

    public String toString() {
        return getName();
    }
}
