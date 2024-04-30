package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

    private IDeleteTaskService deleteTaskService;
    private ICreateTaskService createTaskService;
    private IRetrieveTaskByIdService retrieveTaskByIdService;

    @Before
    public void init() {
        final ITaskRepository taskRepository = new TaskRepository();

        deleteTaskService = new DeleteTaskService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
    }

    @Test
    public void shouldBeAbleToDeleteTaskById() {
        TaskDTO taskDTO = new TaskDTO("Test", "Test description");

        Task task = createTaskService.execute(taskDTO);
        deleteTaskService.execute(task.getId());
        Task deletedTask = retrieveTaskByIdService.execute(task.getId());

        assertEquals(null, task);
    }
}