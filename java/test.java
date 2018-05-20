import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class test{
    static HashSet<num> q;
    static List<Integer> intSet;
    public static void main(String[] args){
        q = IntStream.range(-10,-1).mapToObj(x -> new num(x)).filter(x -> x.getint() % 2 != 0).collect(Collectors.toCollection(HashSet::new));
        intSet = q.stream().map(num::getneg).collect(Collectors.toList());
        intSet.stream().forEach(System.out::println);
    }
}

class num{
    int k;
    num(int a){
        k = a;
    }
    public int getneg(){
        return -k;
    }
    public int getint(){
        return k;
    }
}

