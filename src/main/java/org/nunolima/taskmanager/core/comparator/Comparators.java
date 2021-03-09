package org.nunolima.taskmanager.core.comparator;

import org.nunolima.taskmanager.domain.process.RunningProcess;

import java.util.Comparator;

public final class Comparators {

    private Comparators() {
    }

    public static Comparator<RunningProcess> ageComparator() {
        return Comparator.comparing(RunningProcess::getCreatedAt);
    }

    public static Comparator<RunningProcess> priorityComparator() {
        return Comparator.comparing(RunningProcess::getPriority);
    }

    public static Comparator<RunningProcess> pidComparator() {
        return Comparator.comparing(RunningProcess::getPid);
    }
}
