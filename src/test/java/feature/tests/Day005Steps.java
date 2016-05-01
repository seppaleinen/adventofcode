package feature.tests;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import lombok.extern.java.Log;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log
public class Day005Steps {
    private List<String> inputList;
    private int answer;

    @Given("^day005 input is$")
    public void getInput(final DataTable dataTable) {
        this.inputList = new ArrayList<>();

        DataTableRow[] dataTableRowList = dataTable.getGherkinRows().toArray(new DataTableRow[]{});
        for(DataTableRow dataTableRow : dataTableRowList) {
            inputList.add(dataTableRow.getCells().get(0));
        }
    }

    @When("^solving day005$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.answer = 0;

        for(String input: inputList) {
            log.info(input);
            int vowels = 0;
            boolean sameCharactersInRow = false;
            Character previousChar = ' ';
            for (char c : input.toCharArray()) {
                vowels = c == 'a' || c == 'A' ? ++vowels : vowels;
                vowels = c == 'e' || c == 'E' ? ++vowels : vowels;
                vowels = c == 'i' || c == 'I' ? ++vowels : vowels;
                vowels = c == 'o' || c == 'O' ? ++vowels : vowels;
                vowels = c == 'u' || c == 'U' ? ++vowels : vowels;

                if (!sameCharactersInRow) {
                    sameCharactersInRow = c == previousChar;
                    previousChar = c;
                }
            }

            boolean contains = input.contains("ab") || input.contains("cd") || input.contains("pq") || input.contains("xy");

            if (vowels > 2 && sameCharactersInRow && !contains) {
                this.answer++;
            }
        }

        log.info("Answer is: " + this.answer);
    }

    @Then("^day005 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }
}

