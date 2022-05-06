package in.political.party.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.political.party.entity.Development;



@Repository
public interface DevelopmentRepository extends JpaRepository<Development, Long> {

	List<Development> findByPoliticalLeaderId(Long politicalLeaderId);
}
