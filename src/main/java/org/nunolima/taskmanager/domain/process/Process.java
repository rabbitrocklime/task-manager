package org.nunolima.taskmanager.domain.process;

import org.nunolima.taskmanager.domain.Priority;

import java.util.UUID;

public class Process implements AbstractProcess {
    private final String pid;
    private final Priority priority;

    public Process(Priority priority) {
        this.pid = UUID.randomUUID().toString();
        this.priority = priority;
    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}
