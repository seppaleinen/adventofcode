package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;
import se.david.adventofcode.Day003;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log
public class Day003Steps {
    private Day003 day003;
    private List<String> input;
    private int answer = 0;

    @Given("^day003 input is$")
    public void getInput(final DataTable dataTable) {
        this.day003 = new Day003();
        this.input = new ArrayList<>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            input.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving day003$")
    public void solving() {
        answer = day003.solve(input);

        log.info("Answer is: " + answer);
    }

    @Then("^day003 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }
}
