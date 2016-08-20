package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import view.MDCPanel;

public class Takt_DB{
	private String		dataBase	= "java_doitdatenbank";
	private String		table;
	private String		user		= "root";
	private String		pass		= "root";
	private String		dbURL		= "127.0.0.1";
	private String		driver		= "com.mysql.jdbc.Driver";
	private String		url			= "jdbc:mysql://" + dbURL + "/" + this.dataBase;
	private String		error		= "Es ist ein Fehler mit der Datenbank aufgetreten!\n";
	
	// private boolean connectable = false;
	private Connection	connection	= null;
	private Statement	statement	= null;

	private void open() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, error + ex.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectComboInhalt(DefaultComboBoxModel<String> y, String tabelle, String spalte) {
		table= tabelle;
		open();
		y.addElement("bitte wählen");
		ResultSet rs;

		String sql = " SELECT DISTINCT " + spalte + " FROM " + table;

		try {
			rs = statement.executeQuery(sql);

			while (rs.next()) {

				y.addElement(rs.getString(spalte));
		

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

}
