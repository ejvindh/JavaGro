package first;
import java.sql.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Dictionary {
	
	public static String[][] lookup(boolean toD, boolean fromD, boolean eks, boolean rev, 
			int doubflag, String lang_longname, String gdd_file_path, String dat_file_path
<<<<<<< HEAD
			, String search_string_orig) throws ClassNotFoundException, SQLException, IOException {
=======
			, String search_string) throws ClassNotFoundException, SQLException, IOException {
>>>>>>> a07388813fc646b77195251e38855273ee66cb5d
		// Hovedopgaven for denne metode er at finde ud af hvilke tabeller der skal kigges i i Gdd-databasen
		// Til slut kaldes metoden getEntries for at finde vektorerne i gdd-databasen for de indtastede søgeord
		// Og getRawEntryText bruges til at udtrække selve opslagene i dat-databasen
		Class.forName("org.sqlite.JDBC");
	    Connection conn = null;
		conn = DriverManager.getConnection("jdbc:sqlite:" + gdd_file_path);
		RandomAccessFile dat_file = new RandomAccessFile(dat_file_path, "r");
		
<<<<<<< HEAD
		String search_string = search_string_orig.toLowerCase()
				.replaceAll("[éèêë]", "e")
				.replaceAll("[àâ]", "a")
				.replaceAll("[ùûü]", "u")
				.replaceAll("[îï]", "i")
				.replaceAll("[ôö]", "o")
				.replaceAll("ÿ", "y")
				.replaceAll("ç", "c")
				.replaceAll("ñ", "n")
				.replaceAll("ß", "ss")
				.replaceAll("œ", "oe");
=======
>>>>>>> a07388813fc646b77195251e38855273ee66cb5d
		String[][] results = new String[2][3];
		ArrayList<String> tables = new ArrayList<String>();
		tables.add("lookup");
		tables.add("reverse");
		tables.add("collocation_lookup");
		if (doubflag > 1) { // hvis ordbogen er tovejs
			
			for (int d_=0; d_<2; d_++) {
				boolean d = d_==0;
				int t_count = 0;
				for (String table: tables) {
					if (table.equals("reverse") || table.equals("collocation_lookup")) {
						d=!d;
					}
					//søg ikke i reverse, hvis databasen ikke indeholder reverse
					if ((table.equals("reverse") && (doubflag==0 || doubflag==2))) {
						results[d_][t_count]="";
					} else {
						ArrayList<int[]> entries=getEntries(conn, search_string, table, d);
						String entrytext=getRawEntryText(dat_file, entries);
						results[d_][t_count]=entrytext;
					}
					t_count++;
				}
			}
		} else { // hvis ordbogen er envejs
			boolean d = true;
			int t_count = 0;
			for (String table: tables) {
				//søg ikke i reverse, hvis databasen ikke indeholder reverse
				if ((table.equals("reverse") && (doubflag==0 || doubflag==2))) {
					results[0][t_count]="";
				} else {
					ArrayList<int[]> entries=getEntries(conn, search_string, table, d);
					String entrytext=getRawEntryText(dat_file, entries);
					results[0][t_count]=entrytext;
				}
				t_count++;
			}
		}
		conn.close();
		dat_file.close();

		for (int x=0; x<=((doubflag^1)&2)/2; x++) { //ligningen giver 1, hvis envejs; ellers 2
			if (results[x][0].equals("")) {
				StringBuffer henv = new StringBuffer();
				henv.append("Opslaget er fundet i");
				if (results[x][1].compareTo("")!=0) {
					henv.append(" 'omvendt søgning'");
					if (results[x][2].compareTo("")!=0) henv.append(" &");
				}
				if (results[x][2].compareTo("")!=0) henv.append(" Eksempelsætninger");
				if (henv.toString().equals("Opslaget er fundet i")) {
					results[x][0]="Opslag ikke fundet";
				} else {
					results[x][0]=henv.toString();
				}
			}
		}
		return results;
		/* results[0][0]=fromdanish_lookup
		 * results[0][1]=fromdanish_reverse
		 * results[0][2]=fromdanish_collocation_lookup (eksempelsætninger)
		 * results[1][0]=todanish_lookup
		 * results[1][1]=todanish_reverse
		 * results[1][2]=todanish_collocation_lookup (eksempelsætninger)
		 */
	}

	public static ArrayList<int[]> getEntries(Connection conn, String search_string, String table, boolean d) 
			throws SQLException {
		// Denne metode bruges til at finde vektorerne i gdd-databasen for de indtastede søgeord

		String[] src_terms = search_string.split(" ");
        ArrayList<String> src_terms_list = new ArrayList<String>();
		for(int i=0; i< src_terms.length; i++){
        	src_terms_list.add(src_terms[i]);
        }
        int fromDanish=0;
        if (d) fromDanish=1; else fromDanish=2;
        boolean first_term = true;
		ArrayList<Integer> entry_ids = new ArrayList<Integer>();
		ArrayList<Integer> term_entry_ids = new ArrayList<Integer>();
		for (String term : src_terms_list) {
			PreparedStatement prepStmt = conn.prepareStatement("select * from " + table + fromDanish + " where word_ like \'" + term + "\';");
			ResultSet rows = prepStmt.executeQuery();
			term_entry_ids.clear();
			while (rows.next()) {
				term_entry_ids.add(rows.getInt(1));
		    }
			if (first_term) {
	    		first_term = false;
	    		entry_ids.addAll(term_entry_ids);
	    	} else {
	    		ArrayList<Integer> a1 = new ArrayList<Integer>(entry_ids);
	    		entry_ids.clear();
	    		for (int a : term_entry_ids) {
	    			if (a1.contains(a)) entry_ids.add(a);
	    		}
	    	}
			rows.close();
	    }
	    ArrayList<int[]> entries = new ArrayList<int[]>();
		for (int entry_id: entry_ids) {
			String selectStatement = "select * from entries" + fromDanish + " where id_ = " + entry_id + ";";
	    	PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
	    	ResultSet rows = prepStmt.executeQuery();
			while (rows.next()) {
	    		entries.add(new int [] {entry_id, rows.getInt(4), rows.getInt(5)});
	    	}
	    }
		return entries;
	}

	public static String getRawEntryText(RandomAccessFile dat_file, ArrayList<int[]> entries) throws IOException {
		ArrayList<String> raw_entries = new ArrayList<String>();
		// Denne metode bruges til at udtrække selve opslagene i dat-databasen

		for (int i=0; i<entries.size(); i++) {
			int entry_id=entries.get(i)[0];
			int offset=entries.get(i)[1];
			int nbyte=entries.get(i)[2];
			dat_file.seek(offset);
			ArrayList<Byte> data = new ArrayList<Byte>();
			for (int n=0; n<nbyte; n++) {
			    data.add(n, dat_file.readByte());
			}
			String raw_entry = Groparser.parse_entry(data, entry_id);
			String filtered_entry = Groparser.filter_entry(raw_entry);
<<<<<<< HEAD
			//TODO:
			System.out.println(filtered_entry + "\n\nhansen\n\n");
=======
>>>>>>> a07388813fc646b77195251e38855273ee66cb5d
			raw_entries.add(filtered_entry);
		}
		StringBuffer raw_entries_collect_to_string = new StringBuffer();
		for (String r:raw_entries) {
			raw_entries_collect_to_string.append(r);
		}
		return raw_entries_collect_to_string.toString();
	}
}
