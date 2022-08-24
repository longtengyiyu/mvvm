package com.tangtang.mvvm.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tangtang.mvvm.entity.DayWeather;

import com.tangtang.mvvm.dao.DayWeatherDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dayWeatherDaoConfig;

    private final DayWeatherDao dayWeatherDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dayWeatherDaoConfig = daoConfigMap.get(DayWeatherDao.class).clone();
        dayWeatherDaoConfig.initIdentityScope(type);

        dayWeatherDao = new DayWeatherDao(dayWeatherDaoConfig, this);

        registerDao(DayWeather.class, dayWeatherDao);
    }
    
    public void clear() {
        dayWeatherDaoConfig.clearIdentityScope();
    }

    public DayWeatherDao getDayWeatherDao() {
        return dayWeatherDao;
    }

}