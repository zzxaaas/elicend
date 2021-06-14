package cn.zzxcloud.elicend.common.persistence;

import java.util.List;

public interface BaseMapper<T extends BaseEntity> {

    /**
     * 查询全部数据
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    int insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(int id);

    /**
     * 根据 ID 查询信息
     * @param id
     * @return
     */
    T getById(int id);

    /**
     * 更新
     *
     * @param entity
     */
    void update(T entity);


    /**
     * 查询总笔数
     * @return
     */
    int count(T entity);
}
