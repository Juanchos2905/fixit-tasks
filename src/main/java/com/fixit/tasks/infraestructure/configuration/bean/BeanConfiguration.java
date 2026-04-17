package com.fixit.tasks.infraestructure.configuration.bean;


import com.fixit.tasks.application.port.in.ITaskServicePort;
import com.fixit.tasks.application.port.out.ITaskPersistencePort;
import com.fixit.tasks.application.usecase.TaskServiceUseCase;
import com.fixit.tasks.domain.service.AssignmentStrategy;
import com.fixit.tasks.domain.service.TaskDomainService;
import com.fixit.tasks.infraestructure.adapters.driven.jpa.adapter.TaskJpaAdapter;
import com.fixit.tasks.infraestructure.adapters.driven.jpa.mapper.ITaskEntityMapper;
import com.fixit.tasks.infraestructure.adapters.driven.jpa.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public ITechnicianPersistencePort technicianPersistencePort(
            ITechnicianRepository technicianRepository,
            ITechnicianEntityMapper technicianEntityMapper
    ) {
       return new TechnicianJpaAdapter(technicianRepository, technicianEntityMapper);
    }

    @Bean
    public ITaskPersistencePort taskPersistencePort(
            ITaskRepository taskRepository,
            ITaskEntityMapper taskEntityMapper
    ) {
        return new TaskJpaAdapter(taskRepository, taskEntityMapper);
    }
    @Bean
    public AssignmentStrategy assignmentStrategy() {
        return new AssignmentStrategy();
    }

    @Bean
    public TechnicianDomainService technicianDomainService() {
        return new TechnicianDomainService();
    }

    @Bean
    public TaskDomainService taskDomainService() {
        return new TaskDomainService();
    }


    @Bean
    public ITechnicianServicePort technicianServicePort(
            ITechnicianPersistencePort technicianPersistencePort,
            TechnicianDomainService technicianDomainService,
            ITaskPersistencePort taskPersistencePort
    ) {
        return new TechnicianUseCase(technicianPersistencePort, technicianDomainService, taskPersistencePort);
    }

    @Bean
    public ITaskServicePort taskServicePort(
            ITaskPersistencePort taskPersistencePort,
            ITechnicianPersistencePort technicianPersistencePort,
            TechnicianDomainService technicianDomainService,
            TaskDomainService taskDomainService,
            AssignmentStrategy assignmentStrategy
    ) {
        return new TaskServiceUseCase(taskPersistencePort, technicianPersistencePort, technicianDomainService, taskDomainService, assignmentStrategy);
    }
}