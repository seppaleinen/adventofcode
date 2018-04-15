package feature.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.java.Log;
import se.david.adventofcode.Day001;

import static org.junit.Assert.assertEquals;

@Log
public class Day001Steps {
    private Day001 day001;
    private String input;
    private int answer = 0;

    @Given("^dayone input is '(.+)'$")
    public void getInput(final String input) {
        day001 = new Day001();
        this.input = input;
    }

    @When("^solving dayone$")
    public void solving() {
        answer = day001.solve(input);

        log.info("Answer is: " + answer);
    }

    @Then("dayone should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }

}
