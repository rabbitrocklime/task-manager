package org.nunolima.taskmanager.domain.process;

import java.time.Instant;

public class RunningProcess extends ProcessDecorator {
    private final Instant creation;

    public RunningProcess(Process process) {
        super(process);
        this.creation = Instant.now();
    }

    public Instant getCreation() {
        return creation;
    }

}
