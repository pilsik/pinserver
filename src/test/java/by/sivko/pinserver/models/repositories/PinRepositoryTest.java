package by.sivko.pinserver.models.repositories;

import by.sivko.pinserver.models.Pin;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static by.sivko.pinserver.models.repositories.PinRepositoryTest.LINK_DATA_SET;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@DatabaseSetup(LINK_DATA_SET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LINK_DATA_SET)
public class PinRepositoryTest extends AbstractAnnotationInclude {

    @Autowired
    PinRepository pinRepository;

    public static final String LINK_DATA_SET = "classpath:datasets/pin-table.xml";

    @Test
    public void findOne() throws Exception {
        Optional<Pin> pin = pinRepository.findOne(100000L);
        assertThat(pin.isPresent(), equalTo(true));
        assertThat(pin.get().getEmail(), is("EMAIL_1"));
        assertThat(pin.get().getCode(), is(1231));
    }

    @Test
    public void save() throws Exception {
        Pin pin = pinRepository.save(new Pin("save_pin_test",200L,"api_token"));
        Long id = pin.getId();
        Optional<Pin> pinFromDB = pinRepository.findOne(id);
        assertThat(pinFromDB.isPresent(), equalTo(true));
        assertThat(pinFromDB.get().getEmail(), is("save_pin_test"));
        org.junit.Assert.assertTrue(pinFromDB.get().getCode() >= 1000L && pinFromDB.get().getCode() <= 9999L);
        System.out.println("CODE------------" + pinFromDB.get().getCode());
    }

    @Test
    public void findAll() throws Exception {
        assertThat(pinRepository.findAll().size(), is(4));
    }

}