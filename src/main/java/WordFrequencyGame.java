import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    String WHITE_SPACE = "\\s+";
    int wordCount = 1;
    public String getResult(String sentence){
        if (sentence.split(WHITE_SPACE).length == wordCount) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList;
                List<WordInfo> list = calculateWordFrequency(sentence);
                wordInfoList = list;
                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                return generateWordFrequencyReport(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence){
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord ->{
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord, count);
            wordInfos.add(wordInfo);
        });
        return wordInfos;
    }

    private String generateWordFrequencyReport(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo w : wordInfoList) {
            String s = w.getValue() + " " +w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }
}
