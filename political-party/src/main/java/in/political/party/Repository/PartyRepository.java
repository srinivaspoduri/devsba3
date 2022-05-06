package in.political.party.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.political.party.entity.Party;



@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
	
	
}
