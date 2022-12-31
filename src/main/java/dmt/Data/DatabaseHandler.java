// package dmt.Data;

// import java.sql.Date;
// import java.util.ArrayList;

// import dmt.ContentItem;

// import java.security.cert.Certificate;
// import java.sql.*;

// public class DatabaseHandler {
// public ArrayList<ContentItem> getViewedWebcasts2() {
// ArrayList<ContentItem> webcasts = new ArrayList<>();
// try {
// Connection connection =
// DatabaseConnectionManager.getInstance().getConnection();
// PreparedStatement query = connection.prepareStatement(
// "SELECT * FROM Webcast JOIN WebcastViewed ON Webcast.Id = WebcastViewed.Id
// WHERE WebcastViewed.Email = ?");
// query.setString(1, this.email);

// ResultSet result = query.executeQuery();

// while (result.next()) {
// int id = result.getInt("Id");
// String title = result.getString("Title");
// String description = result.getString("Description");
// String speakerName = result.getString("SpeakerName");
// String organization = result.getString("Organization");
// int watchTime = result.getInt("WatchTime");
// Date publicationDate = result.getDate("PublicationDate");
// String url = result.getString("Url");
// int progress = result.getInt("Progress");

// // haal arralist op database.getviewewebcast

// this.webcasts.add(new Webcast(id, publicationDate, url, title, description,
// speakerName, organization,
// progress, watchTime));

// }
// } catch (

// SQLException e) {
// e.printStackTrace();
// }

// return webcasts;
// }
// }
