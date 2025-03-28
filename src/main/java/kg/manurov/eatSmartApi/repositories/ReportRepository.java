package kg.manurov.eatSmartApi.repositories;

import kg.manurov.eatSmartApi.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}