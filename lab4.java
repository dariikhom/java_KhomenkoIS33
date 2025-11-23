import java.util.ArrayList;
import java.util.List;

// клас Letter представляє окрему літеру
class Letter {
    
    private char character; // поле для зберігання символу

    public Letter(char character) {
        this.character = character;
    }

    // геттер для отримання символу
    public char getCharacter() { 
        return character;
    }

    // toString для повернення літери як рядка
    @Override
    public String toString() {
        return String.valueOf(character);
    }

    // перевірка, чи голосна літера
    public boolean isVowel() {
        String vowels = "aeiouyAEIOUY";
        return vowels.indexOf(character) != -1;  // повертає true, якщо character є в vowels, інакше false
    }
}

// клас Word представляє слово (масив літер)
class Word {
    private Letter[] letters;  // поле для зберігання масиву літер

    public Word(Letter[] letters) {
        this.letters = letters;
    }

    // геттер для отримання масиву літер
    public Letter[] getLetters() {
        return letters;
    }

    // підрахунок голосних у слові
    public int countVowels() {
        int count = 0;
        // проходимо по кожній літері
        for (Letter l : letters) {
            if (l.isVowel()) {
                count++;
            }
        }
        return count;
    }

    // toString для повернення слова як рядка
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter l : letters) { 
            sb.append(l.getCharacter()); // додаємо по літері
        }
        return sb.toString();
    }
}

// клас Punctuation представляє розділовий знак
class Punctuation {
    private char symbol;  // поле для зберігання символу розділового знаку

    public Punctuation(char symbol) {
        this.symbol = symbol;
    }

    // геттер для отримання символу
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}

// клас Sentence представляє речення (масив слів і розділових знаків)
class Sentence {
    private List<Object> elements; // може містити Word або Punctuation

    // конструктор класу, створює порожній список елементів
    public Sentence() {
        this.elements = new ArrayList<>();
    }

    // метод для додавання елемента (Word або Punctuation) у речення
    public void addElement(Object element) {
        elements.add(element);
    }

    // геттер для отримання списку елементів
    public List<Object> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object el : elements) {
            sb.append(el.toString()); // додаємо елемент
            // додаємо пробіл після слова
            if (el instanceof Word) {
                sb.append(" ");
            }
        }
        return sb.toString().trim(); // повертаємо готовий рядок без зайвих пробілів на кінці
    }
}

// клас Text — представляє текст (масив речень)
class Text {
    private List<Sentence> sentences;  // поле для зберігання списку речень

    public Text() {
        this.sentences = new ArrayList<>();
    }

    // метод для додавання речення до тексту
    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence s : sentences) {   // проходимо по кожному реченню
            sb.append(s.toString()).append(" ");  // додаємо речення та пробіл
        }
        return sb.toString().trim();  // повертаємо готовий рядок без зайвих пробілів на кінці
    }
}

// головний клас програми
public class lab4 {
    public static void main(String[] args) {
        // приклад тексту
        String rawText = "You're good at English when you know the difference between a man eating chicken and a man-eating chicken. "
                + "Facing his greatest fear, he ate his first marshmallow.";

        // замінюємо послідовність пробілів і табуляцій одним пробілом
        rawText = rawText.replaceAll("\\s+", " ");

        // розділяємо на речення
        String[] rawSentences = rawText.split("(?<=[.!?])"); // зберігаємо розділові знаки

        Text text = new Text();  // створюємо об'єкт тексту

        for (String rs : rawSentences) {
            Sentence sentence = new Sentence();

            // розділяємо на слова і розділові знаки
            String[] tokens = rs.trim().split("(?=[,.!?-])|\\s+"); // роздільники + слова

            // обробляємо кожен токен
            for (String token : tokens) {

                // якщо токен - розділовий знак
                if (token.matches("[,!.?-]")) {
                    sentence.addElement(new Punctuation(token.charAt(0)));
                } else if (!token.isEmpty()) {  // якщо токен - слово
                    // створюємо масив літер
                    Letter[] letters = new Letter[token.length()];
                    for (int i = 0; i < token.length(); i++) {
                        letters[i] = new Letter(token.charAt(i));
                    }
                    sentence.addElement(new Word(letters));  // додаємо слово до речення
                }
            }
            text.addSentence(sentence);  // додаємо речення до тексту
        }

        // збір всіх слів для сортування за кількістю голосних
        List<Word> allWords = new ArrayList<>();
        for (Sentence s : text.getSentences()) {
            for (Object el : s.getElements()) {
                if (el instanceof Word) {
                    allWords.add((Word) el);
                }
            }
        }

        // сортування слів за кількістю голосних (зростання)
        allWords.sort((w1, w2) -> Integer.compare(w1.countVowels(), w2.countVowels()));

        System.out.println("Words sorted by number of vowels:");
        for (Word w : allWords) {
            System.out.print(w + " ");
        }
    }
}
