import java.util.Map;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
/**
 * Metod imzalarını, sınıf ismini ve paket bilgisini değiştirmeden 
 * istediğiniz değişiklikleri yapabilirsiniz.
 */

public final class Odev {
    public List<String> sirali = new ArrayList<>();
    public SimpleGraph<String> aa =new SimpleGraph<>();
    public Odev3(String girdi){
        final HashMap<String, Integer> hmap= new HashMap<String, Integer>();
        String[] satir = girdi.split("\\\n");
        for (String satir1 : satir) {
            String[] harf = satir1.split(" ");
            aa.addVertex(harf[0]);
        }
        int x=0;
        for(int i=0;i<satir.length;i++){
            String[] harf=satir[i].split(" ");
            for(int j=1;j<harf.length;j++){
                 aa.addEdge(harf[0],harf[j]);
                 x+=1;
            }
            hmap.put(harf[0], x);
            x=0;
        }
        final Comparator<Map.Entry<String, Integer>> komusu =
            Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder());
        final Comparator<Map.Entry<String, Integer>> alfabe =
            Comparator.comparing(Map.Entry::getKey);
        final List<String> hasToBeReturned = hmap.entrySet().stream()
                .sorted(komusu.thenComparing(alfabe))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for(String a:hasToBeReturned){
            sirali.add(a);
        }
    }

   

    public Map<String, String> boya(String[] renkler) {
        Map<String, String> map= new HashMap<>();
        for(String renk:renkler){
            map.put(sirali.get(0),renk);
            sirali.remove(0);
            Iterator<String> t = sirali.iterator();
            while(t.hasNext()){
                String gg = t.next();
                boolean deger=true;
                Vertex<String> mevVer=this.aa.verticesMap.get(gg);
                for (Edge<String> ab:  mevVer.edges){
                    if(renk.equals(map.get(ab.to.value))){
                        deger=false;
                    }
                }
                if(deger==true){
                     map.put(gg,renk);
                     t.remove();
                }
            }
            if(sirali.isEmpty()) {
                break;
            }  
        }
        return map;
    }

}

 
