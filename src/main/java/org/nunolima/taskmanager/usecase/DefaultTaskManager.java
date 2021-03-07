package org.nunolima.taskmanager.usecase;

import org.nunolima.taskmanager.core.errorhandling.NoMoreResourcesException;
import org.nunolima.taskmanager.core.errorhandling.ProcessNotFoundException;
import org.nunolima.taskmanager.core.errorhandling.TaskManagerException;
import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.domain.process.RunningProcess;
import org.nunolima.taskmanager.ports.TaskManagerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultTaskManager implements TaskManagerPort {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTaskManager.class);

    protected final int capacity;
    protected Collection<RunningProcess> runningProcesses = new LinkedList<>();

    public DefaultTaskManager(int capacity) {
        this.capacity = capacity;
        LOGGER.info("Task Manager initialized with {} capacity", capacity);
    }

    @Override
    public void add(Process process) throws TaskManagerException {
        if (runningProcesses.size() + 1 > capacity) {
            NoMoreResourcesException noMoreResourcesException = new NoMoreResourcesException();
            LOGGER.warn("Capacity exceeded:", noMoreResourcesException);
            throw noMoreResourcesException;
        }
        runningProcesses.add(new RunningProcess(process));
        LOGGER.info("Process: {} added", process);
    }

    @Override
    public List<Process> list(Comparator<RunningProcess> comparator) {
        return runningProcesses.stream().map(RunningProcess::getProcess).collect(Collectors.toList());
    }

    @Override
    public void kill(String pid) throws TaskManagerException {
        runningProcesses.stream()
                .filter(runningProcess -> runningProcess.getPid().equals(pid))
                .findFirst()
                .map(process -> {
                    if (runningProcesses.remove(process)) {
                        LOGGER.info("Process: {} killed", process);
                    }
                    return process;
                })
                .orElseThrow(ProcessNotFoundException::new);
    }

    @Override
    public void killGroup(Priority priority) {
        Set<RunningProcess> processesToRemove = runningProcesses.stream()
                .filter(runningProcess -> runningProcess.getPriority().equals(priority))
                .collect(Collectors.toSet());

        if (runningProcesses.removeAll(processesToRemove)) {
            LOGGER.info("Processes: {} killed", processesToRemove);
        }
    }

    @Override
    public void killAll() {
        runningProcesses.clear();
        LOGGER.info("All Processes killed");

    }

    public int getProcessCount() {
        return runningProcesses.size();
    }
}
