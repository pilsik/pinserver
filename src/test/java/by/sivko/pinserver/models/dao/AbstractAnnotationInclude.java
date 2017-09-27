package by.sivko.pinserver.models.dao;

import by.sivko.pinserver.PinServerApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@TestPropertySource("classpath:application-test.properties")
@TestExecutionListeners(DbUnitTestExecutionListener.class)
@ContextConfiguration(classes = PinServerApplication.class)
@DirtiesContext
public abstract class AbstractAnnotationInclude extends AbstractTransactionalJUnit4SpringContextTests{
}
