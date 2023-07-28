/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {

    private conectaDAO conexao;
    private ArrayList<ProdutosDTO> listagem;

    public ProdutosDAO(conectaDAO conexao) {

        this.conexao = conexao;
        this.listagem = new ArrayList<>();
    }

    public ProdutosDAO() {

    }

    public boolean cadastrarProduto(ProdutosDTO produto) {

        try {

            conexao.connectDB();
            Connection conn = conexao.getConexao();

            PreparedStatement st = conn.prepareStatement("INSERT INTO produtos"
                    + " (nome, valor, status) VALUES (?,?,?)");

            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());

            int status = st.executeUpdate();
            st.close();

            conexao.desconectarDB();
            return status > 0;

        } catch (Exception e) {

            System.out.println("Erro ao conectar: " + e.getMessage());

            return false;

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";

        try {

            conexao.connectDB();
            Connection conn = conexao.getConexao();

            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            listagem.clear();

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }

            st.close();
            conexao.desconectarDB();
            return listagem;

        } catch (SQLException ex) {

            System.out.println("Erro ao pesquisar: " + ex.getMessage());

            return null;
        }
    }
}
