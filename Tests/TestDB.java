import Definitions.Physician;
import java.util.ArrayList;
import java.util.Arrays;
import org.DatabaseController;
import org.DatabaseEditor;
import org.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Evan on 4/8/2017.
 */
public class TestDB {

  DatabaseController dbc = new DatabaseController("org.apache.derby.jdbc.EmbeddedDriver",
      "jdbc:derby:testDB;create=true");
  DatabaseEditor dbe = new DatabaseEditor(dbc);

  @Test
  void testMakeDB() {
    dbc.send_Command(
        "CREATE TABLE Physician(PID int, First_Name VARCHAR(25) NOT NULL, Last_Name VARCHAR(25) NOT NULL,Title VARCHAR(25) NOT NULL,CONSTRAINT pk_physician PRIMARY KEY (PID));CREATE TABLE Physician_Location(PLID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), PID_po int,PID_ph int, CONSTRAINT pk_physicianl PRIMARY KEY (PLID),CONSTRAINT u_physicianl UNIQUE(PID_po,PID_ph));CREATE TABLE Point(pid int NOT NULL, x int NOT NULL,y int NOT NULL,floor int NOT NULL, Cost int NOT NULL,name varchar(50) ,CONSTRAINT pk_ppoint1 PRIMARY KEY (pid));CREATE TABLE Neighbor(\tnid int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\tPid1 int NOT NULL,\tPid2 int NOT NULL,\tCONSTRAINT pk_neighbors  PRIMARY KEY (nid),CONSTRAINT u_neighbors UNIQUE(Pid1,Pid2));");
  }


  @Test
  void testDB1() {
    Point p1 = new Point(1024, 2048, "", 1, new ArrayList<>(Arrays.asList(2, 3, 5, 4)), 4);
    Point p2 = new Point(2024, 1048, "", 2, new ArrayList<>(Arrays.asList(1, 3, 5)), 4);
    Point p3 = new Point(1024, 1024, "", 3, new ArrayList<>(Arrays.asList(2, 1, 4)), 4);
    Point p4 = new Point(2345, 1048, "", 4, new ArrayList<>(Arrays.asList(1, 3)), 4);
    Point p5 = new Point(2334, 1024, "", 5, new ArrayList<>(Arrays.asList(2, 1)), 4);

    dbe.addPoint(p1);
    dbe.addPoint(p2);
    dbe.addPoint(p3);
    dbe.addPoint(p4);
    dbe.addPoint(p5);
  }

  @Test
  void testDB2() {
    Physician physician1 = new Physician("Evan", "Duffy", "Doctor Professor", 1,
        new ArrayList<>(Arrays.asList(1, 2)));
    Physician physician2 = new Physician("Tom", "White", "Mister Doctor Professor", 2,
        new ArrayList<>(Arrays.asList(3)));
    Physician physician3 = new Physician("Spyros", "Antonatos", "Doctor Professor", 3,
        new ArrayList<>(Arrays.asList(3, 4, 5)));

    dbe.addPhysician(physician1.getID(), physician1.getFirstName(), physician1.getLastName(),
        physician1.getTitle(), physician1.getLocations());
    dbe.addPhysician(physician2.getID(), physician2.getFirstName(), physician2.getLastName(),
        physician2.getTitle(), physician2.getLocations());
    dbe.addPhysician(physician3.getID(), physician3.getFirstName(), physician3.getLastName(),
        physician3.getTitle(), physician3.getLocations());
  }

  @Test
  void testGetFromDB1(){
    Physician physician1 = new Physician("Evan", "Duffy", "Doctor Professor", 1, new ArrayList<>(Arrays.asList(1, 2)));
    Assertions.assertEquals(physician1.getFirstName(), dbe.get_physician(1).getFirstName());
  }

  @Test
  void testGetFromDB2(){
    Physician physician1 = new Physician("Evan", "Duffy", "Doctor Professor", 1, new ArrayList<>(Arrays.asList(1, 2)));
    Assertions.assertEquals(physician1.getLocations().get(1), dbe.get_physician(1).getLocations().get(1));
  }


}
