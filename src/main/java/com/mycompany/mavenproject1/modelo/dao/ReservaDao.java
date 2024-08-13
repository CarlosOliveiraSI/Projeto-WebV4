/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.entidade.Reserva;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservaDao extends GenericoDAO<Reserva> {

    public void salvar(Reserva r) {
        String insert = "INSERT INTO Reserva (cliente_id, mesa_id, data, horario) VALUES (?, ?, ?, ?)";
        save(insert, r.getClienteId(), r.getMesaId(), r.getData(), r.getHorario());
    }

    public void alterar(Reserva r) {
        String update = "UPDATE Reserva SET cliente_id = ?, mesa_id = ?, data = ?, horario = ? WHERE id = ?";
        save(update, r.getClienteId(), r.getMesaId(), r.getData(), r.getHorario(), r.getId());
    }

    public void excluir(Reserva r) {
        String delete = "DELETE FROM Reserva WHERE id = ?";
        save(delete, r.getId());
    }

    public Reserva buscarPorId(int id) {
        String select = "SELECT * FROM Reserva WHERE id = ?";
        return buscarPorId(select, new ReservaRowMapper(), id);
    }

    public List<Reserva> buscarTodos() {
        String select = "SELECT * FROM Reserva";
        return buscarTodos(select, new ReservaRowMapper());
    }

    public static class ReservaRowMapper implements RowMapper<Reserva> {
        @Override
        public Reserva mapRow(ResultSet rs) throws SQLException {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id"));
            reserva.setClienteId(rs.getInt("cliente_id"));
            reserva.setMesaId(rs.getInt("mesa_id"));
            reserva.setData(rs.getDate("data"));
            reserva.setHorario(rs.getString("horario"));
            return reserva;
        }
    }
}
