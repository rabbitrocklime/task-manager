package org.nunolima.taskmanager.domain;

import java.util.UUID;

public abstract class Process {
    private final String pid;
    private final Priority priority;

    public Process(Priority priority) {
        this.pid = UUID.randomUUID().toString();
        this.priority = priority;
    }

    public String getPid() {
        return pid;
    }

    public Priority getPriority() {
        return priority;
    }
}
