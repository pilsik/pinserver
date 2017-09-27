package by.sivko.pinserver.models.dao.pin;

import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PinDaoImpl extends GenericDaoImpl<Pin, Long> implements PinDao{

    private static final Logger logger = LoggerFactory.getLogger(PinDaoImpl.class);

    @Override
    public Pin getPinByApiToken(String apiToken) {
        return super.em.createNamedQuery("Pin.getPinByApiToken", Pin.class)
                .setParameter("api_token_value", apiToken)
                .getSingleResult();
    }


    @Scheduled(cron = "1 * * * * ?")
    @Transactional
    @Override
    public int removeExpiredPins() {
        logger.info("Выполняется удаление пинов");
        return super.em.createNamedQuery("Pin.removeExpiredPins").executeUpdate();
    }

    @Override
    public List<Pin> getAll() {
        return super.em.createNamedQuery("Pin.getAllPins", Pin.class).getResultList();
    }
}
