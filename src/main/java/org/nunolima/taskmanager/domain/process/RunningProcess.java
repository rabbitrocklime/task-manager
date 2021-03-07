package org.nunolima.taskmanager.domain.process;

import java.time.Instant;

public class RunningProcess extends ProcessDecorator {
    private final Instant createdAt;

    public RunningProcess(Process process) {
        super(process);
        this.createdAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "RunningProcess{" +
                "process=" + process +
                ", createdAt=" + createdAt +
                '}';
    }
}
