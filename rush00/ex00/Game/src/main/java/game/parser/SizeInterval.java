package game.parser;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class SizeInterval implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        int n = Integer.parseInt(value);
        if (n < 5 || n > 100) {
            throw new ParameterException("Parameter " + name + " should be in interval {5, 100} (found " + value +")");
        }
    }
}