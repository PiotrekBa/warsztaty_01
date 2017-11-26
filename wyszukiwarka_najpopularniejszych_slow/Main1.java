package wyszukiwarka_najpopularniejszych_slow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main1 {

	public static void main(String[] args) {

		Connection connect = Jsoup.connect("http://www.onet.pl/");
		Path path = Paths.get("popular_word.txt");
		try {
			ArrayList<String> out = new ArrayList<>();

			Document document = connect.get();
			Elements links = document.select("span.title");

			for (Element elem : links) {
				String text = elem.text().toString();
				StringTokenizer token = new StringTokenizer(text, "\" \\.,:?!");

				while (token.hasMoreTokens()) {
					String a = token.nextToken();
					if (a.charAt(0) >= 'A') {
						out.add(a);
					}
				}
			}
			Collections.sort(out);
			try {
				Files.write(path, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			HashMap<String, Integer> in = new HashMap<String, Integer>();
			ArrayList<String> str = new ArrayList<>();
			for (String word : Files.readAllLines(path)) {
				if (word.length() > 3) {
					str.add(word);
				}
			
			}
			int count = 1;
			for(int i = 0; i < str.size()-1; i++) {
				String word = str.get(i).toString();
				if(word.equalsIgnoreCase(str.get(i+1))) {
					count++;
				} else {
					in.put(str.get(i).toString(), count);	
					count = 1;
				}
			}
			ArrayList<String> topWord = new ArrayList<>();
			int size = in.size();
			if (size > 10) {
				size = 10;
			}
			for (int i = 0; i < size; i++) {
				int max = Collections.max(in.values());
				for (Entry<String, Integer> entry : in.entrySet()) {
					if (entry.getValue()==max) {
						topWord.add(entry.getKey().toString() + " - " +max+" razy");
						String a = entry.getKey();
						in.remove(a);
						break;
					}
				}
			}
			Path path1 = Paths.get("most_popular_words.txt");
			Files.write(path1, topWord);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
