package cv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class cv {
	
	public static void main(String args[]) throws FileNotFoundException {
			
		// procurar dejar un archivo con el cv en formato txt en una carpeta del c:	
		Scanner s = new Scanner(new File("C:\\Users\\Win7_32b\\Documents\\cv.txt"));
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<HashMap<String, Integer>> mArrayList = new ArrayList<>();
		Integer temp;
		
		// crear lista con todos las palabras en min˙scula y suprimiendo caracteres especiales
		while (s.hasNext()){
			
	        	String c= s.next();
	        	Pattern pt = Pattern.compile("[^a-zA-Z0-9Ò—·ÈÌÛ˙¡…Õ”⁄/-@.]");
	        	Matcher match= pt.matcher(c);
	        	while(match.find()) {
	        		String str= match.group();
	        		c=c.replaceAll("\\"+str, "");
	        	}
			
	        	list.add(c.toLowerCase());
		}
		s.close();
			
		// crear hashmap y aÒadirle cada palabra con la cantidad de veces que aparece en el archivo
		for (int y = 0; y < list.size(); y++) {
			map.put(list.get(y), Collections.frequency(list, list.get(y)));
		}

		mArrayList.add(map);
		
		// crear lista pasandole el hashmap y borrarle palabras duplicadas para que solo aparezcan una vez en el listado		
		for(int i=0;i<mArrayList.size();i++) {
           		temp=mArrayList.get(i).get("1");
          		for(int k=i+1;k<mArrayList.size();k++) {
         			if(temp.equals(mArrayList.get(k).get("1"))) {
         	           		mArrayList.remove(k); 
       	       	      		} 
            	  	}

        	}
		
		// ordenar la lista para que aparezca desde la palabra menos usada hasta la m·s usada
		TreeMap<String, Integer> map2 = new TreeMap<>();
		map2.putAll(map);
		
		Map<String, Integer> sortedMap =
				map2.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
		
		// imprimir el listado de palabras del cv
		System.out.println(Arrays.toString(sortedMap.entrySet().toArray()));
		
	}
}