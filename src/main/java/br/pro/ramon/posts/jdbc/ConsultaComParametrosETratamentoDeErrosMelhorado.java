package br.pro.ramon.posts.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaComParametrosETratamentoDeErrosMelhorado {

    public static void main(String[] args) {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/sakila";
            String user = "root";
            String pass = "root";

            String sql = "select * from customer where active = ? and store_id = ? order by first_name, last_name";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, 0); // o primeiro deve ser substituído pelo valor 0
                stmt.setInt(2, 2); // o segundo deve ser substituído pelo valor 2
                try {
                    ResultSet rs = stmt.executeQuery();
                    try {
                        while (rs.next()) {
                            String nome = rs.getString("first_name");
                            String sobrenome = rs.getString("last_name");
                            int ativo = rs.getInt("active");
                            int loja = rs.getInt("store_id");

                            System.out.println(loja + " - " + nome + " " + sobrenome + " - " + ativo);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    stmt.close();
                }
            } finally {
                conn.close();

            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException ex) {
            System.out.println("Erro de banco: " + ex.getMessage());
        }
    }

}
