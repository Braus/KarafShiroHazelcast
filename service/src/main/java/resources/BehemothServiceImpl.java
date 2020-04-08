package resources;

import model.Behemoth;
import model.BehemothService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.osgi.service.component.annotations.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Component
public class BehemothServiceImpl implements BehemothService {
    private Map<Integer, Behemoth> behemoths = new HashMap<>();

public BehemothServiceImpl(){
    behemoths.put(1, new Behemoth(1L, "Yeel"));
    behemoths.put(2, new Behemoth(2L, "Yaal"));
}

    public Behemoth getById(Integer id) {
        return behemoths.get(id);
    }

    public Collection<Behemoth> getAll() {
        return behemoths.values();
    }

}
