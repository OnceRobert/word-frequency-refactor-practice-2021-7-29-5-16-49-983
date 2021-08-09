public class WordInfo {
    private String value;
    private int count;

    public WordInfo(String word, int i){
        this.value = word;
        this.count = i;
    }


    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }


}
