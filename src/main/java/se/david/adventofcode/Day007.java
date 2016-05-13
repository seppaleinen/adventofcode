package se.david.adventofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day007 {
    public String solve(List<String> inputList) {
        String answer = "";

        Map<String, Integer> map = new TreeMap<>();

        final Pattern pattern = Pattern.compile("^(.+) -> ([a-z]+$)");

        List<String> fulList = new ArrayList<>();

        for(String input : inputList) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String leftSide = matcher.group(1);
                String rightSide = matcher.group(2);

                fulList.add(rightSide + ":" + leftSide);
            }
        }

        for (String input : fulList) {
            String leftSide = input.split(":")[1];
            String rightSide = input.split(":")[0];

            if (leftSide.contains("AND")) {
                Pattern andPattern = Pattern.compile("([a-z]+) (.+) ([a-z0-9]+)");
                Matcher andMatcher = andPattern.matcher(leftSide);
                if (andMatcher.matches()) {
                    String leftKey = andMatcher.group(1);
                    String rightKey = andMatcher.group(3);
                    Integer leftValue = map.get(leftKey);
                    Integer rightValue = map.get(rightKey);

                    leftValue = leftValue == null ? 0 : leftValue;
                    rightValue = rightValue == null ? 0 : rightValue;

                    map.put(rightSide, (leftValue & rightValue));
                }

            } else if (leftSide.contains("OR")) {
                Pattern orPattern = Pattern.compile("([a-z]+) (.+) ([a-z0-9]+)");
                Matcher orMatcher = orPattern.matcher(leftSide);
                if (orMatcher.matches()) {
                    String leftKey = orMatcher.group(1);
                    String rightKey = orMatcher.group(3);
                    Integer leftValue = map.get(leftKey);
                    Integer rightValue = map.get(rightKey);

                    leftValue = leftValue == null ? 0 : leftValue;
                    rightValue = rightValue == null ? 0 : rightValue;

                    map.put(rightSide, (leftValue | rightValue));
                }

            } else if (leftSide.contains("LSHIFT")) {
                Pattern leftShiftPattern = Pattern.compile("([a-z]+) (.+) ([a-z0-9]+)");
                Matcher leftShiftMatcher = leftShiftPattern.matcher(leftSide);
                if (leftShiftMatcher.matches()) {
                    String leftKey = leftShiftMatcher.group(1);
                    String rightValue = leftShiftMatcher.group(3);
                    Integer leftValue = map.get(leftKey);

                    leftValue = leftValue == null ? 0 : leftValue;
                    rightValue = rightValue == null ? "0" : rightValue;

                    map.put(rightSide, (leftValue << Integer.valueOf(rightValue)));
                }

            } else if (leftSide.contains("RSHIFT")) {
                Pattern rightShiftPattern = Pattern.compile("([a-z]+) (.+) ([a-z0-9]+)");
                Matcher rightShiftMatcher = rightShiftPattern.matcher(leftSide);
                if (rightShiftMatcher.matches()) {
                    String leftKey = rightShiftMatcher.group(1);
                    String rightValue = rightShiftMatcher.group(3);
                    Integer leftValue = map.get(leftKey);

                    leftValue = leftValue == null ? 0 : leftValue;
                    rightValue = rightValue == null ? "0" : rightValue;

                    map.put(rightSide, (leftValue >> Integer.valueOf(rightValue)));
                }
            } else if (leftSide.contains("NOT")) {
                Pattern notPattern = Pattern.compile("([A-Z]+) (.+)");
                Matcher notMatcher = notPattern.matcher(leftSide);

                if (notMatcher.matches()) {
                    String rightKey = notMatcher.group(2);
                    Integer rightValue = map.get(rightKey);

                    rightValue = rightValue == null ? 0 : rightValue;

                    map.put(rightSide, (65536 + (~rightValue)));
                }

            } else {
                if (leftSide.matches("^[0-9]+$")) {
                    map.put(rightSide, Integer.valueOf(leftSide));
                } else {
                    Integer value = map.get(leftSide) == null ? 0 : map.get(leftSide);
                    map.put(rightSide, value);
                }
            }
        }

        answer = map.toString().replaceAll("=", ":");

        return answer;
    }
}
