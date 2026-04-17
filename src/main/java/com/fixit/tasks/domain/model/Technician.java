package com.fixit.tasks.domain.model;

import com.fixit.tasks.domain.enums.TechnicianCategory;
import com.fixit.tasks.domain.enums.TechnicianStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Technician {
    Long id;
    String dni;
    String name;
    TechnicianCategory category;
    TechnicianStatus status;
    Integer taskCount;
    Integer currentPoints;

    public static Technician createNew(String dni , String name, TechnicianCategory category) {
        return Technician.builder()
                .dni(dni)
                .name(name)
                .category(category)
                .status(TechnicianStatus.AVAILABLE)
                .taskCount(0)
                .currentPoints(0)
                .build();
    }


    public Integer getAvailablePoints(){
        int maxPoints = category.getMaxPoints();
        if (maxPoints == 0) return 0;
        return maxPoints - this.currentPoints;
    }


}
