package pvt.hrk.personal.tracker.adapters.db

import org.springframework.data.repository.CrudRepository;

interface RecordDetailsJPARepository : CrudRepository<RecordDetailsEntity,Long>  {
}