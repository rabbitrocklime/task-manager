package org.nunolima.taskmanager.core.comparator;

import org.nunolima.taskmanager.domain.process.RunningProcess;

import java.util.Comparator;

public interface Comparators {
    static Comparator<RunningProcess> ageComparator() {
        return Comparator.comparing(RunningProcess::getCreatedAt);
    }

    static Comparator<RunningProcess> priorityComparator() {
        return Comparator.comparing(RunningProcess::getPriority);
    }

    static Comparator<RunningProcess> pidComparator() {
        return Comparator.comparing(RunningProcess::getPid);
    }
}
