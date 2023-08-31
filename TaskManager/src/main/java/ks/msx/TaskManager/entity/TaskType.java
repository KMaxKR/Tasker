package ks.msx.TaskManager.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskType {
    TODO("TODO"),
    DONE("DONE");

    @Getter
    public final String type;
}
