package my.com.homesmartvertablet.model;

public class RoomItem {
	private int roomId;
	private String nameRoom;

	public RoomItem(int roomId, String nameRoom) {
		this.roomId = roomId;
		this.nameRoom = nameRoom;

	}
	public RoomItem(){}
	public int getRoomId() {
		return roomId;

	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;

	}

	public String getNameRoom() {
		return nameRoom;

	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;

	}
}
