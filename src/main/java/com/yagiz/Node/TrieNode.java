package main.java.com.yagiz.Node;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private char c;
    private boolean isEndOfWord;

    public TrieNode(char c) {
        this.c = c;
        children = new HashMap<>();
    }

    public TrieNode() {
        children = new HashMap<>();
    }

    public void insert(String word) {
        if (word==null || word.isEmpty())
        {
            return;
        }

        char firstChar = word.charAt(0);
        TrieNode child = children.get(firstChar);

        if (child == null) {
            child = new TrieNode(firstChar);
            children.put(firstChar, child);
        }

        if (word.length() > 1)
            child.insert(word.substring(1));
        else
            child.isEndOfWord = true;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}
