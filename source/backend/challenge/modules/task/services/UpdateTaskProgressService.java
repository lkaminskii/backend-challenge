package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService{
    private RetrieveTaskByIdService retrieveTaskByIdService;
    private ITaskRepository taskRepository;

    @Inject
    public UpdateTaskProgressService (RetrieveTaskByIdService retrieveTaskByIdService, ITaskRepository taskRepository) {
        this.retrieveTaskByIdService = retrieveTaskByIdService;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(TaskProgressDTO taskProgressDTO) {
        Task task = retrieveTaskByIdService.execute(taskProgressDTO.getId());

        if (task.getProgress() == 100) {
            task.setStatus(TaskStatus.COMPLETE);
        }

        return taskRepository.updateProgress(taskProgressDTO);
    }
}
