package org.nunolima.taskmanager.domain.process;

import org.nunolima.taskmanager.domain.Priority;

public class ProcessDecorator implements AbstractProcess {
    protected Process process;

    public ProcessDecorator(Process process) {
        this.process = process;
    }

    @Override
    public String getPid() {
        return process.getPid();
    }

    @Override
    public Priority getPriority() {
        return process.getPriority();
    }

    public Process getProcess() {
        return process;
    }
}