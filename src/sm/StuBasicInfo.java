package sm;

public class StuBasicInfo {
	
	String ID;
	String Name;
	String Sex;
	String Birthday;
	String Department;
	String IDcard;
	String Photo;
	String Brief;
	@Override
	public String toString() {
		return  ID + "  " + Name + "  " + Sex + "  " + Birthday + "  "
				+ Department + "  " + IDcard  + "  " + Brief;
	}

}
