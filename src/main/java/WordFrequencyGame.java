import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String sentence){
        String WHITE_SPACE = "\\s+";
        int wordCount = 1;
        if (sentence.split(WHITE_SPACE).length == wordCount) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] arr = sentence.split(WHITE_SPACE);
                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String s : arr) {
                    WordInfo wordInfo = new WordInfo(s, wordCount);
                    wordInfoList.add(wordInfo);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(wordInfoList);
                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }

                wordInfoList = list;
                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                return generateWordFrequencyReport(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private String generateWordFrequencyReport(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo w : wordInfoList) {
            String s = w.getValue() + " " +w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
