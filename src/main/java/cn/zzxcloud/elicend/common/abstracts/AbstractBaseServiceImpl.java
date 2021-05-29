package cn.zzxcloud.elicend.common.abstracts;

import cn.zzxcloud.elicend.common.persistence.BaseEntity;
import cn.zzxcloud.elicend.common.persistence.BaseMapper;
import cn.zzxcloud.elicend.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractBaseServiceImpl<T extends BaseEntity,M extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    protected M mapper;

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public T getById(int id) {
        return mapper.getById(id);
    }

    @Override
    public void update(T entity) {
        mapper.update(entity);
    }

    @Override
    public int count(T entity) {
        return mapper.count(entity);
    }
}
