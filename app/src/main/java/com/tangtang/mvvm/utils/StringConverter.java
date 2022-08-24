package com.tangtang.mvvm.utils;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Author:
 * Version    V1.0
 * Date:      2019/11/4
 * Description:
 * Modification  History:
 * Date            Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2019/11/4                           1.0               1.0
 * Why & What is modified:
 */
public class StringConverter implements PropertyConverter<List<String>, String> {
  @Override
  public List<String> convertToEntityProperty(String databaseValue) {
    if (databaseValue == null) {
      return null;
    } else {
      return Arrays.asList(databaseValue.split(","));
    }
  }

  @Override
  public String convertToDatabaseValue(List<String> entityProperty) {
    if (entityProperty == null || entityProperty.isEmpty()) {
      return "";
    } else {
      StringBuilder sb = new StringBuilder();
      for (String link : entityProperty) {
        sb.append(link);
        sb.append(",");
      }
      return sb.toString();
    }
  }
}

