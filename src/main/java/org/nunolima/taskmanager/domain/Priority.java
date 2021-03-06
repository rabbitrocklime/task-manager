package org.nunolima.taskmanager.domain;

public enum Priority {
    // Linux and UNIX® systems use a priority system with 40 priorities (See README), ranging from -20 (highest priority)
    // to 19 (lowest priority). I decided to use the following values:

    LOW(15),
    MEDIUM(0),
    HIGH(-15),
    ;

    final int priority;

    Priority(int priority) {
        this.priority = priority;
    }
}
