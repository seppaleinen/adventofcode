package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;
import se.david.adventofcode.Day005;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log
public class Day005Steps {
    private Day005 day005;
    private List<String> inputList;
    private int answer;

    @Given("^day005 input is$")
    public void getInput(final DataTable dataTable) {
        this.day005 = new Day005();
        this.inputList = new ArrayList<>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            inputList.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving day005$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        answer = day005.solve(inputList);

        log.info("Answer is: " + this.answer);
    }

    @Then("^day005 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }
}

