package struts2.common.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;


@NoRepositoryBean
public class BaseRepository<T> {

    @Autowired
    protected HibernateTemplate template;
    @Autowired
    protected SessionFactory sessionFactory;

    protected Session session;

    protected Logger logger = LogManager.getLogger("RepositoryLOG");


    protected List<T> getList(Class<T> clazz, int page, int size) {
        int firstResult = (page - 1) * size;
        session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(size);
        List<T> list = query.list();
        logger.info("====Found " + query.list() + " in " + clazz.getSimpleName() + " with page =" + page + " and pageSize =" + size);
        return list;
    }

    protected List<T> getAll(Class<T> clazz) {
        session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        List<T> list = query.list();
        return list;
    }

    protected SearchResult<T> search(Class<T> clazz, String key, String columnSearch, int page, int size) {
        session = getSession();
        int firstResult = (page - 1) * size;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        CriteriaQuery<Long> countCriteriaQuery = builder.createQuery(Long.class);
        Root<T> root1 = criteriaQuery.from(clazz);
        Root<T> root2 = countCriteriaQuery.from(clazz);

        criteriaQuery.select(root1);
        countCriteriaQuery.select(builder.count(root2));

        Predicate p1 = builder.like(root1.get(columnSearch), key);
        Predicate p2 = builder.like(root2.get(columnSearch), key);

        criteriaQuery.where(p1);
        countCriteriaQuery.where(p2);

        Query<T> query = session.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(size);
        List<T> list = query.list();

        int totalResults = (int) (long) session.createQuery(countCriteriaQuery).getSingleResult();

        SearchResult<T> searchResult = new SearchResult<>();
        searchResult.setResults(list);
        searchResult.setTotalResults(totalResults);
        searchResult.setTotalPages(BaseUtils.countTotalPage(totalResults, size));
        return searchResult;
    }

    protected SearchResult<T> searchByJoinColumn(Class<T> clazz, String joinColumn, String key, String columnSearch, int page, int size) {
        session = getSession();
        int firstResult = (page - 1) * size;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        CriteriaQuery<Long> countCriteriaQuery = builder.createQuery(Long.class);

        Root<T> root1 = criteriaQuery.from(clazz);
        Root<T> root2 = countCriteriaQuery.from(clazz);

        criteriaQuery.select(root1);
        countCriteriaQuery.select(builder.count(root2));

        Path path1 = root1.get(joinColumn).get(columnSearch);
        Path path2 = root2.get(joinColumn).get(columnSearch);

        Predicate p1 = builder.like(path1, key);
        Predicate p2 = builder.like(path2, key);

        criteriaQuery.where(p1);
        countCriteriaQuery.where(p2);

        Query<T> query = session.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(size);
        List<T> list = query.list();

        int totalResults = (int) (long) session.createQuery(countCriteriaQuery).getSingleResult();

        SearchResult<T> searchResult = new SearchResult<>();
        searchResult.setResults(list);
        searchResult.setTotalResults(totalResults);
        searchResult.setTotalPages(BaseUtils.countTotalPage(totalResults, size));
        return searchResult;
    }


    protected int getTotalPage(String className, int pageSize) {
        int numberRecord = countNumberRecords(className);
        return BaseUtils.countTotalPage(numberRecord, pageSize);
    }

    protected int getTotalPageSearch(String className, String key, String columnSearch, int pageSize) {
        int numberRecord = totalResultSearch(className, key, columnSearch);
        return BaseUtils.countTotalPage(numberRecord, pageSize);
    }


    protected int totalResultSearch(String className, String key, String columnSearch) {

        session = getSession();

        String hql = "SELECT COUNT(c) FROM " + className + " c WHERE c." + columnSearch + " like :key";

        Query<Long> query = session.createQuery(hql);

        query.setParameter("key", key);

        return (int) (long) query.getSingleResult();
    }


    protected int countNumberRecords(String className) {

        session = getSession();

        String hql = "SELECT COUNT(c) FROM " + className + " c";

        return (int) (long) session.createQuery(hql).getSingleResult();
    }


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected T getById(Class<T> clazz, Object id) {

        return template.get(clazz, (Serializable) id);
    }

    protected boolean saveOrUpdate(T bean) {
        try {

            template.saveOrUpdate(bean);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean delete(Class<T> clazz, Object id) {
        try {

            T bean = template.load(clazz, (Serializable) id);

            template.delete(bean);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}
