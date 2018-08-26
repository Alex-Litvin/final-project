package ua.training.model.dao;

import ua.training.model.dao.implementation.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao getUserDao();
    public abstract UniversityDao getUniversityDao();
    public abstract SpecialityDao getSpecialityDao();
    public abstract SubjectDao getSubjectDao();
    public abstract ExamDao getExamDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
