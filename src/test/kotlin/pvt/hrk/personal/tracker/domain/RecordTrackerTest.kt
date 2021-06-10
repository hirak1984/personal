package pvt.hrk.personal.tracker.domain

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mock
import pvt.hrk.personal.tracker.adapters.StorageAdapter
import pvt.hrk.personal.tracker.adapters.db.RecordDetailsJPARepository
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.adapters.db.RecordDetailsEntity
import org.mockito.Mockito
import org.mockito.ArgumentMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import pvt.hrk.personal.PersonalApplication


@SpringBootTest(classes = [PersonalApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension::class)
class RecordTrackerTest {
    @Mock
    var storageAdapter: StorageAdapter? = null

    @Mock
    var recordDetailsJPARepository: RecordDetailsJPARepository? = null
    @Test
    fun saveOrUpdateTest() {
        val model = RecordDetailsModel()
        val entity = RecordDetailsEntity()
        entity.id = 1L
        Mockito.`when`(
            recordDetailsJPARepository!!.save(
                ArgumentMatchers.any(
                    RecordDetailsEntity::class.java
                )
            )
        ).thenReturn(entity)
        storageAdapter!!.saveOrUpdate(model)
        MatcherAssert.assertThat(model.id, ArgumentMatchers.isNotNull())
    }
}