package org.nunolima.taskmanager.ports;

import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.Process;

import java.util.List;

public interface TaskManagerPort {
    void add(Process process) throws IllegalAccessException;
    List<Process> list();
    void kill(String pid) throws IllegalAccessException;
    void killGroup(Priority priority);
    void killAll();
}
