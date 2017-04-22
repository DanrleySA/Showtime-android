package danrleysa.com.showtime.database;

/**
 * Created by Danrley on 22/04/2017.
 */

public class ScriptSQL  {

    public static String getCreateTableEvento() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS EVENTO ( " +
                "_id_evento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "id_usuario LONG NOT NULL, " +
                "descricao TEXT NOT NULL, "+
                "data DATE NOT NULL, "+
                "lotacao INTEGER NOT NULL, "+
                "FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) );");
        return sqlBuilder.toString();
    }

    public static String getCreateTableUsuario() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS USUARIO ( " +
                "_id_usuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL, "+
                "senha VARCHAR (20) NOT NULL );");
        return sqlBuilder.toString();
    }

    public static String getCreateTableInscricao(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS ( " +
                "_id_usuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome VARCHAR(60) NOT NULL, " +
                "email VARCHAR(255), " +
                "senha VARCHAR(32), " +
                "id_evento INTEGER NOT NULL, " +
                "id_usuario INTEGER NOT NULL, " +
                "FOREIGN KEY(id_evento) REFERENCES evento(id_evento)," +
                "FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) );");
        return sqlBuilder.toString();
    }
}

