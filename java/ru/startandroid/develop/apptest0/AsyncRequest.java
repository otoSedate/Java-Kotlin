package ru.startandroid.develop.apptest0;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class AsyncRequest extends AsyncTask<String, Void, JSONArray> {
    final static String MSSQL_DB = "jdbc:jtds:sqlserver://169.254.108.123:1443:/pubs";
    final static String MSSQL_LOGIN = "hero.daemon@yandex.ru";
    final static String MSSQL_PASS= "";

    public AsyncResponse delegate = null;

    @Override
    protected JSONArray doInBackground(String... query) {
        JSONArray resultSet = new JSONArray();
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = DriverManager.getConnection(MSSQL_DB, MSSQL_LOGIN, MSSQL_PASS);
                if (con != null) {
                    st = con.createStatement();
                    rs = st.executeQuery(query[0]);
                    if (rs != null) {
                        int columnCount = rs.getMetaData().getColumnCount();
                        while (rs.next()) {
                            JSONObject rowObject = new JSONObject();
                            for (int i = 1; i <= columnCount; i++) {
                                rowObject.put(rs.getMetaData().getColumnName(i), ((rs.getString(i)) != null) ? rs.getString(i) : "");
                            }
                            resultSet.put(rowObject);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (st != null) st.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //MainActivity.jsonArray1 = resultSet;
        return resultSet;
        //return null;
        /*ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0, count = resultSet.length(); i < count; i++) {
            try {
                JSONObject jsonObject = resultSet.getJSONObject(i);
                stringArrayList.add(jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/


    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        //super.onPostExecute(jsonArray);
        // TODO:
        delegate.processFinish(jsonArray);
    }

}
