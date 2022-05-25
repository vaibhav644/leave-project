package in.leave.springbootmongodb.model;

public enum enumLeave {
	fullLeave,
	halfLeave;


public int fullLeave() {
	if (getStatus( )== fullLeave) {
		return fullLeave();
	}
	return 2;
}

enumLeave getStatus() {
	// TODO Auto-generated method stub
	return null;
}

public int halfLeave() {
	if (getStatus() == halfLeave) {
		return halfLeave();
	}
	return 1;
}
}

