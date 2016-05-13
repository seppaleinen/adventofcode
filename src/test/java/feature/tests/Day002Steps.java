package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;
import se.david.adventofcode.Day002;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log
public class Day002Steps {
    private Day002 day002;
    private List<String> input;
    private int answer = 0;

    @Given("^daytwo input is$")
    public void getInput(final DataTable dataTable) {
        this.day002 = new Day002();
        this.input = new ArrayList<String>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            input.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving daytwo$")
    public void solving() {
        answer = day002.solve(input);

        log.info("Answer is: " + answer);
    }

    @Then("^daytwo should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }

}
