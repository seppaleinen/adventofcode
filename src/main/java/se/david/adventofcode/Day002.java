package se.david.adventofcode;

import java.util.List;

public class Day002 {
    public int solve(List<String> input) {
        int answer = 0;

        for(String row: input) {
            String[] cellList = row.trim().split("x");

            int length = Integer.valueOf(cellList[0]);
            int width = Integer.valueOf(cellList[1]);
            int heigth = Integer.valueOf(cellList[2]);

            int first = (length * width);
            int second = (width * heigth);
            int third = (length * heigth);

            int minValue = 0;

            minValue = Math.min(first, second);
            minValue = Math.min(minValue, third);

            answer += ((2 * first) + (2* second) + (2 * third) + minValue);
        }

        return answer;
    }
}
