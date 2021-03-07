package org.nunolima.taskmanager.usecase;

import org.nunolima.taskmanager.core.comparator.Comparators;
import org.nunolima.taskmanager.core.errorhandling.NoMoreResourcesException;
import org.nunolima.taskmanager.core.errorhandling.TaskManagerException;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.domain.process.RunningProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;

public class PriorityTaskManager extends DefaultTaskManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTaskManager.class);

    public PriorityTaskManager(int capacity) {
        super(capacity);
        super.runningProcesses = new PriorityQueue<>(Comparators.priorityComparator());
    }

    @Override
    public void add(Process process) throws TaskManagerException {
        if (runningProcesses.size() + 1 > capacity) {
            runningProcesses.stream()
                    .filter(runningProcess -> runningProcess.getPriority().getPriorityValue() > process.getPriority().getPriorityValue())
                    .min(Comparators.ageComparator())
                    .map(runningProcesses::remove)
                    .orElseThrow(() -> {
                        NoMoreResourcesException noMoreResourcesException = new NoMoreResourcesException();
                        LOGGER.warn("Capacity exceeded", noMoreResourcesException);
                        return noMoreResourcesException;
                    });
        }
        runningProcesses.add(new RunningProcess(process));
        LOGGER.info("Process: {} added", process);
    }
}
