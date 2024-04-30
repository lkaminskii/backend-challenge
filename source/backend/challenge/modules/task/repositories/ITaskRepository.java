package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.models.Task;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository {

	Task index(UUID taskId);
	List<Task> show();
	Task create(TaskDTO taskDTO);
	Task update(Task task);
	void delete(UUID taskId);
	boolean existsById(UUID taskId);
	Task updateProgress(TaskProgressDTO taskProgressDTO);

}
