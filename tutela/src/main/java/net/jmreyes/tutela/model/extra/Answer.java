package net.jmreyes.tutela.model.extra;

/**
 * Created by juanma on 17/11/14.
 */
public class Answer {
    private String ansText;
    private int ansIndex;

    public Answer(String ansText, int ansIndex) {
        super();
        this.ansText = ansText;
        this.ansIndex = ansIndex;
    }

    public Answer() {
    }

    public String getAnsText() {
        return ansText;
    }
    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }
    public int getAnsIndex() {
        return ansIndex;
    }
    public void setAnsIndex(int ansIndex) {
        this.ansIndex = ansIndex;
    }
}
