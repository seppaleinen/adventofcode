package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@Log
public class Day003Steps {
    private List<String> input;
    private int answer = 0;

    @Given("^day003 input is$")
    public void getInput(final DataTable dataTable) {
        this.input = new ArrayList<String>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            input.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving day003$")
    public void solving() {
        Map<String, Integer> map = new HashMap<>();

        map.put(0 + ":" + 0, 1);

        for(String row: input) {
            int currentX = 0;
            int currentY = 0;

            for(char way : row.toCharArray()) {
                if(WAY.NORTH.equals(WAY.getEnumFromCode(way))) {
                    currentX++;
                } else if(WAY.SOUTH.equals(WAY.getEnumFromCode(way))) {
                    currentX--;
                } else if(WAY.EAST.equals(WAY.getEnumFromCode(way))) {
                    currentY++;
                } else if(WAY.WEST.equals(WAY.getEnumFromCode(way))) {
                    currentY--;
                }

                String coords = currentX + ":" + currentY;

                Integer currentPlace = map.get(coords);
                if(currentPlace != null){
                    map.replace(coords, currentPlace++);
                } else {
                    map.put(coords, 1);
                }
            }
        }

        answer = map.size();

        log.info("Answer is: " + answer);
    }

    @Then("^day003 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }


    public enum WAY {
        NORTH('^'),
        EAST('>'),
        SOUTH('v'),
        WEST('<');

        private char code;

        WAY(char code) {
            this.code = code;
        }

        public static WAY getEnumFromCode(char code) {
            WAY way = null;

            if(NORTH.code == code) {
                way = NORTH;
            } else if(EAST.code == code) {
                way = EAST;
            } else if(SOUTH.code == code) {
                way = SOUTH;
            } else if(WEST.code == code) {
                way = WEST;
            }

            return way;
        }

    }
}
