package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Log
public class Day007Steps {
    private List<String> inputList;
    private String answer;

    @Given("^day007 input is$")
    public void getInput(final DataTable dataTable) {
        this.inputList = new ArrayList<>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            inputList.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving day007$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
        log.info("Answer is: " + this.answer);
    }

    @Then("^day007 should expect '(.+)'$")
    public void answer(final String expected) {
        assertFalse("a should not be 0: " + answer, answer.contains("a:0,"));
        assertEquals(expected, answer);
    }
}

