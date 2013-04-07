public class Subset {
   public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    String s[];
    RandomizedQueue<String> rq= new RandomizedQueue<String>();
    s = StdIn.readStrings();
    for(int i=0;i<s.length;i++) {
        rq.enqueue(s[i]);
    }
    while(n > 0) {
           System.out.println(""+rq.dequeue());
           n--;
    }

   }
}

