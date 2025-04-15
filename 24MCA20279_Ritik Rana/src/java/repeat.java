import java.util.LinkedHashMap;
import java.util.Map;

public class repeat {
    public static Character firstNonRepeatingChar(String str) {
        if (str == null || str.isEmpty()) return null;

        Map<Character, Integer> charCount = new LinkedHashMap<>();
        
        for (char ch : str.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        
        return null;
    }

    public static void main(String[] args) {
        String str = "swiss";
        Character result = firstNonRepeatingChar(str);
        if (result != null) {
            System.out.println("First non-repeating character is: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
    }
}
