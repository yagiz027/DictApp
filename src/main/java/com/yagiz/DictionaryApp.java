package main.java.com.yagiz;

import java.io.File;
import java.util.Scanner;

import main.java.com.yagiz.TrieManager.TrieManager;

public class DictionaryApp{    
  public static void main(String[] args) {    
    Scanner scanner=new Scanner(System.in);
    System.out.println("Lütfen Tree'e eklemek istediğiniz sözlüğün dosya yolunu giriniz:");
    String path=scanner.nextLine();    

    System.out.println("Dosya yolu alındı Tree'ye kaydediliyor...");
    
    TrieManager trieManager=new TrieManager();
    trieManager.insertWords(new File(path));

    System.out.println("Lütfen aranacak kelimeyi giriniz: ");
    String searchInput=scanner.nextLine();
    while(true){
      System.out.println("Önerilen kelimeler:");
      System.out.println(trieManager.findMatchedWords(searchInput));
      scanner.close();
      break;
    }
  }
}
