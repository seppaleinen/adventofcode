package se.david.adventofcode;

public class Day001 {
    public int solve(String input) {
        int answer = 0;

        for(char c: input.toCharArray()) {
            if(c == '(') {
                answer++;
            } else {
                answer--;
            }
        }

        return answer;
    }
}
