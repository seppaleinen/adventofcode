package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log
public class Day002Steps {
    private List<String> input;
    private int answer = 0;

    @Given("^daytwo input is$")
    public void getInput(final DataTable dataTable) {
        this.input = new ArrayList<String>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            input.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving daytwo$")
    public void solving() {
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
        log.info("Answer is: " + answer);
    }

    @Then("^daytwo should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }

}
