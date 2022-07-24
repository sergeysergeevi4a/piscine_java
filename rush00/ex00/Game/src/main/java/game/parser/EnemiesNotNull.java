package game.parser;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class EnemiesNotNull implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        int n = Integer.parseInt(value);
        if (n == 0) {
            throw new ParameterException("Parameter " + name + " cannot be null (found " + value +")");
        }
    }
}
