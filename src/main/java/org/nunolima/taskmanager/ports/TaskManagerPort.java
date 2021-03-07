package org.nunolima.taskmanager.ports;

import org.nunolima.taskmanager.core.errorhandling.TaskManagerException;
import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.domain.process.RunningProcess;

import java.util.Comparator;
import java.util.List;

public interface TaskManagerPort {
    void add(Process process) throws TaskManagerException;

    List<Process> list(Comparator<RunningProcess> comparator);

    void kill(String pid) throws TaskManagerException;

    void killGroup(Priority priority);

    void killAll();
}
