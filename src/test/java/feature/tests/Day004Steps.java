package feature.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.java.Log;
import se.david.adventofcode.Day004;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

@Log
public class Day004Steps {
    private Day004 day004;
    private String input;
    private int answer = 0;

    @Given("^day004 input is '(.+)'$")
    public void getInput(final String input) {
        this.day004 = new Day004();
        this.input = input;
    }

    @When("^solving day004$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.answer = day004.solve(input);

        log.info("Answer is: " + answer);
    }

    @Then("^day004 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }
}

