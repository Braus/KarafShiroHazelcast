package model;

import java.util.Collection;

public interface BehemothService {

    public Behemoth getById(Integer id);

    public Collection<Behemoth> getAll();

}
