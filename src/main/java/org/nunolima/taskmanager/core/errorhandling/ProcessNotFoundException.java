package org.nunolima.taskmanager.core.errorhandling;

public class ProcessNotFoundException extends TaskManagerException {

    public ProcessNotFoundException() {
    }

    public ProcessNotFoundException(String message) {
        super(message);
    }
}
