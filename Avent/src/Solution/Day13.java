package Solution;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class PDU implements Comparable<PDU> 
{
    private final List<PDU> child = new ArrayList<>();
    private int val = -1;
    private boolean isNum = true;
    private final String rawValue;
    
    public PDU(String input) 
    {
        this.rawValue = input;
        if (!input.startsWith("[")) {
            val = Integer.parseInt(input);
        } else {
            input = input.substring(1, input.length() - 1);
            int level = 0;
            StringBuilder tmp = new StringBuilder();
            for (char c : input.toCharArray()) 
            {
                if (c == ',' && level == 0) 
                {
                    child.add(new PDU(tmp.toString()));
                    tmp = new StringBuilder();
                } 
                else 
                {
                    level += (c == '[') ? 1 : (c == ']') ? -1 : 0;
                    tmp.append(c);
                }
            }
            if (!tmp.toString().equals(""))
            {
                child.add(new PDU(tmp.toString()));
            }
            isNum = false;
        }
    }
   
    @Override
    public int compareTo(PDU other) 
    {
        if (isNum && other.isNum) 
        {
            return Integer.compare(other.val, val);
        }
        if (!isNum && !other.isNum) 
        {
            for (int i = 0; i < Math.min(child.size(), other.child.size()); i++) {
                int val = child.get(i).compareTo(other.child.get(i));
                if (val != 0) {
                    return val;
                }
            }
            return other.child.size() - child.size();
        }
        PDU pdu1 = isNum ? new PDU("[" + val + "]") : this;
        PDU pdu2 = other.isNum ? new PDU("[" + other.val + "]") : other;
        return pdu1.compareTo(pdu2);
    }
    
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PDU that = (PDU) o;
        return rawValue.equals(that.rawValue);
    }
    
    @Override
    public int hashCode() 
    {
        return Objects.hashCode(rawValue);
    }

    static void day13(String puzzleInputUri) throws IOException, InterruptedException 
    {
//    Object client;
//	Object request;
//	String[] packets = client.send(((Object) request).uri((URI.create(puzzleInputUri))).build(), HttpResponse.BodyHandlers.ofLines())
//        .body()
//        .filter(line -> line.startsWith("["))
//        .toArray(String[]::new);

//    int answerPartOne = 0;
//    List<PDU> broadcast = new ArrayList<>();
//    for (int i = 0, pairIdx = 1; i < packets.length - 1; i += 2, pairIdx++) {
//        PDU left = new PDU(packets[i]);
//        PDU right = new PDU(packets[i+1]);
//        broadcast.addAll(List.of(left, right));
//        answerPartOne += left.compareTo(right) > 0 ? pairIdx : 0;
//    }
//    System.out.println(answerPartOne);

//    PDU separator1 = new PDU("[[2]]");
//    PDU separator2 = new PDU("[[6]]");
//    broadcast.addAll(List.of(separator1, separator2));
//    Collections.sort(broadcast);
//    Collections.reverse(broadcast);
//    int answerPartTwo = (broadcast.indexOf(separator1) + 1) * (broadcast.indexOf(separator2) + 1);
//    System.out.println(answerPartTwo);
}
}