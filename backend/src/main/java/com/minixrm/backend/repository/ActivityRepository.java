package com.minixrm.backend.repository;


import com.minixrm.backend.dto.report.ResponsibleReportDto;
import com.minixrm.backend.model.entity.Activity;
import com.minixrm.backend.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByPartnerId(Long partnerId);

    @Query("""
SELECT new com.minixrm.backend.dto.report.ResponsibleReportDto(
    a.responsibleName,
    SUM(a.durationMinutes),
    COUNT(DISTINCT a.partner.id)
)
FROM Activity a
GROUP BY a.responsibleName
""")
    List<ResponsibleReportDto> getResponsibleReport();

    List<Activity> findByPartner(Partner partner);

}
