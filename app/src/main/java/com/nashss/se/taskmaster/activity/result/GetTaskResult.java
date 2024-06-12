package com.nashss.se.taskmaster.activity.result;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.models.TaskModel;

public class GetTaskResult {
    private final Logger log = LogManager.getLogger();
    private final List<TaskModel> pending;
    private final List<TaskModel> completed;

    private GetTaskResult(List<TaskModel> pending, List<TaskModel> completed) {
        log.info("Pending Tasks: {}, Completed Tasks: {}", pending, completed);
        this.pending = pending;
        this.completed = completed;
    }

    public List<TaskModel> getPending() {
        return pending;
    }

    public List<TaskModel> getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "GetTaskResult{" +
        "Pending='" + pending + '\'' +
        "Completed='" + completed + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TaskModel> pending;
        private List<TaskModel> completed;

        public Builder withPendingTasks(List<TaskModel> pending) {
            this.pending = pending;
            return this;
        }

        public Builder withCompletedTasks(List<TaskModel> completed) {
            this.completed = completed;
            return this;
        }
        
        public GetTaskResult build() {
            return new GetTaskResult(pending, completed);
        }
    }
}
