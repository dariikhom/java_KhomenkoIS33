public class lab2 {
    public static void main(String[] args) {
        int num = 23,
                c3 = num % 3,
                c17 = num % 17;
        System.out.println("c3 = " + c3);
        System.out.println("c17 = " + c17);

        StringBuffer text = new StringBuffer(
                "You're good at English when you know the difference between a man eating chicken and a man-eating chicken. Facing his greatest fear, he ate his first marshmallow.");
        StringBuffer[] words = splitToWords(text);

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (countVowels(words[i]) > countVowels(words[j])) {
                    StringBuffer temp = words[i];
                    //temp.append(countVowels(words[i]));
                    words[i] = words[j];
                    //words[i].append(countVowels(words[j]));
                    words[j] = temp;
                }
            }
        }

        System.out.println("Words sorted by number of vowels:");
        for (StringBuffer word : words) {
            System.out.print(word + " ");
        }

    }
    
    // для того щоб поділити текст на слова
    public static StringBuffer[] splitToWords(StringBuffer text) {
        int wordCount = 0;
        boolean inWord = false;

        //рахуємо кі-сть слів
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                if (!inWord) {
                    inWord = true;
                    wordCount++;
                }
            } else {
                inWord = false;
            }
        }

        StringBuffer[] words = new StringBuffer[wordCount];
        int index = 0;
        words[index] = new StringBuffer();

        //ділимо на слова
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                words[index].append(c);
            } else {
                if (words[index].length() > 0) {
                    index++;
                    if (index < wordCount) {
                        words[index] = new StringBuffer();
                    }
                }
            }
        }

        return words;
    }

    // для підрахунку голосних у слові
    public static int countVowels(StringBuffer word) {
        String vowels = "aeiouyAEIOUY";
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (int j = 0; j < vowels.length(); j++) {
                if (c == vowels.charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
