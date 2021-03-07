package org.nunolima.taskmanager.core.errorhandling;

public class NoMoreResourcesException extends TaskManagerException {

    public NoMoreResourcesException() {
    }

    public NoMoreResourcesException(String message) {
        super(message);
    }
}
