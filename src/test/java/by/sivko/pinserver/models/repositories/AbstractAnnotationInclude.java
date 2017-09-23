package by.sivko.pinserver.models.repositories;

import by.sivko.pinserver.PinserverApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@TestExecutionListeners(DbUnitTestExecutionListener.class)
@ContextConfiguration(classes = PinserverApplication.class)
@DirtiesContext
public abstract class AbstractAnnotationInclude extends AbstractTransactionalJUnit4SpringContextTests{
}
