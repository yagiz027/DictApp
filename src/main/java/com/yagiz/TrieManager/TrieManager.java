package main.java.com.yagiz.TrieManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.yagiz.Node.TrieNode;

public class TrieManager {
    private TrieNode root;

    public TrieManager(List<String> words) {
        this.root = new TrieNode();
        for (String word : words)
         root.insert(word);
    }   

    public TrieManager() {
        this.root = new TrieNode();
    }

    public void insertWords(File file) {
        List<String> words = readWordsFromFile(file.getAbsolutePath());
        for(String word: words){
            root.insert(word);
        }
    }

    public List<String> findMatchedWords(String prefix) {        
        List<String> words=new ArrayList<>();
        TrieNode currentNode=root;
        StringBuffer buffer=new StringBuffer();
        for(char c: prefix.toCharArray()){
            currentNode=currentNode.getChildren().get(c);
            //Son node null ise listeyi döndür. Değil ise StringBuilder kullanarak değer ataması yap.
            if(currentNode==null){
                return words; 
            }   
            buffer.append(c);
        }
        suggestHelper(currentNode,words,buffer);
        return words;
    }

    private void suggestHelper(TrieNode currentNode, List<String> words, StringBuffer buffer) {
        if(currentNode.isEndOfWord()){
            words.add(buffer.toString());
        }
        
        if(currentNode.getChildren()==null || currentNode.getChildren().isEmpty()){
            return;
        }

        for(TrieNode child: currentNode.getChildren().values()){
            buffer.append(child.getC()); //Node'un karakterini Stringbuffer'a ekle.
            suggestHelper(child, words, buffer);   // Özyineleme yaparak child node'lar üzerinde çalışır.
            buffer.setLength(buffer.length() - 1); //Eklenen karakteri çıkar.
        }
    }

    private List<String> readWordsFromFile(String filePath){
        List<String> words=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(filePath))){
            String line;
            while((line=br.readLine()) != null){
                String[] lineWords=line.split(" ");
                for(String word : lineWords){
                    word=word.trim().toUpperCase();
                    if(!word.isEmpty()){
                        words.add(word);
                    }
                }
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        return words;
    }
}

