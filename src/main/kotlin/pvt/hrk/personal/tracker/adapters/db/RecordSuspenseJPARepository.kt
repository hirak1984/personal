package pvt.hrk.personal.tracker.adapters.db

import org.springframework.data.repository.CrudRepository;

interface RecordSuspenseJPARepository : CrudRepository<RecordSuspenseEntity,Long>  {
}