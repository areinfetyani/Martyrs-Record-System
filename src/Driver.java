import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	static SortedDLL doublylist = new SortedDLL();
	Stage locationStage, martyrsStage, statStage, saveStage, viewStage;
	TextField selecttf, selecttxt, tf3;
	Label found;
	Button showStatistics;

	@Override
	public void start(Stage primaryStage) {
		Font font = Font.font("Comic Sans MS", FontWeight.MEDIUM, FontPosture.REGULAR, 17);
		Image img = new Image("martyrs.jpg");
		ImageView iv = new ImageView(img);
		Image imglogo = new Image("logo.png");
		ImageView ivlogo = new ImageView(imglogo);
		ivlogo.setFitWidth(75);
		ivlogo.setFitHeight(70);
		iv.setFitWidth(650);
		iv.setFitHeight(500);
		Label logo = new Label("The Israeli Information Center for Human Rights in the Occupied Territories");
		logo.setWrapText(true);
		logo.setPrefWidth(250);
		logo.setGraphic(ivlogo);
		logo.setStyle("-fx-text-fill:WHITE;");
		logo.setPrefHeight(120);
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color:BLACK;");
		Button btfile = new Button("Load Martyrs File");
		Button location = new Button("Location screen");
		Button martyrs = new Button("Martyrs Screen");
		Button stat = new Button("Statistics Screen");
		Button save = new Button("Save Screen");
		Button view = new Button("View Summary");
		location.setFocusTraversable(false);
		location.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		location.setPrefWidth(240);
		location.setFont(font);
		location.setOnMouseEntered(e -> {
			location.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		location.setOnMouseExited(e -> {
			location.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		location.setOnAction(e -> {
			locationStage = new Stage();
			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(10, 10, 10, 10));
			GridPane gp = new GridPane();
			Label insert = new Label("Insert new location record:");
			Label update = new Label("Update or delete a location record:");
			Label search = new Label("Search for a location record:");
			TextField tf1 = new TextField();
			TextField tf2 = new TextField();
			tf3 = new TextField();
			TextField updtf = new TextField();
			Button in = new Button("Insert");
			in.setOnAction(a -> {
				if (tf1.getText() != "")
					doublylist.add(tf1.getText());
			});
			Label msg = new Label("");
			HBox updatefield = new HBox(5);
			Button upd = new Button("Update");
			Button dlt = new Button("Delete");
			dlt.setOnAction(a -> {
				if (tf2.getText() != "" && doublylist.getNode(tf2.getText()) != null) {
					doublylist.remove(tf2.getText());
					msg.setText("city " + tf2.getText() + " deleted.");
				} else {
					msg.setText("city " + tf2.getText() + " not in list.");
				}
			});
			upd.setOnAction(a -> {
				if (tf2.getText() != "" && updtf.getText() != "" && doublylist.getNode(tf2.getText()) != null) {
					SortedLL l = doublylist.getNode(tf2.getText()).getList();
					doublylist.add(updtf.getText());
					doublylist.getNode(updtf.getText()).setList(l);
					doublylist.remove(tf2.getText());
					msg.setText("city " + tf2.getText() + " updated.");
				} else {
					msg.setText("city " + tf2.getText() + " not in list.");
				}
			});
			Button srch = new Button("Search");
			found = new Label("");
			srch.setOnAction(a -> {
				if (tf3.getText() != "" && doublylist.getNode(tf3.getText()) != null) {

					found.setText("city found");
					martyrs.fire();
					selecttf.setText(tf3.getText());

				} else {
					found.setText("city not found");
				}
				stat.setDisable(false);
			});
			updatefield.getChildren().addAll(upd, updtf);

			Image im = new Image("map.jpg");
			ImageView imv = new ImageView(im);
			imv.setFitWidth(600);
			imv.setFitHeight(300);
			gp.setVgap(5);
			gp.setHgap(50);
			vbox.getChildren().addAll(gp, imv);
			vbox.setAlignment(Pos.CENTER);
			gp.setAlignment(Pos.TOP_CENTER);
			gp.add(insert, 0, 0);
			gp.add(tf1, 0, 1);
			gp.add(in, 0, 2);
			gp.add(update, 1, 0);
			gp.add(tf2, 1, 1);
			gp.add(updatefield, 1, 2);
			gp.add(dlt, 1, 3);
			gp.add(msg, 1, 4);
			gp.add(search, 2, 0);
			gp.add(tf3, 2, 1);
			gp.add(srch, 2, 2);
			gp.add(found, 2, 3);
			Scene scene = new Scene(vbox, 800, 550);
			locationStage.setScene(scene);
			locationStage.show();

		});
		martyrs.setFocusTraversable(false);
		martyrs.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		martyrs.setPrefWidth(240);
		martyrs.setFont(font);
		martyrs.setOnMouseEntered(e -> {
			martyrs.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		martyrs.setOnMouseExited(e -> {
			martyrs.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		martyrs.setOnAction(e -> {
			martyrsStage = new Stage();
			Label select = new Label("Select Location: ");
			selecttf = new TextField();

			Label name = new Label("Name: ");
			Label age = new Label("Age: ");
			Label date = new Label("Date of Death: ");
			Label gender = new Label("Gender: ");
			Label status = new Label("Status: ");
			Label in = new Label("Insert new Martyr: ");
			Label msg = new Label("");
			TextField intf1 = new TextField();
			TextField intf2 = new TextField();
			TextField intf3 = new TextField();
			TextField intf4 = new TextField();
			TextField intf5 = new TextField();
			Button insertmar = new Button("Insert");
			insertmar.setOnAction(a -> {
				if (selecttf.getText() != "" && intf1.getText() != "" && intf2.getText() != "" && intf3.getText() != ""
				        && intf4.getText() != "" && intf5.getText() != "") {
					if (doublylist.getNode(selecttf.getText()) != null) {
						String marName = intf1.getText();
						int marAge = Integer.parseInt(intf2.getText());
						String[] marDate = intf3.getText().split("/");
						int marYear = Integer.parseInt(marDate[2]) - 1900;
						int marMonth = Integer.parseInt(marDate[0]) - 1;
						int marDay = Integer.parseInt(marDate[1]);
						Date marDateOfDeath = new Date(marYear, marMonth, marDay);
						char marGender = intf4.getText().charAt(0);
						String marStat = intf5.getText();
						doublylist.getNode(selecttf.getText()).setList(doublylist.getNode(selecttf.getText()).getList(),
						        new Martyr(marName, marAge, marDateOfDeath, marGender, marStat));
					} else {
						msg.setText("City not found!");
					}
				} else {
					msg.setText("All fields must be filled!");
				}
			});

			Label selectmar = new Label("Choose a martyr to udpate info: ");
			TextField updtf1 = new TextField();
			TextField updtf2 = new TextField();
			TextField updtf3 = new TextField();
			TextField updtf4 = new TextField();
			TextField updtf5 = new TextField();
			TextField selectmartf = new TextField("Write martyr's name");
			selectmartf.setOnMouseClicked(a -> {
				selectmartf.setText(null);
			});
			TextField selectdatetf = new TextField("Write martyr's dof");
			selectdatetf.setOnMouseClicked(a -> {
				selectdatetf.setText(null);
			});
			Label msg2 = new Label("");
			Button show = new Button("Show info");
			show.setOnAction(a -> {
				try {
				if (selectmartf.getText() != null && selectdatetf.getText() != null && selecttf.getText() != null) {
					Node martyrnode = doublylist.getNode(selecttf.getText()).getList().getNode(selectmartf.getText());
					if (martyrnode != null) {
						Martyr martyr = doublylist.getNode(selecttf.getText()).getList().getNode(selectmartf.getText())
						        .getElement();
						for (int i = 0; i < doublylist.getNode(selecttf.getText()).getList().count; i++) {
							Martyr martyr2 = doublylist.getNode(selecttf.getText()).getList().getNode(i).getElement();

							if (martyr.getName().equals(martyr2.getName())
							        && martyr2.getDateOfDeath().compareTo(new Date(selectdatetf.getText()))==0) {
								updtf1.setText(martyr2.getName());
								updtf2.setText(martyr2.getAge() + "");
								String dateod = martyr2.getDateOfDeath().getMonth() + 1 + "/"
								        + martyr2.getDateOfDeath().getDate() + "/"
								        + (martyr2.getDateOfDeath().getYear() + 1900);
								updtf3.setText(dateod);
								updtf4.setText(martyr2.getGender() + "");
								updtf5.setText(martyr2.getStatus());
								msg2.setText("Martyr found!");
							}
						}
					} else {
						msg2.setText("This martyr is not found!");
					}

				} else {
					msg2.setText("Fill the fields!");
				}
			}catch(Exception ex) {
				msg2.setText("Fill the fields with the required information!");
			}
			});
			Button updmar = new Button("Update");
			updmar.setOnAction(a -> {
				Martyr martyr = doublylist.getNode(selecttf.getText()).getList().getNode(selectmartf.getText())
				        .getElement();
				if (updtf1 != null && updtf2 != null && updtf3 != null && updtf4 != null && updtf5 != null) {
					martyr.setName(updtf1.getText());
					martyr.setAge(Integer.parseInt(updtf2.getText()));
					Date dof = new Date(updtf3.getText());
					martyr.setDateOfDeath(dof);
					martyr.setGender(updtf4.getText().charAt(0));
					martyr.setStatus(updtf5.getText());
				}
			});

			Label updlbl = new Label("Update the record: ");
			GridPane margp = new GridPane();
			margp.setVgap(5);
			margp.setHgap(15);
			margp.add(select, 0, 0);
			margp.add(selecttf, 0, 1);
			margp.add(msg, 0, 2);
			margp.add(name, 1, 1);
			margp.add(age, 1, 2);
			margp.add(date, 1, 3);
			margp.add(gender, 1, 4);
			margp.add(status, 1, 5);
			margp.add(in, 2, 0);
			margp.add(intf1, 2, 1);
			margp.add(intf2, 2, 2);
			margp.add(intf3, 2, 3);
			margp.add(intf4, 2, 4);
			margp.add(intf5, 2, 5);
			margp.add(insertmar, 2, 6);
			margp.add(selectmar, 3, 0);
			margp.add(selectmartf, 3, 1);
			margp.add(selectdatetf, 3, 2);
			margp.add(show, 3, 3);
			margp.add(msg2, 3, 4);
			margp.add(updlbl, 4, 0);
			margp.add(updtf1, 4, 1);
			margp.add(updtf2, 4, 2);
			margp.add(updtf3, 4, 3);
			margp.add(updtf4, 4, 4);
			margp.add(updtf5, 4, 5);
			margp.add(updmar, 4, 6);
			Image marim = new Image("martyrscreen.jpeg");
			ImageView mariv = new ImageView(marim);
			mariv.setFitHeight(300);
			mariv.setFitWidth(600);
			HBox hbsearch = new HBox(5);
			TextField namehere = new TextField("search name");
			TextArea txar = new TextArea();

			namehere.setOnMouseClicked(a -> {
				namehere.setText(null);
				txar.setText("");
			});
			Button search = new Button("Search");
			hbsearch.getChildren().addAll(namehere, search);
			VBox searchvb = new VBox(5);
			txar.setPrefWidth(280);
			search.setOnAction(a -> {
				if (namehere.getText() != null && namehere.getText() != "search name") {
					for (int i = 0; i < doublylist.getNode(selecttf.getText()).getList().count; i++) {
						Martyr martyr = doublylist.getNode(selecttf.getText()).getList().getNode(i).getElement();
						if (martyr.getName().toLowerCase().contains((namehere.getText().toLowerCase()))) {
							txar.setText(txar.getText() + martyr.toString() + "\r\n");
						}
					}
				} else {//brings all names
					for (int i = 0; i < doublylist.getNode(selecttf.getText()).getList().count; i++) {
						Martyr martyr = doublylist.getNode(selecttf.getText()).getList().getNode(i).getElement();
						txar.setText(txar.getText() + martyr.toString() + "\r\n");
					}

				}
			});
			searchvb.getChildren().addAll(hbsearch, txar);
			HBox finalhb = new HBox(15);
			finalhb.getChildren().addAll(margp, searchvb);
			VBox martyrsvb = new VBox(10);
			martyrsvb.setPadding(new Insets(10, 10, 10, 10));
			martyrsvb.getChildren().addAll(finalhb, mariv);
			martyrsvb.setAlignment(Pos.CENTER);
			Scene scene = new Scene(martyrsvb, 1100, 550);
			martyrsStage.setScene(scene);
			martyrsStage.show();
		});

		stat.setFocusTraversable(false);
		stat.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		stat.setPrefWidth(240);
		stat.setFont(font);
		stat.setOnMouseEntered(e -> {
			stat.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		stat.setOnMouseExited(e -> {
			stat.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		stat.setDisable(true);
		stat.setOnAction(e -> {
			statStage = new Stage();
			BorderPane pane = new BorderPane();
			Label select = new Label("Select Location: ");
			selecttxt = new TextField();
			showStatistics = new Button("Show info");
			selecttxt.setText(tf3.getText());
			Label numbyage = new Label("*Nubmer of martyrs by age: ");
			Label numbygender = new Label("*Number of martyrs by gender: ");
			Label avg = new Label("*The average age of martyrs: ");
			Label maxdof = new Label("The date that has the max number of martyrs: ");
			TextArea ans1 = new TextArea();
			ans1.setPrefHeight(200);
			TextArea ans2 = new TextArea();
			ans2.setPrefHeight(50);
			TextArea ans3 = new TextArea();
			ans3.setPrefHeight(20);
			TextArea ans4 = new TextArea();
			ans4.setPrefHeight(20);
			showStatistics.setOnAction(z -> {
				if (selecttxt.getText() != "") {
					int female = 0;
					int male = 0;
					int a = 0;
					int b = 0;
					int c = 0;
					int d = 0;
					int f = 0;
					int g = 0;
					int h = 0;
					int j = 0;
					int k = 0;
					int l = 0;
					int m = 0;
					int n = 0;
					int sum = 0;
					for (int i = 0; i < doublylist.getNode(selecttxt.getText()).getList().count; i++) {
						Martyr mar = doublylist.getNode(selecttxt.getText()).getList().getNode(i).getElement();
						if (mar.getAge() >= 0 && mar.getAge() <= 9) {
							sum += mar.getAge();
							a++;
						} else if (mar.getAge() >= 10 && mar.getAge() <= 19) {
							sum += mar.getAge();
							b++;
						} else if (mar.getAge() >= 20 && mar.getAge() <= 29) {
							sum += mar.getAge();
							c++;
						} else if (mar.getAge() >= 30 && mar.getAge() <= 39) {
							sum += mar.getAge();
							d++;
						} else if (mar.getAge() >= 40 && mar.getAge() <= 49) {
							sum += mar.getAge();
							f++;
						} else if (mar.getAge() >= 50 && mar.getAge() <= 59) {
							sum += mar.getAge();
							g++;
						} else if (mar.getAge() >= 60 && mar.getAge() <= 69) {
							sum += mar.getAge();
							h++;
						} else if (mar.getAge() >= 70 && mar.getAge() <= 79) {
							sum += mar.getAge();
							j++;
						} else if (mar.getAge() >= 80 && mar.getAge() <= 89) {
							sum += mar.getAge();
							k++;
						} else if (mar.getAge() >= 90 && mar.getAge() <= 99) {
							sum += mar.getAge();
							l++;
						} else if (mar.getAge() >= 100 && mar.getAge() <= 109) {
							sum += mar.getAge();
							m++;
						} else if (mar.getAge() >= 110 && mar.getAge() <= 119) {
							sum += mar.getAge();
							n++;
						}
					}
					ans1.setText("0-9 ----> " + a + "\n" + "10-19 ----> " + b + "\n" + "20-29 ----> " + c + "\n"
					        + "30-39 ----> " + d + "\n" + "40-49 ----> " + f + "\n" + "50-9 ----> " + g + "\n"
					        + "60-69 ----> " + h + "\n" + "70-79 ----> " + j + "\n" + "80-89 ----> " + k + "\n"
					        + "90-99 ----> " + l + "\n" + "100-109 ----> " + m + "\n" + "110-119 ----> " + n);
					for (int i = 0; i < doublylist.getNode(selecttxt.getText()).getList().count; i++) {
						Martyr mar = doublylist.getNode(selecttxt.getText()).getList().getNode(i).getElement();
						if (mar.getGender() == 'M')
							male++;
						else if (mar.getGender() == 'F')
							female++;
					}
					ans2.setText("# of females = " + female + "\n" + "# of males = " + male);
					double average = sum * 1.0 / (doublylist.getNode(selecttxt.getText()).getList().count);
					ans3.setText(average + "");
					SortedLL list = doublylist.getNode(selecttxt.getText()).getList();
					Date dofMax = list.getNode(0).getElement().getDateOfDeath();
					Date currDof = list.getNode(0).getElement().getDateOfDeath();
					int maxCounter = 0;
					int currCounter = 0;
					for (int i = 0; i < list.count; i++) {
						if (currDof.equals(list.getNode(i).getElement().getDateOfDeath())) {
							currCounter++;
							if (currCounter > maxCounter) {
								maxCounter = currCounter;
								dofMax = currDof;
							}
						} else {
							currDof = list.getNode(i).getElement().getDateOfDeath();
							currCounter = 1;
						}
					}
					ans4.setText(dofMax + "");
				}
			});
			showStatistics.fire();

			ans1.setEditable(false);
			ans2.setEditable(false);
			ans3.setEditable(false);
			ans4.setEditable(false);

			Button next = new Button(">");
			Button prev = new Button("<");
			HBox hb = new HBox(5);
			hb.getChildren().addAll(select, selecttxt, showStatistics);
			VBox vb = new VBox(5);
			vb.getChildren().addAll(numbyage, ans1, numbygender, ans2, avg, ans3, maxdof, ans4);
			vb.setPadding(new Insets(10, 10, 10, 10));
			pane.setPadding(new Insets(10, 10, 10, 10));
			pane.setTop(hb);
			pane.setCenter(vb);
			pane.setRight(next);
			pane.setLeft(prev);
			pane.setAlignment(next, Pos.CENTER_RIGHT);
			pane.setAlignment(prev, Pos.CENTER_LEFT);
			next.setOnAction(y -> {
				if (selecttxt.getText() != "") {
					selecttxt.setText(doublylist.getNode(selecttxt.getText()).getNext().getLocation());
					showStatistics.fire();
				}

			});
			prev.setOnAction(y -> {
				if (selecttxt.getText() != "") {
					selecttxt.setText(doublylist.getNode(selecttxt.getText()).getPrev().getLocation());
					showStatistics.fire();
				}
			});
			Scene s = new Scene(pane, 900, 500);
			statStage.setScene(s);
			statStage.show();
		});
		save.setFocusTraversable(false);
		save.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		save.setPrefWidth(240);
		save.setFont(font);
		save.setOnMouseEntered(e -> {
			save.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		save.setOnMouseExited(e -> {
			save.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		save.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(primaryStage);
			try (FileWriter output = new FileWriter(f)) {
				if (f.length() == 0) {
					output.append("Name,Age,Event location - District,Date of death,Gender,Status\r\n");
				}
				for (int i = 0; i < doublylist.count; i++) {
					for (int j = 0; j < doublylist.getNode(i).getList().count; j++) {
						Martyr m = doublylist.getNode(i).getList().getNode(j).getElement();
						String dateod = m.getDateOfDeath().getMonth() + 1 + "/" + m.getDateOfDeath().getDate() + "/"
						        + (m.getDateOfDeath().getYear() + 1900);
						try {
							output.append(m.getName() + "," + m.getAge() + "," + doublylist.getNode(i).getLocation()
							        + "," + dateod + "," + m.getGender() + "," + m.getStatus() + "\r\n");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
		});
		btfile.setFocusTraversable(false);
		btfile.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		btfile.setPrefWidth(240);
		btfile.setFont(font);
		btfile.setOnMouseEntered(e -> {
			btfile.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		btfile.setOnMouseExited(e -> {
			btfile.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		VBox vb = new VBox(1);
		vb.getChildren().addAll(btfile, location, martyrs, stat, save, view);
		HBox hb = new HBox();
		hb.getChildren().addAll(vb, iv);
		bp.setTop(logo);
		bp.setCenter(hb);
		btfile.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(primaryStage);
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts[2] == "") {
						parts[2] = "Unknown Location";
					}
					doublylist.add(parts[2]);
				}
			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(",", -1);
					if (parts[1] == "") {
						parts[1] = "0";
					}
					if (parts[0] == "") {
						parts[0] = "Name unknown to B'Tselem";
					}
					String[] datedetails = parts[3].split("/", -1);
					if (parts[3] == "" || !parts[3].contains("/") || datedetails.length != 3) {
						parts[3] = "1/1/1990";
					}
					if (datedetails.length == 3) {
						if (datedetails[0] == "" || datedetails[1] == "" || datedetails[2] == "") {
							parts[3] = "1/1/1990";
						}
					}
					if (parts[4] == "") {
						parts[4] = "NA";
					}
					if (parts[5] == "") {
						parts[5] = "Status unknown";
					}
					if (parts[2] == "") {
						parts[2] = "Unknown Location";
					}
					String name = parts[0];
					int age = Integer.parseInt(parts[1]);
					String city = parts[2];
					Date date = new Date(parts[3]);
					Character gender = parts[4].charAt(0);
					String status = parts[5];
					doublylist.getNode(city).setList(doublylist.getNode(city).getList(),
					        (new Martyr(name, age, date, gender, status)));

				}
			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
		});
		view.setFocusTraversable(false);
		view.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		view.setPrefWidth(240);
		view.setFont(font);
		view.setOnMouseEntered(e -> {
			view.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		view.setOnMouseExited(e -> {
			view.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		view.setOnAction(e -> {
			viewStage = new Stage();
			Label summary = new Label("Summary");
			TableView<Information> table = new TableView<>();
			ObservableList<Information> data = FXCollections.observableArrayList();
			table.setItems(data);
			TableColumn loc = new TableColumn("Location");
			location.setMinWidth(100);
			loc.setCellValueFactory(new PropertyValueFactory<Information, String>("location"));
			TableColumn age1 = new TableColumn("Age<18");
			age1.setMinWidth(70);
			age1.setCellValueFactory(new PropertyValueFactory<Information, Integer>("age1"));
			TableColumn age2 = new TableColumn("18<=Age<=50");
			age2.setMinWidth(100);
			age2.setCellValueFactory(new PropertyValueFactory<Information, Integer>("age2"));
			TableColumn age3 = new TableColumn("Age>50");
			age3.setMinWidth(70);
			age3.setCellValueFactory(new PropertyValueFactory<Information, Integer>("age3"));

			TableColumn avg = new TableColumn("Average Age");
			avg.setMinWidth(70);
			avg.setCellValueFactory(new PropertyValueFactory<Information, Double>("avg"));
			TableColumn females = new TableColumn("Females");
			females.setMinWidth(70);
			females.setCellValueFactory(new PropertyValueFactory<Information, Integer>("females"));
			TableColumn males = new TableColumn("Males");
			males.setMinWidth(70);
			males.setCellValueFactory(new PropertyValueFactory<Information, Integer>("males"));
			TableColumn married = new TableColumn("Married");
			married.setMinWidth(70);
			married.setCellValueFactory(new PropertyValueFactory<Information, Integer>("married"));
			TableColumn single = new TableColumn("Single");
			single.setMinWidth(70);
			single.setCellValueFactory(new PropertyValueFactory<Information, Integer>("single"));
			TableColumn dof = new TableColumn("Date of max #of deaths");
			dof.setMinWidth(170);
			dof.setCellValueFactory(new PropertyValueFactory<Information, Date>("date"));
			table.getColumns().addAll(loc, age1, age2, age3, avg, females, males, married, single, dof);
			for (int i = 0; i < doublylist.count; i++) {
				String l = doublylist.getNode(i).getLocation();
				data.add(new Information(l));
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			pane.getChildren().addAll(summary, table);
			Scene s = new Scene(pane, 960, 600);
			viewStage.setScene(s);
			viewStage.show();
		});
		Scene s = new Scene(bp, 1100, 650);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public class Information {
		private String location;
		private int age1;
		private int age2;
		private int age3;
		private double avg;
		private int females;
		private int males;
		private int married;
		private int single;
		private Date date;

		public Information(String location) {
			this.location = location;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public int getAge1() {
			SortedLL list = doublylist.getNode(location).getList();
			int age1Counter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getAge() < 18) {
					age1Counter++;
				}
			}
			this.age1 = age1Counter;
			return age1;
		}

		public void setAge1(int age1) {
			this.age1 = age1;
		}

		public int getAge2() {
			SortedLL list = doublylist.getNode(location).getList();
			int age2Counter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getAge() >= 18 && list.getNode(i).getElement().getAge() <= 50) {
					age2Counter++;
				}
			}
			this.age2 = age2Counter;
			return age2;
		}

		public void setAge2(int age2) {
			this.age2 = age2;
		}

		public int getAge3() {
			SortedLL list = doublylist.getNode(location).getList();
			int age3Counter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getAge() > 50) {
					age3Counter++;
				}
			}
			this.age3 = age3Counter;
			return age3;
		}

		public void setAge3(int age3) {
			this.age3 = age3;
		}

		public double getAvg() {
			SortedLL list = doublylist.getNode(location).getList();
			int sum = 0;
			for (int i = 0; i < list.count; i++) {
				sum += list.getNode(i).getElement().getAge();
			}
			this.avg = (sum * 1.0) / list.count;
			return avg;
		}

		public void setAgv(double avg) {
			this.avg = avg;
		}

		public int getFemales() {
			SortedLL list = doublylist.getNode(location).getList();
			int femalesCounter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getGender() == 'F') {
					femalesCounter++;
				}
			}
			this.females = femalesCounter;
			return females;
		}

		public void setFemales(int females) {
			this.females = females;
		}

		public int getMales() {
			SortedLL list = doublylist.getNode(location).getList();
			int malesCounter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getGender() == 'M') {
					malesCounter++;
				}
			}
			this.males = malesCounter;
			return males;
		}

		public void setMales(int males) {
			this.males = males;
		}

		public int getMarried() {
			SortedLL list = doublylist.getNode(location).getList();
			int marriedCounter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getStatus().equals("Married")) {
					marriedCounter++;
				}
			}
			this.married = marriedCounter;
			return married;
		}

		public void setMarried(int married) {
			this.married = married;
		}

		public int getSingle() {
			SortedLL list = doublylist.getNode(location).getList();
			int singleCounter = 0;
			for (int i = 0; i < list.count; i++) {
				if (list.getNode(i).getElement().getStatus().equals("Single")) {
					singleCounter++;
				}
			}
			this.single = singleCounter;
			return single;
		}

		public void setSingle(int single) {
			this.single = single;
		}

		public Date getDate() {
			SortedLL list = doublylist.getNode(location).getList();
			Date dofMax = list.getNode(0).getElement().getDateOfDeath();
			Date currDof = list.getNode(0).getElement().getDateOfDeath();
			int maxCounter = 0;
			int currCounter = 0;
			for (int i = 0; i < list.count; i++) {
				if (currDof.equals(list.getNode(i).getElement().getDateOfDeath())) {
					currCounter++;
					if (currCounter > maxCounter) {
						maxCounter = currCounter;
						dofMax = currDof;
					}
				} else {
					currDof = list.getNode(i).getElement().getDateOfDeath();
					currCounter = 1;
				}
			}
			return dofMax;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
