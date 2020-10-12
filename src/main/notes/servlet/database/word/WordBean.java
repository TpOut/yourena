package main.notes.servlet.database.word;

public class WordBean {
    private String src;
    private String dst;
    private String sentence;

    public WordBean(String src, String dst, String sentence) {
        this.src = src;
        this.dst = dst;
        this.sentence = sentence;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
