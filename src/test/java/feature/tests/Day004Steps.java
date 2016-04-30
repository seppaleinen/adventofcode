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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@Log
public class Day004Steps {
    private String input;
    private int answer = 0;

    @Given("^day004 input is '(.+)'$")
    public void getInput(final String input) {
        this.input = input;
    }

    @When("^solving day004$")
    public void solving() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int index = 0;
        while(true) {
            String key = String.format("%s%d", input, index++);

            byte[] hash = messageDigest.digest(key.getBytes("UTF-8"));

            if(DatatypeConverter.printHexBinary(hash).startsWith("00000")) {
                answer = index - 1;
                break;
            }
        }
        log.info("Answer is: " + index);
    }

    @Then("^day004 should expect '(.+)'$")
    public void answer(final int expected) {
        assertEquals(expected, answer);
    }
}

