package by.sivko.pinserver.models.dao;

import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.dao.pin.PinDao;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.sivko.pinserver.models.dao.PinRepositoryTest.LINK_DATA_SET;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@DatabaseSetup(LINK_DATA_SET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LINK_DATA_SET)
@DbUnitConfiguration(dataSetLoader = PinDataSetLoader.class)
public class PinRepositoryTest extends AbstractAnnotationInclude {

    @Autowired
    PinDao pinDao;

    public static final String LINK_DATA_SET = "classpath:datasets/pin-table.xml";



    @Test
    public void findOne() throws Exception {
        Pin pin = pinDao.findOne(100000L);
        assertThat(pin.getEmail(), is("EMAIL_1"));
        assertThat(pin.getCode(), is(1231));
    }

    @Test
    public void save() throws Exception {
        Pin pin = pinDao.save(new Pin("save_pin_test", 200L, "api_token"));
        Long id = pin.getId();
        Pin pinFromDB = pinDao.findOne(id);
        assertThat(pinFromDB.getEmail(), is("save_pin_test"));
        org.junit.Assert.assertTrue(pinFromDB.getCode() >= 1000L && pinFromDB.getCode() <= 9999L);
        System.out.println("CODE------------" + pinFromDB.getCode());
    }

    @Test
    public void findAll() throws Exception {
        assertThat(pinDao.getAll().size(), is(4));
    }

    @Test
    public void getPinByApiToken() throws Exception {
        assertThat(pinDao.getPinByApiToken("API_TOKEN_1").getEmail(), is("EMAIL_1"));
    }

    @Test
    public void removeExpiredPins() throws Exception {
        List<Pin> pinList = pinDao.getAll();
        for (Pin pin : pinList) {
            System.out.println(pin);
        }
        assertThat(pinDao.removeExpiredPins(), is(4));
    }

}