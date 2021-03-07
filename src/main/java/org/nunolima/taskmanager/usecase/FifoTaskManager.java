package org.nunolima.taskmanager.usecase;

import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.domain.process.RunningProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class FifoTaskManager extends DefaultTaskManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTaskManager.class);

    public FifoTaskManager(int capacity) {
        super(capacity);
    }

    @Override
    public void add(Process process) {
        if (runningProcesses.size() + 1 > capacity) {
            LinkedList<RunningProcess> runningProcesses = (LinkedList<RunningProcess>) this.runningProcesses;
            runningProcesses.removeFirst();
        }
        runningProcesses.add(new RunningProcess(process));
        LOGGER.info("Process: {} added", process);
    }
}
