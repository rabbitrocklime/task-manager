package org.nunolima.taskmanager.domain.process;

import org.nunolima.taskmanager.domain.Priority;

public interface AbstractProcess {
    String getPid();

    Priority getPriority();
}
