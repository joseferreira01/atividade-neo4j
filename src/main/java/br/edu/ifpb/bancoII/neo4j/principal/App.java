/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bancoII.neo4j.principal;

import br.edu.ifpb.bancoII.neo4j.entity.Publicacao;
import br.edu.ifpb.bancoII.neo4j.entity.Usuario;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 *
 * @author jose2
 */
public class App {

    public static void main(String[] args) {
        // Caminho onde sera os dados no neo4j
        String urlBD = "./banco.db";
        File flle = new File(urlBD);

        GraphDatabaseService graph = new GraphDatabaseFactory()
                .newEmbeddedDatabase(flle);

        try (Transaction tc = graph.beginTx()) {

            // --------------------- Criando Usuarios --------------------------
            Usuario u1 = new Usuario("123", "Maria", 18);
            Usuario u2 = new Usuario("1234", "Joao", 20);
            Usuario u3 = new Usuario("12345", "Pedro", 32);
            Usuario u4 = new Usuario("12346", "Ana", 65);

            // ---------------------- Salvando Usuarios -----------------------------------------------
            
            criarUsuario(graph, u1);
            criarUsuario(graph, u2);
            criarUsuario(graph, u3);
            criarUsuario(graph, u4);
            // Fazendo a criaçao das publicaçoes
            
            Publicacao p1 = new Publicacao(1, new Date(), "Puplicaçao 1");
            Publicacao p2 = new Publicacao(2, new Date(), "Puplicaçao 2");
            Publicacao p3 = new Publicacao(3, new Date(), "Puplicaçao 3");
            Publicacao p4 = new Publicacao(4, new Date(), "Puplicaçao 4");
            
            System.err.println("================================================");
            
            // --------------------- Salvando as publicaçoe no banco -----------
            criarPublicacao(graph, p1);
            criarPublicacao(graph, p2);
            criarPublicacao(graph, p3);
            criarPublicacao(graph, p4);
            
            System.err.println("================================================");
            /* --------------------- Criando relacionamentos de amizade 
            entre os usuarios
             */
            criarAmizade(graph, u1, u3);
            criarAmizade(graph, u3, u2);
            criarAmizade(graph, u4, u3);
            criarAmizade(graph, u3, u4);
            
            /* --------------------- Criando relaçoes em publicaçoes
              e os usuarios que as publicaram ----------------------------------
             */
            usuarioPublicar(graph, u1, p3);
            usuarioPublicar(graph, u2, p4);
            usuarioPublicar(graph, u4, p2);
            usuarioPublicar(graph, u3, p1);

            graph.schema();
            tc.success();
        } finally {
            graph.shutdown();

        }
    }

    private static void criarUsuario(GraphDatabaseService graph, Usuario u) {
        // ---------------------------- usuario 1 ----------------------------
        Node no1 = graph.createNode(Label.label("usuario"));
        no1.setProperty("cpf", u.getCPF());
        no1.setProperty("nome", u.getNome());
        no1.setProperty("idade", u.getIdade());
        Node no2 = graph.createNode(Label.label("usuario"));
        Node result = graph.findNode(Label.label("usuario"), "cpf", u.getCPF());
        System.err.println("Usuario salvo" + result.getAllProperties());

    }

    private static void criarPublicacao(GraphDatabaseService graph, Publicacao p) {
        Node no = graph.createNode(Label.label("publicacao"));
        no.setProperty("ID", p.getID());
        no.setProperty("data", p.getData().toString());
        no.setProperty("texto", p.getTexto());
          Node result = graph.findNode(Label.label("publicacao"), "ID", p.getID());
        System.err.println("publicaçao salvo" + result.getAllProperties());
        
    }

    private static void criarAmizade(GraphDatabaseService graph, Usuario eu, Usuario meuAmigo) {
        Node no1 = graph.findNode(Label.label("usuario"), "cpf", eu.getCPF());
        Node no2 = graph.findNode(Label.label("usuario"), "cpf", meuAmigo.getCPF());
        Relationship rel = no1.createRelationshipTo(no2, RelTipe.AMIGO);
         System.err.println("relacionamento de amigo"+no1.getAllProperties());
    }

    private static void usuarioPublicar(GraphDatabaseService graph, Usuario publicador, Publicacao p) {
        Node no1 = graph.findNode(Label.label("usuario"), "cpf", publicador.getCPF());
        Node no2 = graph.findNode(Label.label("publicacao"), "ID", p.getID());
        Relationship rel = no1.createRelationshipTo(no2, RelTipe.PUBLICA);
        rel.setProperty("EM", LocalDateTime.now().toString());
         Node result = graph.findNode(Label.label("publicacao"), "ID", p.getID());
        System.err.println("publicacao salvo" + result.getAllProperties());
        System.err.println("relacionamento com publicaçao "+no1.getAllProperties());
    }
}
