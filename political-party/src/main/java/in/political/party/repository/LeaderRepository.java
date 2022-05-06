package in.political.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.political.party.entity.Leader;
import java.util.List;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long>{

	

	List<Leader> findByPoliticalLeaderId(Long politicalleaderid);
}
