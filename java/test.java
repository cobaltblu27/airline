import java.util.*;

class test{
    static Queue<num> q;
    public static void main(String[] args){
        q = new PriorityQueue<>(Comparator.comparing(num::getneg));
        System.out.println("getneg:");
        q.add(new num(1));
        q.add(new num(2));
        q.add(new num(3));
        System.out.println(q.poll().getint());
        System.out.println(q.poll().getint());
        System.out.println(q.poll().getint());
        System.out.println("getint:");
        q = new PriorityQueue<>(Comparator.comparing(num::getint));
        q.add(new num(1));
        q.add(new num(2));
        q.add(new num(3));
        System.out.println(q.poll().getint());
        System.out.println(q.poll().getint());
        System.out.println(q.poll().getint());
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

