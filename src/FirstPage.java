import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FirstPage {

    /**
     * Zadanie 2
     * Dana jest tablica zmiennych typu char przechowująca napis ala ma kota. Napisz
     * metodę, która policzy ile razy wystąpiły litery składające się na ciąg dostarczony
     * jako argument.
     * Dla mnie polecenie jest niejasne. Zrobiłem tak, jak zrozumiałem.
     */
    private static int countAppearances(String input) {
        /* Licznik, w którym przechowujemy wystąpienia liter ciągu "input" w ciągu "givenCharArray" */
        int count = 0;
        char[] givenCharArray = "ala ma kota".toCharArray();
        //char[] givenCharArray = { 'a', 'l', 'a', 'm', 'a', 'k', 'o', 't', 'a' } // Alternatywny zapis
        HashSet<Character> uniqueCharSet = new HashSet<Character>();

        /* Pozbywamy się białych znaków i usuwamy powtarzające się litery */
        for (char c : input.replaceAll(" ", "").toCharArray()) {
            uniqueCharSet.add(c);
        }

        for (char c : uniqueCharSet) {
            for (char s : givenCharArray) {
                if (s == c) {
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * Zadanie 4
     * Utwórz statyczną metodę splitToDigits przyjmującą jako argument wartość całkowitą dodatnią i zwracającą jednowymiarową tablicę wartości całkowitych dodatnich.
     * Celem funkcji będzie utworzenie tablicy o rozmiarze tożsamym z ilością cyfr liczby
     * dostarczonej jako argument, a następnie wypełnienie jej cyframi z jakich składa się
     * dostarczona liczba
     */
    private static int[] splitToDigits(int n) {
        /* Nie obsługujemy liczb innych niż pozytywne */
        if (n <= 0) {
            throw new IllegalArgumentException("Passed argument must be positive integer value.");
        }

        int digitsNumber = 0, step, multiplier;
        /* Zmienna pomocnicza by nie nadpisać wartości "n" podanej w parametrach */
        int i = n;

        /* Pętla działa dopóki nie pozbędziemy się wszystkich cyfr (i będzie równe 0) */
        while (i > 0) {
            /* Dzielimy przez 10 by pozbyć się liczby jedności */
            i = i / 10;
            /* Inkrementujemy licznik cyfr w podanej liczbie */
            digitsNumber ++;
        }

        /* Inicjalizujemy wynikową tablicę, gdy już wiemy ile mamy cyfr w liczbie n */
        int [] resultArray = new int[digitsNumber];

        for (i = digitsNumber; i > 0; i--) {
            /** Poniżej opis działania na podstawie przykładowej liczby 546:
             * 546 / 100 = 5 -> pierwsza cyfra
             * 546 - 500 = 46
             * 46 / 10 = 4 -> druga cyfra
             * 46 - 40 = 6
             * 6 / 1 = 6 -> trzecia cyfra
             **/
            multiplier = (int) Math.pow(10, i - 1);
            step = n / multiplier;
            resultArray[i - 1] = step;
            n -= step * multiplier;
        }

        return resultArray;
    }

    /**
     * Zadanie 5
     * Utwórz statyczną funkcję isArmstrongNumber przyjmującą jako argument wartość
     * typu int i dostarczającą jako rezultat wartość logiczną. Przyjmij że działanie tej
     * funkcji będzie adekwatne do nazwy funkcji i będzie sprawdzawdzało czy dostarczona jako
     * argument wartość jest liczbą Armstronga (narcystyczną).
     */
    private static boolean isArmstrongNumber(int i) {
        /* Nie obsługujemy liczb innych niż pozytywne */
        if (i <= 0) {
            throw new IllegalArgumentException("Passed argument should be higher than 0");
        }

        /* Zmienna pomocnicza by nie nadpisać wartości "i" podanej w parametrach */
        int j = i;
        /* Lista do przechowywania pojedynczych cyfr składających się na liczbę i */
        List<Integer> digitsList = new ArrayList<>();

        /* Pętla działa dopóki nie pozbędziemy się wszystkich cyfr (j będzie równe 0) */
        while (j > 0) {
            /* Reszta z dzielenia przez 10 zwraca nam liczbę jedności */
            int number = j % 10;
            /* Odkładamy liczbę jedności w pomocniczej liście */
            digitsList.add(number);
            /* Dzielimy przez 10 by pozbyć się liczby jedności */
            j = j / 10;
        }

        /* Używamy streamów by w zwięzły sposób obliczyć rozwiązanie. Polecam przećwiczyć rozwiązanie bez użycia streamów */
        return digitsList.stream()
                .map(elem -> (int) Math.pow(elem, 3))
                .collect(Collectors.summingInt(Integer::intValue))
                .equals(i);
    }

    /**
     * Zadanie 6
     * Dany jest nagłówek funkcji: public static int [ ] [ ] calculateSquares ( int screenWidth , int screenHeight , int side )
     * przyjmujący jako argumenty odpowiednio: szerokość i wysokość ekranu, oraz długość boku kwadratu.
     * Uzupełnij ciało tej metody algorytmem wyliczającym ile kwadratów o zadanej długości boku można umieścić na ekranie.
     * Następnie wylicz współrzędne (górnego lewego rogu) x i y wszystkich kwadratów
     * i zwróć je w postaci dwu wymiarowej tablicy w której pierwsza kolumna opisuje współrzędne x a druga y.
     *
     * Niedokończone...
     * To zadanie jest zjebane i nikt w rzeczywistości nie tworzy dwywymiarowych tablic w jezyku Java, tylko
     * korzysta się z klas opakowujących, w tym przypadku należy stworzyć klasę Point mającą atrybuty x,y typu int...
     * Poza tym jest nieintuicyjne i nie wiadomo co autor ma na myśli w ostanim zdaniu.
     */
    private static int[][] calculateSquares(int screenWidth, int screenHeight, int side) {
        int screenArea = screenHeight * screenWidth;
        int squaresCount = screenArea / (side * side);
        int [][] resultMatrix = new int[squaresCount][2];

        for (int i = 0; i < squaresCount; i ++) {
            resultMatrix[i][0] = i * side;
            resultMatrix[i][1] = i * side;
        }

        return resultMatrix;
    }

    /**
     * Zadanie 7
     * Dany jest nagłówek metody:
     * public static void swap ( int [ ] tab , int source , int destination )
     * Uzupełnij ciało tej metody, tak aby wskazane przez parametry source i destination
     * elementy tablicy zostały zamienione miejscami.
     */
    private static void swap(int[] tab, int source, int destination) {
        if (source >= tab.length || destination >= tab.length) {
            throw new IllegalArgumentException("Params source and destination have to be less than length of given array.");
        }

        int temp = tab[source];
        tab[source] = tab[destination];
        tab[destination] = temp;
    }

    /**
     * Zadanie 8
     * Utwórz i przetestuj metodę rekurencyjną, która stwierdzi czy dostarczone w tablicy
     * zmiennych typu char słowo jest palindromem.
     * Metoda uwzględnia wielkie litery i nie usuwa białych znaków - do usprawnienia w ramach ćwiczeń
     */
    private static boolean isPalindrome(char[] word) {
        if (word.length == 0 || word.length == 1) return true;

        if (word[word.length - 1] == word[0]) {
            if (word.length == 2) return true;

            var newWord = new char[word.length - 2];

            for (int i = 1; i < word.length - 1; i ++) {
                newWord[i - 1] = word[i];
            }

            return isPalindrome(newWord);
        }

        return false;
    }

    public static void printExercise2Solution() {
        System.out.println("*** Exercise 2 solution ***");
        System.out.println(countAppearances("aaabcdtr"));
    }

    public static void printExercise4Solution() {
        System.out.println("*** Exercise 4 solution ***");
        var sb = new StringBuilder();

        for (int i : splitToDigits(43567)) {
            sb.append(i);
        }

        System.out.println(sb.toString());
    }

    public static void printExercise5Solution() {
        System.out.println("*** Exercise 5 solution ***");
        System.out.println("Is armstrong number: " + isArmstrongNumber(371));
    }

    public static void printExercise6Solution() {
        System.out.println("*** Exercise 6 solution ***");
        var resultMatrix = calculateSquares(2,3,1);
        for (int i = 0; i < resultMatrix.length; i ++) {
            System.out.println(resultMatrix[i][0] + ":" + resultMatrix[i][1]);
        }
    }

    public static void printExercise7Solution() {
        System.out.println("*** Exercise 7 solution ***");
        System.out.println("Input array:");

        int[] tab = new int[] {1,2,3,4,5};

        var sb = new StringBuilder();

        for (int i : tab) {
            sb.append(i);
        }

        System.out.println(sb.toString());

        swap(tab, 2, 4);
        sb = new StringBuilder();

        for (int i : tab) {
            sb.append(i);
        }

        System.out.println("Swapped array:");
        System.out.println(sb.toString());
    }

    public static void printExercise8Solution() {
        System.out.println("*** Exercise 8 solution ***");

        char[] word = "kobyla ma maly bok".replaceAll(" ", "").toCharArray();
        String wordAsString = String.valueOf(word);

        System.out.println("Checking if word: " + wordAsString + " is palindrome:");
        System.out.println(isPalindrome(word));
    }

    public static void main(String[] args) {
        printExercise2Solution();
        System.out.println("=====");
        System.out.println("=====");

        printExercise4Solution();
        System.out.println("=====");
        System.out.println("=====");

        printExercise5Solution();
        System.out.println("=====");
        System.out.println("=====");
//        printExercise6Solution();
        printExercise7Solution();
        System.out.println("=====");
        System.out.println("=====");

        printExercise8Solution();
    }
}
