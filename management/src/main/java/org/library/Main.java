package org.library;

/*1. Создать триггеры которые будут записывать данные в таблицу logs при обнавлении/удалении
пользователей в БД.
2. Создать функцию которая будет возвращать логин последнего зарегестрированного
пользователя в БД.
3. Создайте схему базы данных для небольшой системы управления библиотекой.
Включите следующие таблицы:
Книги (id, название, автор, год издания)
Читатели (id, имя, email)
Бронирования (id, id_книги, id_читателя, дата_бронирования, дата_возврата)*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/library_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getLastUser() {
        String sql = "SELECT public.get_last_registered_user()";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("Last registered user: " + getLastUser());
    }
}

