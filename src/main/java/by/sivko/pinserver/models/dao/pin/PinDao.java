package by.sivko.pinserver.models.dao.pin;

import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.dao.GenericDao;

import java.util.List;

public interface PinDao extends GenericDao<Pin, Long> {
    Pin getPinByApiToken(String apiToken);
    int removeExpiredPins();
    List<Pin> getAll();
}
