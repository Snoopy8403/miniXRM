package com.minixrm.backend.config;

import com.minixrm.backend.model.entity.Activity;
import com.minixrm.backend.model.entity.Partner;
import com.minixrm.backend.model.entity.Qualification;
import com.minixrm.backend.model.enums.PartnerStatus;
import com.minixrm.backend.repository.ActivityRepository;
import com.minixrm.backend.repository.PartnerRepository;
import com.minixrm.backend.repository.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SeedDataLoader implements CommandLineRunner {

    private final QualificationRepository qualificationRepository;
    private final PartnerRepository partnerRepository;
    private final ActivityRepository activityRepository;

    @Override
    public void run(String... args) {

        if (partnerRepository.count() > 0) {
            return;
        }

        /*
         * Qualifications
         */

        Qualification active = qualificationRepository.save(
                createQualification("Aktív"));

        Qualification foreign = qualificationRepository.save(
                createQualification("Külföldi"));

        Qualification top = qualificationRepository.save(
                createQualification("TOP"));

        Qualification export = qualificationRepository.save(
                createQualification("Export"));

        /*
         * Partners
         */

        Partner p1 = new Partner();
        p1.setName("ACME Ltd.");
        p1.setTaxNumber("12345678-2-41");
        p1.setAddress("Budapest");
        p1.setStatus(PartnerStatus.ACTIVE);
        p1.setQualifications(Set.of(active, top));

        Partner p2 = new Partner();
        p2.setName("Global Trade Kft.");
        p2.setTaxNumber("87654321-2-41");
        p2.setAddress("Debrecen");
        p2.setStatus(PartnerStatus.ACTIVE);
        p2.setQualifications(Set.of(export, foreign));

        Partner p3 = new Partner();
        p3.setName("Local Supplier Bt.");
        p3.setTaxNumber("11112222-2-41");
        p3.setAddress("Szeged");
        p3.setStatus(PartnerStatus.INACTIVE);
        p3.setQualifications(Set.of(active));

        partnerRepository.saveAll(List.of(p1, p2, p3));

        /*
         * Activities
         */

        Activity a1 = createActivity(
                "Kickoff meeting",
                "MEETING",
                "Project start discussion",
                60,
                "John Doe",
                p1
        );

        Activity a2 = createActivity(
                "Follow-up call",
                "CALL",
                "Follow-up with client",
                30,
                "Jane Smith",
                p1
        );

        Activity a3 = createActivity(
                "Export planning",
                "MEETING",
                "Export strategy planning",
                90,
                "John Doe",
                p2
        );

        Activity a4 = createActivity(
                "Supplier negotiation",
                "MEETING",
                "Negotiation about pricing",
                45,
                "Mike Johnson",
                p2
        );

        activityRepository.saveAll(List.of(a1, a2, a3, a4));
    }

    private Qualification createQualification(String name) {
        Qualification q = new Qualification();
        q.setName(name);
        return q;
    }

    private Activity createActivity(
            String subject,
            String type,
            String description,
            int duration,
            String responsible,
            Partner partner) {

        Activity a = new Activity();
        a.setSubject(subject);
        a.setType(type);
        a.setDescription(description);
        a.setDurationMinutes(duration);
        a.setResponsibleName(responsible);
        a.setPartner(partner);

        return a;
    }
}