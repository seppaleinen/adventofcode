package se.david.adventofcode;

import java.util.List;

public class Day005 {
    public int solve(List<String> inputList) {
        int answer = 0;

        for(String input: inputList) {
            int vowels = 0;
            boolean sameCharactersInRow = false;
            Character previousChar = ' ';
            for (char c : input.toCharArray()) {
                vowels = c == 'a' || c == 'A' ? ++vowels : vowels;
                vowels = c == 'e' || c == 'E' ? ++vowels : vowels;
                vowels = c == 'i' || c == 'I' ? ++vowels : vowels;
                vowels = c == 'o' || c == 'O' ? ++vowels : vowels;
                vowels = c == 'u' || c == 'U' ? ++vowels : vowels;

                if (!sameCharactersInRow) {
                    sameCharactersInRow = c == previousChar;
                    previousChar = c;
                }
            }

            boolean contains = input.contains("ab") || input.contains("cd") || input.contains("pq") || input.contains("xy");

            if (vowels > 2 && sameCharactersInRow && !contains) {
                answer++;
            }
        }

        return answer;
    }
}
