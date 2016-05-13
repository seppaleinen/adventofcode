package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;
import se.david.adventofcode.Day007;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Log
public class Day007Steps {
    private Day007 day007;
    private List<String> inputList;
    private String answer;

    @Given("^day007 input is$")
    public void getInput(final DataTable dataTable) {
        day007 = new Day007();
        this.inputList = new ArrayList<>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            inputList.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving day007$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        answer = day007.solve(inputList);

        log.info("Answer is: " + this.answer);
    }

    @Then("^day007 should expect '(.+)'$")
    public void answer(final String expected) {
        assertFalse("a should not be 0: " + answer, answer.contains("a:0,"));
        assertEquals(expected, answer);
    }
}

