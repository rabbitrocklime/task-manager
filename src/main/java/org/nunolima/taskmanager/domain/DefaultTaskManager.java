package org.nunolima.taskmanager.domain;

import org.nunolima.taskmanager.core.adaptors.RunningProcess;
import org.nunolima.taskmanager.ports.TaskManagerPort;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DefaultTaskManager implements TaskManagerPort {
    private final int capacity;
    private final LinkedList<RunningProcess> runningProcesses;

    public DefaultTaskManager(int capacity) {
        this.capacity = capacity;
        this.runningProcesses = new LinkedList<>();
    }

    @Override
    public void add(Process process) throws IllegalAccessException {
        if (runningProcesses.size() + 1 > capacity) {
            // TODO - Create proper exception
            throw new IllegalAccessException();
        }
        runningProcesses.add(new RunningProcess(process));
    }

    @Override
    public List<Process> list() {
        // TODO - Will need comparator here
        return runningProcesses.stream().map(RunningProcess::getProcess).collect(toList());
    }

    @Override
    public void kill(String pid) throws IllegalAccessException {
        runningProcesses.stream()
                .filter(runningProcess -> runningProcess.getProcess().getPid().equals(pid))
                .findFirst()
                .map(runningProcesses::remove)
                .orElseThrow(IllegalAccessException::new);
    }

    @Override
    public void killGroup(Priority priority) {
        Set<RunningProcess> proccessesToRemove = runningProcesses.stream()
                .filter(runningProcess -> runningProcess.getProcess().getPriority().equals(priority))
                .collect(Collectors.toSet());

        runningProcesses.removeAll(proccessesToRemove);
    }

    @Override
    public void killAll() {
        runningProcesses.clear();
    }
}
