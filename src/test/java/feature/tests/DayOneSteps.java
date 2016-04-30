package feature.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.java.Log;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Log
public class DayOneSteps {
    private String input;
    private int answer = 0;

    @Given("^dayone input is '(.+)'$")
    public void getInput(final String input) {
        this.input = input;
    }

    @When("^solving dayone$")
    public void solving() {
        for(char c: input.toCharArray()) {
            if(c == '(') {
                answer++;
            } else {
                answer--;
            }
        }

        log.info("Answer is: " + answer);
    }

    @Then("dayone should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }

}
