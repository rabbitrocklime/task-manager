package org.nunolima.taskmanager.core.adaptors;

import org.nunolima.taskmanager.domain.Process;

import java.time.Instant;

public class RunningProcess {
    private final Process process;
    private final Instant instant;

    public RunningProcess(Process process) {
        this.process = process;
        this.instant = Instant.now();
    }

    public Process getProcess() {
        return process;
    }

    public Instant getInstant() {
        return instant;
    }
}
