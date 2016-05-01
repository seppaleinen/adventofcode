package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

@Log
public class Day006Steps {
    private List<String> inputList;
    private int answer;
    private List<List<Boolean>> list = new ArrayList<>();

    @Given("^day006 input is$")
    public void getInput(final DataTable dataTable) {
        this.inputList = new ArrayList<>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            inputList.add(dataTableRow.getCells().get(0));
        }

        for(int x = 0; x < 1000; x++) {
            List<Boolean> boolList = new ArrayList<>();
            for(int y = 0; y < 1000; y++) {
                boolList.add(y, false);
            }
            list.add(x, boolList);
        }
    }

    @When("^solving day006$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.answer = 0;

        final Pattern pattern = Pattern.compile("^(.+) (\\d+),(\\d+) through (\\d+),(\\d+)$");

        for(String input: inputList) {
            Matcher matcher = pattern.matcher(input);
            if(matcher.matches()) {
                String first = matcher.group(1);
                int fromX = Integer.valueOf(matcher.group(2));
                int fromY = Integer.valueOf(matcher.group(3));
                int toX = Integer.valueOf(matcher.group(4));
                int toY = Integer.valueOf(matcher.group(5));

                for(int x = fromX; x <= toX; x++) {
                    for(int y = fromY; y <= toY; y++) {
                        switch (first) {
                            case "turn on":
                                list.get(x).set(y, true);
                                break;
                            case "turn off":
                                list.get(x).set(y, false);
                                break;
                            case "toggle":
                                list.get(x).set(y, !list.get(x).get(y));
                                break;
                        }
                    }
                }
            }
        }


        for(List<Boolean> bool: list){
            for(Boolean bool2: bool) {
                answer = bool2 ? ++answer : answer;
            }
        }

        log.info("Answer is: " + this.answer);
    }

    @Then("^day006 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }
}

