package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA25 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                
                while (rs.next()) {
                      java.util.Map<String, Object> row = new java.util.LinkedHashMap<>();

                    for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnLabel(i);
                    Object value = rs.getObject(i);

                    if (value instanceof java.sql.Date ||
                        value instanceof java.sql.Time ||
                        value instanceof java.sql.Timestamp) {
                        row.put(columnName, value.toString());
                    }
                                            else {
                            row.put(columnName, value);
                        }
                    }
                    
                    records.add(row);
                }
            }
                

            }
            
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
