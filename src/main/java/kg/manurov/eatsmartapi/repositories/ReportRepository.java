package kg.manurov.eatsmartapi.repositories;

import kg.manurov.eatsmartapi.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findReportByUser_IdAndDate(Long user_id, LocalDate date);
}