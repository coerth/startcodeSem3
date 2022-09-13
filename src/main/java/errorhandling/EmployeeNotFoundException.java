package errorhandling;

import javax.persistence.EntityNotFoundException;

public class EmployeeNotFoundException extends EntityNotFoundException
{
    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
